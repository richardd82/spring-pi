package com.henry.pijava;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PiJavaApplicationTests {

	@Test
	void contextLoads() throws Exception {

    }
	@Nested
	class ExpensesTests extends ExpenseControllerTest {
	}
	@Nested
	class ExpenseCatTests extends ExpenseCatControllerTests {
	}

}
