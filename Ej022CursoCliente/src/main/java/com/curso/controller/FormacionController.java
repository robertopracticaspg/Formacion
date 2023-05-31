package com.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Formacion;
import com.curso.service.FormacionService;

@RestController
public class FormacionController {
@Autowired	
	
	FormacionService service;
	//http://localhost:9090/formacion/1111/Matematicas/100
	@PostMapping(value="formacion/{curso}/{asignatura}/{precio}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void nuevoCurso(
	@PathVariable("curso") String curso, 
	 @PathVariable("asignatura")int asignatura,
	 @PathVariable("precio")double precio){
		Formacion form = new Formacion (curso, asignatura, precio);
		service.altaCurso(form);
	}

}
