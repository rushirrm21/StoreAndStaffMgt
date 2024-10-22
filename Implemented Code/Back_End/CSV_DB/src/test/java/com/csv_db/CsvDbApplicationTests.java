package com.csv_db;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvDbApplicationTests {

	@Test
	void main() {
		CsvDbApplication.main(new String[] {});
		boolean val = true;
		assertEquals(true, val);
	}
}
