package com.csv_db.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.csv_db.model.LoginCredentials;
import com.csv_db.repository.LoginCredentialsRepository;

@Service
public class CustomeUserDetialsService implements UserDetailsService {

	@Autowired
	LoginCredentialsRepository loginRepo;

	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginCredentials logCred = loginRepo.findByUsername(username);
		if (username.equals(logCred.getUsername())) {
			return new User(logCred.getUsername(), logCred.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("USER NOT OUND !");
		}
	}

	public String checkCredentials(String username, String password) {
		LoginCredentials logCred = loginRepo.findByUsername(username);
		if (logCred != null && username.equals(logCred.getUsername())) {
			boolean result = bcryptPasswordEncoder.matches(password, logCred.getPassword());
			if (result) {
				return null;
			} else {
				return "Invalid Password";
			}

		} else {
			return "Invalid Username";
		}

	}

}
