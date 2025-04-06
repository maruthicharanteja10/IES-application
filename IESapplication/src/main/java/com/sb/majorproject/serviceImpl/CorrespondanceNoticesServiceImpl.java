package com.sb.majorproject.serviceImpl;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sb.majorproject.entity.CorrespondanceNotices;
import com.sb.majorproject.entity.EligibilityDetermination;
import com.sb.majorproject.repository.CorrespondanceRepository;
import com.sb.majorproject.repository.EligibilityDeterminationRepository;
import com.sb.majorproject.service.CorrespondanceNoticesService;
import com.sb.majorproject.utils.EmailUtils;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CorrespondanceNoticesServiceImpl implements CorrespondanceNoticesService {

	@Autowired
	private CorrespondanceRepository correspondanceRepository;
	@Autowired
	private EligibilityDeterminationRepository determinationRepository;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public void saveNotices() {
		
		List<EligibilityDetermination> edList = determinationRepository.findAll();
		for (EligibilityDetermination ed : edList) {
			 if (correspondanceRepository.existsByCaseNo(ed.getCaseNo())) {
		            continue;
		        }
			CorrespondanceNotices correspondanceNotices = new CorrespondanceNotices();
			correspondanceNotices.setCaseNo(ed.getCaseNo());
			correspondanceNotices.setPlanName(ed.getPlanName());
			correspondanceNotices.setPlanStatus(ed.getPlanStatus());
			correspondanceNotices.setEligStartDate(ed.getEligStartDate());
			correspondanceNotices.setEligEndDate(ed.getEligEndDate());
			correspondanceNotices.setBenifitAmt(ed.getDenialReason());
			correspondanceNotices.setDenialReason(ed.getDenialReason());
			correspondanceNotices.setNoticeStatus("P");
			correspondanceNotices.setPrintDate(LocalDate.now());
			correspondanceNotices.setCreatedDate(LocalDate.now());
			correspondanceNotices.setNotice_pdf("NotSent");
			correspondanceRepository.save(correspondanceNotices);
		}
	}
	
	@Override
	public List<CorrespondanceNotices> findAllListNotices() {
		// TODO Auto-generated method stub
		return correspondanceRepository.findAll();
	}

	@Override
	public Page<CorrespondanceNotices> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.correspondanceRepository.findAll(pageable);
	}

}
