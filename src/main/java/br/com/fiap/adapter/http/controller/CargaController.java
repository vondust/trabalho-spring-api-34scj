package br.com.fiap.adapter.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.core.util.CargaInicial;

@RestController
@RequestMapping("/fiap/v1/cargas")
public class CargaController {

	@Autowired
	private CargaInicial cargaInicial;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void carga(@RequestParam(required = false) Integer batchSize) {
		cargaInicial.carga(batchSize);
	}
}
