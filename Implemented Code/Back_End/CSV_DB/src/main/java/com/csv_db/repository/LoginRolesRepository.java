package com.csv_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csv_db.model.Roles;

public interface LoginRolesRepository extends JpaRepository<Roles, String> {

	Roles findByUsername(String username);

}
