package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.curso.model.Formacion;
import com.curso.service.FormacionService;

@RestController
public class FormacionController {
	@Autowired

	FormacionService service;
	// http://localhost:9090/formaciones
	@GetMapping(value = "formaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Formacion> formacion() {
		return service.listaCursos();
	}

	// http://localhost:9090/formaciones/Programacion/10/10/1111
	@PostMapping(value = "formaciones/{curso}/{asignatura}/{precio}/{codCurso}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void nuevoCurso(@PathVariable("curso") String curso,
			@PathVariable("asignatura") int asignatura,
			@PathVariable("precio") double precio,
			@PathVariable("codCurso") int codCurso) {
		Formacion form = new Formacion(curso, asignatura, precio);
		service.altaCurso(form, codCurso);
	}

}
