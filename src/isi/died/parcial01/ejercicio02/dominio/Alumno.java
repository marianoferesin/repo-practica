package isi.died.parcial01.ejercicio02.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import isi.died.parcial01.ejercicio02.dominio.Inscripcion.Estado;

public class Alumno {
	
	private static Integer ID_GENERATOR=0;
	
	private Integer id;
	private String nombre;

	private List<Inscripcion> materiasCursadas;
	private List<Examen> examenes;
	
	public Alumno() {
		this.id = ID_GENERATOR++;
		this.materiasCursadas = new ArrayList<Inscripcion>();
		this.examenes = new ArrayList<Examen>();
	}
	
	
	public Alumno(String nombre) {
		this();
		this.nombre = nombre;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void addExamen(Examen e) {
		this.examenes.add(e);
		e.setAlumno(this);
	}
	
	public void addCursada(Inscripcion e) {
		this.materiasCursadas.add(e);
		e.setInscripto(this);
	}


	public boolean materiaYaCursada(Materia materia) {
		return materiasCursadas.stream()
				.filter(i -> i.getMateria() == materia)
				.filter(i -> i.getEstado() == Estado.LIBRE)
				.findAny()
				.isPresent();
	}


	public void promocionarMateria(Materia materia) {
		Inscripcion inscripcionAPromocionar = new Inscripcion();
		
		for(Inscripcion insc: materiasCursadas) {
			if(insc.getMateria() == materia) inscripcionAPromocionar = insc;
		}
		
		inscripcionAPromocionar.setEstado(Estado.PROMOCIONADO);
		
	}
	
	public List<Examen> topNExamenes(Integer n,Integer nota){
		
		List <Examen> listaExamenes = examenes.stream()
				.filter(e -> e.getNota()>=nota)
				.collect(Collectors.toList());
		
		listaExamenes.sort((e1,e2) -> (e2.getNota()).compareTo(e1.getNota()));
		
		while(listaExamenes.size()>n) listaExamenes.remove(listaExamenes.size()-1);
		
		return listaExamenes;
	}

}
