package br.com.fiap.adapter.datastore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.adapter.datastore.entity.CompraEntity;

public interface CompraRepository extends JpaRepository<CompraEntity, Long>, JpaSpecificationExecutor<CompraEntity> {

	@Query("SELECT compra FROM CompraEntity compra"
			+ " INNER JOIN CartaoEntity cartao ON cartao.id = compra.cartaoUtilizado.id"
			+ " INNER JOIN AlunoEntity aluno ON aluno.cartao.id = cartao.id AND aluno.id = :id")
	List<CompraEntity> extratoDoAluno(@Param("id") Long id);
}
