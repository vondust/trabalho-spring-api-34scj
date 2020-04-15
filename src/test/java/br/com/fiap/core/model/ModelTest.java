package br.com.fiap.core.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.fiap.core.model.Aluno.AlunoBuilder;
import br.com.fiap.core.model.Cartao.CartaoBuilder;
import br.com.fiap.core.model.Compra.CompraBuilder;

@ActiveProfiles("test")
@SpringBootTest
public class ModelTest {

	private static Date now = new Date();
	
	@Test
	public void testaCamposEValoresDoAluno() {
		Aluno aluno = new Aluno();
		aluno.setId(1l);
		aluno.setNome("nome do aluno");
		aluno.setMatricula("001-01");
		aluno.setCartao(null);

		AlunoBuilder builder = Aluno.builder().id(1l).nome("nome do aluno").matricula("001-01").cartao(null);

		assertThat(aluno).hasFieldOrPropertyWithValue("id", 1l);
		assertThat(aluno).hasFieldOrPropertyWithValue("nome", "nome do aluno");
		assertThat(aluno).hasFieldOrPropertyWithValue("matricula", "001-01");
		assertThat(aluno).hasFieldOrPropertyWithValue("cartao", null);
		assertThat(builder).hasFieldOrPropertyWithValue("id", 1l);
		assertThat(builder).hasFieldOrPropertyWithValue("nome", "nome do aluno");
		assertThat(builder).hasFieldOrPropertyWithValue("matricula", "001-01");
		assertThat(builder).hasFieldOrPropertyWithValue("cartao", null);
		assertThat(aluno.toString()).isEqualTo(builder.build().toString());
	}

	@Test
	public void testaCamposEValoresDoCartao() {
		Cartao cartao = new Cartao();
		cartao.setId(1l);
		cartao.setNumero(BigInteger.valueOf(5555444433332222l));
		cartao.setValorLimite(BigDecimal.valueOf(1000.00d));
		cartao.setValorUtilizado(BigDecimal.valueOf(50.02d));
		cartao.setVencimento(now);

		CartaoBuilder builder = Cartao.builder()
				.id(1l)
				.numero(BigInteger.valueOf(5555444433332222l))
				.valorLimite(BigDecimal.valueOf(1000.00d))
				.valorUtilizado(BigDecimal.valueOf(50.02d))
				.vencimento(now);

		BigDecimal valor = BigDecimal.valueOf(749.99d);

		assertThat(cartao).hasFieldOrPropertyWithValue("id", 1l);
		assertThat(cartao).hasFieldOrPropertyWithValue("numero", BigInteger.valueOf(5555444433332222l));
		assertThat(cartao).hasFieldOrPropertyWithValue("valorLimite", BigDecimal.valueOf(1000.00d));
		assertThat(cartao).hasFieldOrPropertyWithValue("valorUtilizado", BigDecimal.valueOf(50.02d));
		assertThat(cartao).hasFieldOrPropertyWithValue("vencimento", now);
		assertThat(builder).hasFieldOrPropertyWithValue("id", 1l);
		assertThat(builder).hasFieldOrPropertyWithValue("numero", BigInteger.valueOf(5555444433332222l));
		assertThat(builder).hasFieldOrPropertyWithValue("valorLimite", BigDecimal.valueOf(1000.00d));
		assertThat(builder).hasFieldOrPropertyWithValue("valorUtilizado", BigDecimal.valueOf(50.02d));
		assertThat(builder).hasFieldOrPropertyWithValue("vencimento", now);

		assertThat(cartao.toString()).isEqualTo(builder.build().toString());

		cartao.atualizaValorUtilizado(valor);
		assertThat(cartao.getValorUtilizado()).isEqualTo(BigDecimal.valueOf(800.01d));
	}

	@Test
	public void testaCamposEValoresDaCompra() {
		Compra compra = new Compra();
		compra.setId(1l);
		compra.setIdCartaoUtilizado(2l);
		compra.setData(now);
		compra.setValorDebito(BigDecimal.valueOf(800.01d));

		CompraBuilder builder = Compra.builder()
				.id(1l)
				.idCartaoUtilizado(2l)
				.data(now)
				.valorDebito(BigDecimal.valueOf(800.01d));

		assertThat(compra).hasFieldOrPropertyWithValue("id", 1l);
		assertThat(compra).hasFieldOrPropertyWithValue("idCartaoUtilizado", 2l);
		assertThat(compra).hasFieldOrPropertyWithValue("data", now);
		assertThat(compra).hasFieldOrPropertyWithValue("valorDebito", BigDecimal.valueOf(800.01d));
		assertThat(builder).hasFieldOrPropertyWithValue("id", 1l);
		assertThat(builder).hasFieldOrPropertyWithValue("idCartaoUtilizado", 2l);
		assertThat(builder).hasFieldOrPropertyWithValue("data", now);
		assertThat(builder).hasFieldOrPropertyWithValue("valorDebito", BigDecimal.valueOf(800.01d));
		assertThat(compra.toString()).isEqualTo(builder.build().toString());
	}
}
