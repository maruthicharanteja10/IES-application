package com.sb.majorproject.service;

import java.util.List;

import com.sb.majorproject.entity.ApplicationDetails;

public interface ApplicationDetailsService {

	boolean createApplication(ApplicationDetails details) throws Exception;

	List<ApplicationDetails> viewApplications();

}
