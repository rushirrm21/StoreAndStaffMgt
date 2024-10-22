package com.csv_db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "login_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {


	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "roleId")
	private String roleId;
}
