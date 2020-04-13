package br.com.fiap.adapter.datastore.repository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.adapter.datastore.entity.CartaoEntity;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long>, JpaSpecificationExecutor<CartaoEntity> {

	@Transactional
	default void remove(Long id) {
		CartaoEntity cartao = findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
		cartao.getAluno().setCartao(null);
		delete(cartao);
	}
}
