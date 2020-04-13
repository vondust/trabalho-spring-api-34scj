package br.com.fiap.adapter.http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fiap.application.CompraService;
import br.com.fiap.core.model.Compra;

@RestController
@RequestMapping("/fiap/v1/compras")
public class CompraController {

	@Autowired
	private CompraService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Compra post(@RequestBody Compra model) {
		return service.cria(model);
	}

	@PatchMapping("/{id}")
	public Compra patch(@PathVariable Long id, @RequestBody Compra model) {
		model.setId(id);
		return service.atualiza(id, model);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.remove(id);
	}

	@GetMapping("/{id}")
	public Compra get(@PathVariable Long id) {
		return service.buscaPorId(id);
	}

	@GetMapping
	public List<Compra> get() {
		return service.busca();
	}

	@GetMapping("/extrato/aluno/{id}")
	public ResponseEntity<?> extrato(@RequestHeader HttpHeaders headers, @PathVariable Long id) {
		if (headers.getAccept().contains(MediaType.TEXT_PLAIN))
			return null; // TODO

		return ResponseEntity.ok(service.extratoDoAluno(id));
	}
}
