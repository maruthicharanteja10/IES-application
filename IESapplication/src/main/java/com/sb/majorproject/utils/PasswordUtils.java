package com.sb.majorproject.utils;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordUtils {
	public static String generateRandomPswd() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random(6, characters);
		return pwd;
	}

//	public static Long generateRandomCaseNo() {
//		Random random = new Random();
//		long randomNumber = 10000000L + random.nextLong(90000000L);
//		return randomNumber;
//	}
}
