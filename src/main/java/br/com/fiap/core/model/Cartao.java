package br.com.fiap.core.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Cartao {

	private Long id;
	private BigInteger numero;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vencimento;
	private BigDecimal valorLimite;
	private BigDecimal valorUtilizado;

	@JsonIgnore
	public void atualizaValorUtilizado(BigDecimal valorDebito) {
		setValorUtilizado(getValorUtilizado().add(valorDebito));
	}
}
