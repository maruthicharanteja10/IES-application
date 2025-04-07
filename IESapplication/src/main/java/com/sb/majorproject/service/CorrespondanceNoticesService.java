package com.sb.majorproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sb.majorproject.binding.HistoryNotices;
import com.sb.majorproject.binding.PendingNotices;
import com.sb.majorproject.entity.CorrespondanceNotices;

import jakarta.servlet.http.HttpServletResponse;

public interface CorrespondanceNoticesService {

	
	void saveNotices();

	List<CorrespondanceNotices> findAllListNotices();

	Page<CorrespondanceNotices> findPaginated(int pageNo, int pageSize);

	List<PendingNotices> getPendingNoticesByCaseNo(Long caseNo);

	List<HistoryNotices> getHistoryNoticesByCaseNo(Long caseNo);

	void togglenoticeStatus(Integer notice_id, HttpServletResponse response) throws Exception;


	

	

}
