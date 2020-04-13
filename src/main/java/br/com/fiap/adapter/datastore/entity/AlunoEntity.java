package br.com.fiap.adapter.datastore.entity;

import javax.persistence.CascadeType;
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
@Table(name = "alunos")
public class AlunoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nome;
	@Column(unique = true)
	private String matricula;
	private String turma;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true, orphanRemoval = true)
	private CartaoEntity cartao;
}
