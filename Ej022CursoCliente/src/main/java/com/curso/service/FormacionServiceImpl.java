package com.curso.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.curso.model.CursoAux;
import com.curso.model.Formacion;

@Service
public class FormacionServiceImpl implements FormacionService {

	@Autowired(required = true)
	RestTemplate template;

	private String url = "http://localhost:8080/cursos";

	@Override
	public List<Formacion> listaCursos() {

		List<Formacion> formaciones = new ArrayList<Formacion>();
		List<CursoAux> cursos = Arrays.asList(template.getForObject(url, CursoAux[].class));

		for (CursoAux cur : cursos) {
			Formacion form = new Formacion();
			form.setCurso(cur.getNombre());
			form.setPrecio(cur.getPrecio());
			if (cur.getDuracion() >= 50) {
				form.setAsignaturas(10);
			} else {
				form.setAsignaturas(5);
			}
			formaciones.add(form);
		}
		return formaciones;
	}
			
		@Override
		public void altaCurso (Formacion form) {
			
			CursoAux curso = new CursoAux();
			curso.setDuracion(form.getAsignaturas()*10);			
			curso.setNombre(form.getCurso());
			curso.setPrecio((int) form.getPrecio());
			
			List<CursoAux> listaCur = Arrays.asList(template.getForObject(url, CursoAux[].class));
			for(CursoAux curso2: listaCur) {
				if(curso2.getNombre().equals(curso.getNombre())) {
					return;
				}
			}
			
			template.postForLocation(url, curso);
			
		}
	
	}


