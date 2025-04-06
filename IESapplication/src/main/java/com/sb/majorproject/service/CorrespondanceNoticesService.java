package com.sb.majorproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sb.majorproject.entity.CorrespondanceNotices;

import jakarta.servlet.http.HttpServletResponse;

public interface CorrespondanceNoticesService {

	
	void saveNotices();

	List<CorrespondanceNotices> findAllListNotices();

	Page<CorrespondanceNotices> findPaginated(int pageNo, int pageSize);

}
