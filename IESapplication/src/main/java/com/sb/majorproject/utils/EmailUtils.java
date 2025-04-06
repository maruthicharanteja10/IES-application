package com.sb.majorproject.utils;

import java.io.File;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	private final JavaMailSender mailSender;

	public EmailUtils(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmail(String recipient, String subject, String emailBody) throws Exception {
		if (!isValidEmail(recipient)) {
			throw new IllegalArgumentException("Invalid email address: " + recipient);
		}
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.setText(emailBody, true);

		mailSender.send(mimeMessage);
	}
	public boolean sendEmail(String subject, String body, String to,File file) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			helper.addAttachment("files-info", file);
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return email.matches(emailRegex);
	}
}
