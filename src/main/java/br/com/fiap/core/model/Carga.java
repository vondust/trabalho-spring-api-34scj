package br.com.fiap.core.model;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.adapter.datastore.entity.AlunoEntity;
import br.com.fiap.adapter.datastore.entity.CompraEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Carga {

	private List<AlunoEntity> alunos = new ArrayList<>();
	private List<CompraEntity> compras = new ArrayList<>();
}
