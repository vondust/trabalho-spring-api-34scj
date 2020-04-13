package br.com.fiap.adapter.http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.application.CartaoService;
import br.com.fiap.core.model.Cartao;

@RestController
@RequestMapping("/fiap/v1")
public class CartaoController {

	private static final String PATH_ALUNO = "/alunos";
	private static final String PATH_ID_ALUNO = "/{idAluno}";
	private static final String PATH_CARTAO = "/cartoes";
	private static final String PATH_ID_CARTAO = "/{id}";

	@Autowired
	private CartaoService service;

	@PostMapping(PATH_ALUNO + PATH_ID_ALUNO + PATH_CARTAO)
	@ResponseStatus(HttpStatus.CREATED)
	public Cartao post(@PathVariable Long idAluno, @RequestBody Cartao model) {
		return service.cria(idAluno, model);
	}

	@PatchMapping(PATH_ALUNO + PATH_ID_ALUNO + PATH_CARTAO + PATH_ID_CARTAO)
	public Cartao patch(@PathVariable Long idAluno, @PathVariable Long id, @RequestBody Cartao model) {
		model.setId(id);
		return service.atualiza(idAluno, id, model);
	}

	@DeleteMapping(PATH_ALUNO + PATH_ID_ALUNO + PATH_CARTAO + PATH_ID_CARTAO)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idAluno, @PathVariable Long id) {
		service.remove(id);
	}

	@GetMapping(PATH_ALUNO + PATH_ID_ALUNO + PATH_CARTAO + PATH_ID_CARTAO)
	public Cartao get(@PathVariable Long idAluno, @PathVariable Long id) {
		return service.buscaPorId(id);
	}

	@GetMapping(PATH_ID_CARTAO)
	public List<Cartao> get() {
		return service.busca();
	}
}
