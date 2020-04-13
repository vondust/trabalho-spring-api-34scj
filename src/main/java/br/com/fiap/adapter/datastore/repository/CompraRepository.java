package br.com.fiap.adapter.datastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.adapter.datastore.entity.CompraEntity;

public interface CompraRepository extends JpaRepository<CompraEntity, Long>, JpaSpecificationExecutor<CompraEntity> {
}
