package br.com.fiap.adapter.http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.application.AlunoService;
import br.com.fiap.core.model.Aluno;

@RestController
@RequestMapping("/fiap/v1/alunos")
public class AlunoController {

	@Autowired
	private AlunoService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno post(@RequestBody Aluno model) {
		return service.cria(model);
	}

	@PatchMapping("/{id}")
	public Aluno patch(@PathVariable Long id, @RequestBody Aluno model) {
		model.setId(id);
		return service.atualiza(id, model);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.remove(id);
	}

	@GetMapping("/{id}")
	public Aluno get(@PathVariable Long id) {
		return service.buscaPorId(id);
	}

	@CrossOrigin
	@GetMapping
	public List<Aluno> get() {
		return service.busca();
	}
}
