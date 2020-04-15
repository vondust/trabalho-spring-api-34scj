package br.com.fiap;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.fiap.core.model.Aluno;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TrabalhoSpringApplicationTests {

	@LocalServerPort
	private int randomServerPort;
	private static final String localhost = "http://localhost:";
	private TestRestTemplate restTemplate = new TestRestTemplate();

	private static final long id = 1l;

	@Test
	public void testaPost() {
		Aluno aluno = Aluno.builder().id(id).nome("nome do aluno").matricula("001-01").cartao(null).build();
		ResponseEntity<Aluno> exchange = restTemplate.exchange(localhost + randomServerPort + "/fiap/v1/alunos",
				HttpMethod.POST, new HttpEntity<>(aluno), Aluno.class);

		Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(aluno).hasFieldOrPropertyWithValue("id", 1l);
		assertThat(aluno).hasFieldOrPropertyWithValue("nome", "nome do aluno");
		assertThat(aluno).hasFieldOrPropertyWithValue("matricula", "001-01");
		assertThat(aluno).hasFieldOrPropertyWithValue("cartao", null);
	}

	@Test
	public void testaGet() {
		ResponseEntity<Aluno> exchange = restTemplate.exchange(localhost + randomServerPort + "/fiap/v1/alunos/" + id,
				HttpMethod.GET, HttpEntity.EMPTY, Aluno.class);

		Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
		Aluno aluno = exchange.getBody();
		assertThat(aluno).hasFieldOrPropertyWithValue("id", 1l);
		assertThat(aluno).hasFieldOrPropertyWithValue("nome", "nome do aluno");
		assertThat(aluno).hasFieldOrPropertyWithValue("matricula", "001-01");
		assertThat(aluno).hasFieldOrPropertyWithValue("cartao", null);
	}
}
