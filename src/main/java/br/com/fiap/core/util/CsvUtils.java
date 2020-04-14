package br.com.fiap.core.util;

import java.util.List;

import br.com.fiap.core.model.Compra;

public class CsvUtils {

	public static final String SPLITTER = ";";
	public static final String END_LINE = "\n";
	
	public static byte[] generate(List<Compra> compras) {
		StringBuilder builder = new StringBuilder();
		
		csvDefaulHeader(builder);
		for (Compra compra : compras)
			builder
				.append(compra.getId()).append(SPLITTER)
				.append(compra.getData()).append(SPLITTER)
				.append(compra.getValorDebito()).append(SPLITTER)
				.append(compra.getIdCartaoUtilizado()).append(END_LINE);
		
		return builder.toString().getBytes();
	}

	public static void csvDefaulHeader(StringBuilder builder) {
		builder
			.append("ID").append(SPLITTER)
			.append("DATA").append(SPLITTER)
			.append("VALOR DEBITO").append(SPLITTER)
			.append("CARTAO").append(END_LINE);
	}
}