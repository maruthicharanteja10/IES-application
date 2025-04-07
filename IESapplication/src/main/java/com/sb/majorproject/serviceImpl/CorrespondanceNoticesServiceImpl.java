package com.sb.majorproject.serviceImpl;

import java.io.File;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sb.majorproject.binding.HistoryNotices;
import com.sb.majorproject.binding.PendingNotices;
import com.sb.majorproject.entity.CorrespondanceNotices;
import com.sb.majorproject.entity.EligibilityDetermination;
import com.sb.majorproject.repository.CorrespondanceRepository;
import com.sb.majorproject.repository.EligibilityDeterminationRepository;
import com.sb.majorproject.service.CorrespondanceNoticesService;
import com.sb.majorproject.utils.EmailUtils;
import com.sb.majorproject.utils.PdfGenerator;


import jakarta.servlet.http.HttpServletResponse;

@Service
public class CorrespondanceNoticesServiceImpl implements CorrespondanceNoticesService {

	@Autowired
	private CorrespondanceRepository correspondanceRepository;
	@Autowired
	private EligibilityDeterminationRepository determinationRepository;
	@Autowired
	private EmailUtils emailUtils;
	@Autowired
	private PdfGenerator pdfGenerator;

	@Override
	public void saveNotices() {

		List<EligibilityDetermination> edList = determinationRepository.findAll();
		for (EligibilityDetermination ed : edList) {
			if (correspondanceRepository.existsByCaseNo(ed.getCaseNo())) {
				continue;
			}
			CorrespondanceNotices correspondanceNotices = new CorrespondanceNotices();
			correspondanceNotices.setCaseNo(ed.getCaseNo());
			correspondanceNotices.setEd_trace_id(ed.getEd_trace_id());
			correspondanceNotices.setPlanName(ed.getPlanName());
			correspondanceNotices.setPlanStatus(ed.getPlanStatus());
			correspondanceNotices.setEligStartDate(ed.getEligStartDate());
			correspondanceNotices.setEligEndDate(ed.getEligEndDate());
			correspondanceNotices.setBenifitAmt(ed.getBenifitAmt());
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
		return correspondanceRepository.findAll();
	}

	@Override
	public Page<CorrespondanceNotices> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.correspondanceRepository.findAll(pageable);
	}

	@Override
	public List<PendingNotices> getPendingNoticesByCaseNo(Long caseNo) {
		List<CorrespondanceNotices> entityList = correspondanceRepository.findByCaseNoAndNoticeStatus(caseNo, "P");
		List<PendingNotices> pendingList = new ArrayList<>();

		for (CorrespondanceNotices entity : entityList) {
			PendingNotices dto = new PendingNotices();
			BeanUtils.copyProperties(entity, dto);
			pendingList.add(dto);
		}

		return pendingList;
	}

	@Override
	public List<HistoryNotices> getHistoryNoticesByCaseNo(Long caseNo) {
		List<CorrespondanceNotices> entityList = correspondanceRepository.findByCaseNoAndNoticeStatus(caseNo, "H");
		List<HistoryNotices> historyList = new ArrayList<>();

		for (CorrespondanceNotices entity : entityList) {
			HistoryNotices dto = new HistoryNotices();
			BeanUtils.copyProperties(entity, dto);
			historyList.add(dto);
		}

		return historyList;
	}

	@Override
	public void togglenoticeStatus(Integer notice_id, HttpServletResponse response) throws Exception {
		Optional<CorrespondanceNotices> cnotices = correspondanceRepository.findById(notice_id);
		if (cnotices.isPresent()) {
			CorrespondanceNotices correspondanceNotices = cnotices.get();
			correspondanceNotices.setNoticeStatus(correspondanceNotices.getNoticeStatus().equals("P") ? "H" : "P");

			correspondanceRepository.save(correspondanceNotices);

			File f = new File("plans.pdf");
			List<CorrespondanceNotices> cn = correspondanceRepository.findAll();
			pdfGenerator.generate(response, cn, f);
			String subject = "Test mail subject";
			String body = "<h1>Test mail body</h1>";
			String to = "charantejadonthireddy@gmail.com";

			emailUtils.sendEmail(subject, body, to, f);
			f.delete();
		}
	}

}
