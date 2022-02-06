package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class SpringSecurityAuthenicationApplicationTests {

	public static void main(String[] args) {
		
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String ecode = pe.encode("1234");
		System.out.println(ecode);
		boolean matches = pe.matches("1234", ecode);
		System.out.println(matches);
		
		System.out.println();
		
		String ecode2 = pe.encode("5678");
		System.out.println(ecode2);
		boolean matches2 = pe.matches("5678", ecode2);
		System.out.println(matches2);
		
		// $2a$10$4HrYhe7nBj3aogISLLgRnux8t0V9CDWyCeM09J0AVt92fBbM6AUvm
		//boolean matches3 = pe.matches("1234", "$2a$10$4HrYhe7nBj3aogISLLgRnux8t0V9CDWyCeM09J0AVt92fBbM6AUvm");
		//System.out.println(matches3);
	}
		

}
