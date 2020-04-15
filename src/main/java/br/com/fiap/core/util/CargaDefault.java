package br.com.fiap.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import br.com.fiap.adapter.datastore.entity.AlunoEntity;
import br.com.fiap.adapter.datastore.entity.CartaoEntity;
import br.com.fiap.adapter.datastore.entity.CompraEntity;

public class CargaDefault {

	private static Integer DEFAULT_BATCH_SIZE = 30;
	private static Integer INDEX = 1;
	private static BigInteger INDEX_CARTAO = BigInteger.valueOf(1000000000000000l);
	private static BigDecimal INDEX_LIMITE = BigDecimal.valueOf(1000);
	private static BigDecimal INDEX_DEBITO = BigDecimal.valueOf(10);
	private static Calendar INDEX_DATA = Calendar.getInstance();

	public static List<AlunoEntity> carregaAlunos(Integer batchSize) {
		if (Objects.isNull(batchSize) || batchSize < 1)
			batchSize = INDEX + DEFAULT_BATCH_SIZE;
		else
			batchSize = INDEX + batchSize;

		List<AlunoEntity> alunos = new ArrayList<>();
		for (; INDEX < batchSize; INDEX++) {
			alunos.add(new AlunoEntity(null, nome(), matricula(), turma(),
					new CartaoEntity(null, numeroCartao(), data(), limite(), BigDecimal.ZERO, null)));
		}

		return alunos;
	}

	public static List<CompraEntity> carregaCompras(List<AlunoEntity> alunos) {
		List<CompraEntity> compras = new ArrayList<>();
		for (AlunoEntity aluno : alunos) {
			for (int i = 0; i < 7; i++) {
				BigDecimal debito = debito(i);
				aluno.getCartao().setValorUtilizado(aluno.getCartao().getValorUtilizado().add(debito));
				compras.add(new CompraEntity(null, debito, new Date(), aluno.getCartao()));
			}
		}

		return compras;
	}

	private static String nome() {
		return "subject_" + INDEX;
	}

	private static String matricula() {
		return String.format("%07d", INDEX);
	}

	private static String turma() {
		return "001-01";
	}

	private static BigInteger numeroCartao() {
		return INDEX_CARTAO.add(BigInteger.valueOf(INDEX));
	}

	private static BigDecimal limite() {
		return INDEX_LIMITE.add(BigDecimal.valueOf(INDEX * 50));
	}

	private static Date data() {
		INDEX_DATA.add(Calendar.DAY_OF_YEAR, INDEX);
		return INDEX_DATA.getTime();
	}

	private static BigDecimal debito(int index) {
		return INDEX_DEBITO.add(BigDecimal.valueOf((INDEX + index) * .01));
	}
}
