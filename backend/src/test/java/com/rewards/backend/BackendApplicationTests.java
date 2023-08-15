package com.rewards.backend;

import com.rewards.backend.controller.RewardsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RewardsController.class)
class BackendApplicationTests {
	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldCalculateMonthlyRewardsFromCSV() throws Exception {
		String expectedResponse = "[{\"month\":\"APRIL\",\"totalSpent\":129243.02,\"totalTransactions\":122,\"totalRewards\":250519},{\"month\":\"MAY\",\"totalSpent\":19638.93,\"totalTransactions\":69,\"totalRewards\":34946},{\"month\":\"JUNE\",\"totalSpent\":15816.88,\"totalTransactions\":91,\"totalRewards\":25297}]";
		MockMultipartFile mockMultipartFile = new MockMultipartFile(
				"file",
				"demo-transactions.csv",
				"text/csv",
				new ClassPathResource("demo-transactions.csv").getInputStream());

		String responseString = this.mvc.perform(
				MockMvcRequestBuilders.multipart("/rewards/calculate-monthly-rewards")
						.file(mockMultipartFile)
				)
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		assertEquals("Response does not match", expectedResponse, responseString);
	}
}
