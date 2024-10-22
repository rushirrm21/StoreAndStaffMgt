package com.csv_db.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.csv_db.helper.JwtUtil;
import com.csv_db.model.JwtRequest;
import com.csv_db.service.CustomeUserDetialsService;
import com.csv_db.service.RoleService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtController {

	@Autowired
	private CustomeUserDetialsService customeUserDetialsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public static final String RESPONSEKEY1 = "token";
	public static final String RESPONSEKEY2 = "roleId";
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> generateTokenn(@RequestBody JwtRequest jwtRequest){
		String token = null;
		try {
			token = customeUserDetialsService.checkCredentials(jwtRequest.getUsername(), jwtRequest.getPassword());
			if (token != null) {
				Map<String, String> mpp = new HashMap<>();
				mpp.put(RESPONSEKEY1, token);
				mpp.put(RESPONSEKEY2, "0");
				return new ResponseEntity<>(mpp, HttpStatus.OK);
			}

			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (Exception e) {
			e.printStackTrace();
			token = "Failed";
			Map<String, String> mpp = new HashMap<>();
			mpp.put(RESPONSEKEY1, token);
			mpp.put(RESPONSEKEY2, "0");
			return new ResponseEntity<>(mpp, HttpStatus.OK);
		}

		UserDetails userDetials = this.customeUserDetialsService.loadUserByUsername(jwtRequest.getUsername());

		token = this.jwtUtil.generateToken(userDetials);
		String roleId= roleService.getRolesOfUser(jwtRequest.getUsername());
		Map<String, String> mpp = new HashMap<>();
		mpp.put(RESPONSEKEY1, token);
		mpp.put(RESPONSEKEY2, roleId);
		return new ResponseEntity<>(mpp, HttpStatus.OK);
	}
}
