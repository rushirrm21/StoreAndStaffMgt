package com.csv_db.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.csv_db.model.LoginCredentials;
import com.csv_db.repository.LoginCredentialsRepository;

@ExtendWith(MockitoExtension.class)
class CustomerUserDetailServiceTest {

	@InjectMocks
	CustomeUserDetialsService customeUserDetialsService;

	@Mock
	LoginCredentialsRepository loginRepo;

	@Spy
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Test
	void checkCredentialsTest1() throws IOException {
		LoginCredentials logCred = new LoginCredentials("rushi@gmail.com",
				"$10$fbEEO5/IuPUE07mBymnTQOTX8HSM.R9No91evSDdbngjMuh8Ohxc.");
		when(loginRepo.findByUsername("rushi@gmail.com")).thenReturn(logCred);
		when(bcryptPasswordEncoder.matches("QWE@21rrm", logCred.getPassword())).thenReturn(true);
		assertEquals(null, customeUserDetialsService.checkCredentials("rushi@gmail.com", "QWE@21rrm"));
	}

	@Test
	void checkCredentialsTest2() throws IOException {
		LoginCredentials logCred = new LoginCredentials("rushi@gmail.com",
				"$10$fbEEO5/IuPUE07mBymnTQOTX8HSM.R9No91evSDdbngjMuh8Ohxc.");
		when(loginRepo.findByUsername("rushi@gmail.com")).thenReturn(logCred);
		assertEquals("Invalid Password", customeUserDetialsService.checkCredentials("rushi@gmail.com", "QWE@21rrm"));
	}

	@Test
	void checkCredentialsTest3() throws IOException {
		when(loginRepo.findByUsername("rushi@gmail.com")).thenReturn(null);
		assertEquals("Invalid Username", customeUserDetialsService.checkCredentials("rushi@gmail.com", "QWE@21rrm"));
	}
	
	
	@Test
	void loadUserByUsernameTest1() throws IOException {
		LoginCredentials logCred = new LoginCredentials("rushi@gmail.com",
				"$10$fbEEO5/IuPUE07mBymnTQOTX8HSM.R9No91evSDdbngjMuh8Ohxc.");
		UserDetails user = new User(logCred.getUsername(), logCred.getPassword(), new ArrayList<>());
		when(loginRepo.findByUsername("rushi@gmail.com")).thenReturn(logCred);
		assertEquals(user, customeUserDetialsService.loadUserByUsername("rushi@gmail.com"));
	}
}
