package com.csv_db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csv_db.model.Roles;
import com.csv_db.repository.LoginRolesRepository;

@Service
public class RoleService {

	@Autowired
	LoginRolesRepository loginRolesRepository;

	public String getRolesOfUser(String username) {
		Roles role = loginRolesRepository.findByUsername(username);
		return role.getRoleId();
	}
}
