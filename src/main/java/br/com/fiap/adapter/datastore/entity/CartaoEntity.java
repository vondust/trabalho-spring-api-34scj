package br.com.fiap.adapter.datastore.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cartoes")
public class CartaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(unique = true, nullable = false)
	private BigInteger numero;
	private Date vencimento;
	private BigDecimal valorLimite;
	private BigDecimal valorUtilizado;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "cartao")
	private AlunoEntity aluno;
}
