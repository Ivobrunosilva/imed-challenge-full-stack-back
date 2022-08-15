package com.imed.challenge.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class PasswordEncoderUtil {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("12345"));
	}

}
