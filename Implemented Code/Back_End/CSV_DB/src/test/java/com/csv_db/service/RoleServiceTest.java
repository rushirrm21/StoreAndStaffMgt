package com.csv_db.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.csv_db.model.Roles;
import com.csv_db.repository.LoginRolesRepository;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

	@InjectMocks
	RoleService roleService;

	@Mock
	LoginRolesRepository loginRolesRepository;

	@Test
	void saveToDBTest1() throws IOException {
		Roles role = new Roles("rushi@gmail.com", "123");
		when(loginRolesRepository.findByUsername("rushi@gmail.com")).thenReturn(role);
		assertEquals("123", roleService.getRolesOfUser("rushi@gmail.com"));
	}
}
