package com.sb.majorproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sb.majorproject.entity.KidsDetails;

public interface KidsDetailsService {

	boolean savekidsDetails(KidsDetails kidsDetails, Long caseNo);

	List<KidsDetails> getAllKidsDetails();

	

}
