package com.csv_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csv_db.model.LoginCredentials;

public interface LoginCredentialsRepository extends JpaRepository<LoginCredentials, String> {

	LoginCredentials findByUsername(String username);

}
