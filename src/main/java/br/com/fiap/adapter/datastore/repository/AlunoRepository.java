package br.com.fiap.adapter.datastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.adapter.datastore.entity.AlunoEntity;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long>, JpaSpecificationExecutor<AlunoEntity> {
}
