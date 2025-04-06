package com.sb.majorproject.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.repository.ApplicationDetailsRepository;
import com.sb.majorproject.service.ApplicationDetailsService;
import com.sb.majorproject.utils.EmailUtils;

@Service
public class ApplicationDetailsServiceImpl implements ApplicationDetailsService {

    @Autowired
    private ApplicationDetailsRepository applicationDetailsRepo;

    @Autowired
    private EmailUtils emailUtils;

    @Override
    public boolean createApplication(ApplicationDetails details) throws Exception {
        if (applicationDetailsRepo.existsByEmail(details.getEmail())) {
            throw new Exception("Email already registered. Please use a different email.");
        }

        if (!details.getAadhaarNumber().matches("\\d{12}")) {
            throw new Exception("Invalid Aadhaar Number. Must be exactly 12 numeric digits.");
        }

          char firstDigit = details.getAadhaarNumber().charAt(0);

        if (firstDigit == '8') { 
            Long caseNo = generateUniqueCaseNumber();
            details.setCaseNo(caseNo);
            details.setCreatedDate(LocalDate.now());
            details.setStateName("Andhra Pradesh");

            ApplicationDetails savedDetails = applicationDetailsRepo.save(details);
            System.out.println("Application saved with Case No: " + savedDetails.getCaseNo());

            // Send email
            String recipient = details.getEmail();
            String subject = "Please Do Not Share This Case Number";
            String emailBody = "<h1>Use the Case Number below to proceed with your application</h1>"
                    + "<p>Case Number: <strong>" + caseNo + "</strong></p>";
            
            emailUtils.sendEmail(recipient, subject, emailBody);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ApplicationDetails> viewApplications() {
        return applicationDetailsRepo.findAll();
    }

    // Method to generate a unique random case number
    private Long generateUniqueCaseNumber() {
        Random random = new Random();
        Long caseNo;
        Optional<ApplicationDetails> existingCase;

        do {
            caseNo = 10000000L + random.nextInt(90000000); 
            existingCase = applicationDetailsRepo.findById(caseNo);
        } while (existingCase.isPresent()); // Ensure uniqueness

        return caseNo;
    }

	@Override
	public Page<ApplicationDetails> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.applicationDetailsRepo.findAll(pageable);
	}
}
