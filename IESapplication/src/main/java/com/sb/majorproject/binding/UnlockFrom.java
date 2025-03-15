package com.sb.majorproject.binding;

import lombok.Data;

@Data
public class UnlockFrom {
	private String email;
	private String tempPswd;
	private String newPswd;
	private String confirmPswd;
}
