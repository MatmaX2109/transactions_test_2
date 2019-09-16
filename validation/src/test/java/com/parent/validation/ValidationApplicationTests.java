package com.parent.validation;

import com.parent.validation.model.Transaction;
import com.parent.validation.model.TransactionMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureWebTestClient
public class ValidationApplicationTests {

	@Autowired
	private MockMvc mockMvc;

//	@Autowired
//	private WebTestClient webClient;

	@Test
	public void transactionOk() throws Exception {
		String transaction = "{\n" +
				"\t\"type\":\"IBAN_TO_IBAN\",\n" +
				"\t\"description\":\"d\",\n" +
				"\t\"amount\":\"1\",\n" +
				"\t\"payer\":{\n" +
				"\t\t\"name\":\"a\",\n" +
				"\t\t\"cnp\":\"1890921212716\",\n" +
				"\t\t\"iban\":\"RO49AAAA1B31007593840000\"\n" +
				"\t},\n" +
				"\t\"payee\":{\n" +
				"\t\t\"name\":\"a\",\n" +
				"\t\t\"cnp\":\"1850512025795\",\n" +
				"\t\t\"iban\":\"RO49AAAA1B31007593840000\"\n" +
				"\t}\n" +
				"}\n";

		mockMvc.perform(post("/sendTransaction")
				.content(transaction)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.timestamp", is(notNullValue())))
				.andExpect(jsonPath("$.message", is("Tranzactia a fost trimisa")))
				.andExpect(content()
						.contentType(MediaType.APPLICATION_JSON_UTF8));
	}


	@Test
	public void transactionWrongCnp() throws Exception {
		String transaction = "{\n" +
				"\t\"type\":\"IBAN_TO_IBAN\",\n" +
				"\t\"description\":\"d\",\n" +
				"\t\"amount\":\"1\",\n" +
				"\t\"payer\":{\n" +
				"\t\t\"name\":\"a\",\n" +
				"\t\t\"cnp\":\"1890921212716\",\n" +
				"\t\t\"iban\":\"RO49AAAA1B31007593840000\"\n" +
				"\t},\n" +
				"\t\"payee\":{\n" +
				"\t\t\"name\":\"a\",\n" +
				"\t\t\"cnp\":\"1850512025795\",\n" +
				"\t\t\"iban\":\"RO49AAAA1B31007593840000\"\n" +
				"\t}\n" +
				"}\n";

		mockMvc.perform(post("/sendTransaction")
				.content(transaction)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.timestamp", is(notNullValue())))
				.andExpect(jsonPath("$.error", is("Validation Error")))
				.andExpect(jsonPath("$.errors.['payer.cnp']", is("CNP incorect")))
				.andExpect(content()
						.contentType(MediaType.APPLICATION_JSON_UTF8));
	}


	WebClient webClient = WebClient.create();

	@Test
	public void transactionOk2() throws Exception {

		String transaction = "{\n" +
				"\t\"type\":\"IBAN_TO_IBAN\",\n" +
				"\t\"description\":\"d\",\n" +
				"\t\"amount\":\"1\",\n" +
				"\t\"payer\":{\n" +
				"\t\t\"name\":\"a\",\n" +
				"\t\t\"cnp\":\"1890921212716\",\n" +
				"\t\t\"iban\":\"RO49AAAA1B31007593840000\"\n" +
				"\t},\n" +
				"\t\"payee\":{\n" +
				"\t\t\"name\":\"a\",\n" +
				"\t\t\"cnp\":\"1850512025795\",\n" +
				"\t\t\"iban\":\"RO49AAAA1B31007593840000\"\n" +
				"\t}\n" +
				"}\n";

		Transaction t = new Transaction();
		t.setDescription("A");

		webClient.post()
				.uri("/sendTransaction")
				.body(Mono.just(transaction), String.class)
				.retrieve()
				.bodyToMono(String.class);

	}


}
