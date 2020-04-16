package br.com.fiap.adapter.http.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.fiap.core.model.Aluno;
import br.com.fiap.core.model.Cartao;

@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AlunoControllerTests {

	private static String urlAlunos;
	private static TestRestTemplate restTemplate;
	private static final long id = 2l;
	private static final long idCartao = 1l;
	private Aluno aluno;
	private Aluno patch;
	private Cartao cartao;
	private final Date now = new Date();

	public AlunoControllerTests(@Value("${local.server.port}") int port) {
		urlAlunos = "http://localhost:" + port + "/fiap/v1/alunos";
		restTemplate = new TestRestTemplate();

		cartao = Cartao.builder()
				.id(idCartao)
				.numero(BigInteger.valueOf(5555444433332222l))
				.valorLimite(BigDecimal.valueOf(1000.0d))
				.valorUtilizado(BigDecimal.valueOf(50.50d))
				.vencimento(now)
				.build();

		aluno = Aluno.builder()
				.id(id)
				.nome("nome do aluno")
				.matricula("32145678")
				.turma("001-01")
				.cartao(cartao)
				.build();

		patch = Aluno.builder()
				.id(id)
				.nome("nome patch")
				.matricula("12345678")
				.turma("001-02")
				.cartao(cartao)
				.build();
	}

	@Test
	@Order(1)
	public void testaPost() {
		ResponseEntity<Aluno> exchange = restTemplate.exchange(
				urlAlunos, 
				HttpMethod.POST,
				new HttpEntity<>(aluno), 
				Aluno.class);

		Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Aluno response = exchange.getBody();
		assertThat(response.getId()).isEqualTo(id);
		assertThat(response.getNome()).isEqualTo("nome do aluno");
		assertThat(response.getMatricula()).isEqualTo("32145678");
		assertThat(response.getTurma()).isEqualTo("001-01");
		assertThat(response.getCartao().getNumero()).isEqualTo(BigInteger.valueOf(5555444433332222l));
		assertThat(response.getCartao().getValorLimite()).isEqualByComparingTo(BigDecimal.valueOf(1000.0d));
		assertThat(response.getCartao().getValorUtilizado()).isEqualByComparingTo(BigDecimal.valueOf(50.50d));
		assertThat(response.getCartao().getVencimento()).isEqualToIgnoringHours(now);
	}

	@Test
	@Order(2)
	public void testaGet() {
		ResponseEntity<Aluno> exchange = restTemplate.exchange(
				urlAlunos + "/" + id, 
				HttpMethod.GET,
				HttpEntity.EMPTY, 
				Aluno.class);

		Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
		Aluno response = exchange.getBody();
		assertThat(response.getId()).isEqualTo(id);
		assertThat(response.getNome()).isEqualTo("nome do aluno");
		assertThat(response.getMatricula()).isEqualTo("32145678");
		assertThat(response.getTurma()).isEqualTo("001-01");
		assertThat(response.getCartao().getNumero()).isEqualTo(BigInteger.valueOf(5555444433332222l));
		assertThat(response.getCartao().getValorLimite()).isEqualByComparingTo(BigDecimal.valueOf(1000.0d));
		assertThat(response.getCartao().getValorUtilizado()).isEqualByComparingTo(BigDecimal.valueOf(50.50d));
		assertThat(response.getCartao().getVencimento()).isEqualToIgnoringHours(now);
	}

	@Test
	@Order(3)
	public void testaPatch() {
		ResponseEntity<Aluno> exchange = restTemplate.exchange(
				urlAlunos + "/" + id, 
				HttpMethod.PUT,
				new HttpEntity<>(patch), 
				Aluno.class);

		Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
		Aluno response = exchange.getBody();
		System.out.println(response);
		assertThat(response.getId()).isEqualTo(id);
		assertThat(response.getNome()).isEqualTo("nome patch");
		assertThat(response.getMatricula()).isEqualTo("12345678");
		assertThat(response.getTurma()).isEqualTo("001-02");
		assertThat(response.getCartao().getNumero()).isEqualTo(BigInteger.valueOf(5555444433332222l));
		assertThat(response.getCartao().getValorLimite()).isEqualByComparingTo(BigDecimal.valueOf(1000.0d));
		assertThat(response.getCartao().getValorUtilizado()).isEqualByComparingTo(BigDecimal.valueOf(50.50d));
		assertThat(response.getCartao().getVencimento()).isEqualToIgnoringHours(now);
	}
	
	@Test
	@Order(4)
	@SuppressWarnings("rawtypes")
	public void testaGetAll() {
		ResponseEntity<List> exchange = restTemplate.exchange(
				urlAlunos, 
				HttpMethod.GET,
				HttpEntity.EMPTY, 
				List.class);

		Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(exchange.getBody().size()).isEqualTo(1);
	}
	
	@Test
	@Order(5)
	public void testaDelete() {
		ResponseEntity<Void> exchange = restTemplate.exchange(
				urlAlunos + "/" + id, 
				HttpMethod.DELETE,
				HttpEntity.EMPTY, 
				Void.class);

		Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}
