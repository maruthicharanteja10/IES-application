package com.sb.majorproject.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.entity.EducationDetails;
import com.sb.majorproject.entity.EligibilityDetermination;
import com.sb.majorproject.entity.IncomeDetails;
import com.sb.majorproject.entity.KidsDetails;
import com.sb.majorproject.entity.PlanSelection;
import com.sb.majorproject.repository.ApplicationDetailsRepository;
import com.sb.majorproject.repository.EducationDetailsRepository;
import com.sb.majorproject.repository.EligibilityDeterminationRepository;
import com.sb.majorproject.repository.IncomeDetailsRepository;
import com.sb.majorproject.repository.KidsDetailsRepository;
import com.sb.majorproject.repository.PlanSelectionRepository;
import com.sb.majorproject.service.EligibilityDeterminationService;

@Service
public class EligibilityDeterminationServiceImpl implements EligibilityDeterminationService {
	@Autowired
	private EligibilityDeterminationRepository determinationRepository;
	@Autowired
	private PlanSelectionRepository planSelectionRepository;
	@Autowired
	private ApplicationDetailsRepository applicationDetailsRepository;
	@Autowired
	private KidsDetailsRepository kidsDetailsRepository;
	@Autowired
	private EducationDetailsRepository educationDetailsRepository;
	@Autowired
	private IncomeDetailsRepository incomeDetailsRepository;

	@Override
	public boolean checkEligibility(EligibilityDetermination determination, Long caseNo) {
		// Check if eligibility determination already exists
		if (determinationRepository.existsByCaseNo(caseNo)) {
			return false;

		}

		// Fetch all necessary details based on caseNo
		Optional<PlanSelection> planOpt = planSelectionRepository.findByCaseNo(caseNo);
		Optional<ApplicationDetails> appOpt = applicationDetailsRepository.findByCaseNo(caseNo);
		Optional<KidsDetails> kidsOpt = kidsDetailsRepository.findByApplicationDetails_CaseNo(caseNo);
		Optional<IncomeDetails> incomeOpt = incomeDetailsRepository.findByApplicationDetails_CaseNo(caseNo);
		Optional<EducationDetails> eduOpt = educationDetailsRepository.findByApplicationDetails_CaseNo(caseNo);

		// Ensure all required data is present
		if (planOpt.isEmpty() || appOpt.isEmpty() || incomeOpt.isEmpty() || eduOpt.isEmpty() || kidsOpt.isEmpty()) {
			determination.setPlanStatus("Denied");
			determination.setDenialReason("Missing required details for eligibility determination");
			determination.setCaseNo(caseNo);
			determination.setCreatedDate(LocalDate.now());
			determinationRepository.save(determination);

		}

		// Extracting data
		PlanSelection selection = planOpt.get();
		ApplicationDetails applicationDetails = appOpt.get();
		IncomeDetails incomeDetails = incomeOpt.get();
		EducationDetails educationDetails = eduOpt.get();
		KidsDetails kidsDetails = kidsOpt.get();

		LocalDate birthDate = applicationDetails.getDob();
		int age = Period.between(birthDate, LocalDate.now()).getYears();

		// Set plan name
		determination.setPlanName(selection.getPlanName());

		// Compute total income
		double totalIncome = incomeDetails.getPropertyIncome() + incomeDetails.getSalary()
				+ incomeDetails.getRentIncome();

		// Plan-based eligibility checks
		switch (selection.getPlanName()) {
		case "SNAP":
			if (age > 18 || totalIncome == 0) {
				determination.setPlanStatus("Approved");
				determination.setBenifitAmt("$200");
			} else {
				determination.setPlanStatus("Denied");
				determination.setDenialReason("Age is Lessthan 18 And You have Income ");
			}
			break;

		case "CCAP":
			if (kidsDetails.getKidsCaseNo().equals(applicationDetails.getCaseNo()) && totalIncome <= 500000) {
				determination.setPlanStatus("Approved");
				determination.setBenifitAmt("$250");
			} else {
				determination.setPlanStatus("Denied");
				determination.setDenialReason("Have No Child (or) Have More Income");
			}
			break;

		case "Medicaid":
			if ("btech".equalsIgnoreCase(educationDetails.getHighestDegree()) || totalIncome <= 300000) {
				determination.setPlanStatus("Approved");
				determination.setBenifitAmt("$350");
			} else {
				determination.setPlanStatus("Denied");
				determination.setDenialReason("Having Resources (or) Have More Income");
			}
			break;

		case "Medicare":
			if (age > 60) {
				determination.setPlanStatus("Approved");
				determination.setBenifitAmt("$600");
			} else {
				determination.setPlanStatus("Denied");
				determination.setDenialReason("Your age is less than 60");
			}
			break;

		case "QHP":
			if (totalIncome > 500000) {
				determination.setPlanStatus("Approved");
				determination.setBenifitAmt("Purchase this plan to get $500 benefit");
			} else {
				determination.setPlanStatus("Denied");
				determination.setDenialReason("You Should Purchase the Plan ");
			}
			break;

		case "RIW":
			if ("btech".equalsIgnoreCase(educationDetails.getHighestDegree()) || totalIncome == 0) {
				determination.setPlanStatus("Approved");
				determination.setBenifitAmt("$450");
			} else {
				determination.setPlanStatus("Denied");
				determination.setDenialReason("You should have atleast Btech Degree and Have NoIncome");
			}
			break;

		default:
			determination.setPlanStatus("Denied");
			determination.setDenialReason("Not applicable to any plans");
			break;
		}

		// Set eligibility start and end dates
		determination.setEligStartDate(LocalDate.now());
		determination.setEligEndDate(LocalDate.now().plusMonths(6));
		determination.setCaseNo(caseNo);
		determination.setCreatedDate(LocalDate.now());

		// Save eligibility determination
		determinationRepository.save(determination);
		return true;
	}

	@Override
	public List<EligibilityDetermination> findAllListEligibility() {
		return determinationRepository.findAll();
	}

	@Override
	public Page<EligibilityDetermination> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.determinationRepository.findAll(pageable);
	}

}
