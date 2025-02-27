package isi.died.parcial01.ejercicio02.app;

import java.util.ArrayList;
import java.util.List;

import isi.died.parcial01.ejercicio02.db.BaseDeDatos;
import isi.died.parcial01.ejercicio02.dominio.*;
import isi.died.parcial01.ejercicio02.db.BaseDeDatosExcepcion;

public class MySysAcadImpl implements MySysAcad {
	private static final BaseDeDatos DB = new BaseDeDatos();


	private List<Materia> materia = new ArrayList<Materia>();
	
	@Override
	public void registrarMateria(Materia d) {
		this.materia.add(d);
	}
	
	private List<Docente> docentes = new ArrayList<Docente>();
	
	@Override
	public void registrarDocente(Docente d) {
		this.docentes.add(d);
	}
	
	private List<Alumno> alumnos = new ArrayList<Alumno>();
	
	@Override
	public void registrarAlumnos(Alumno d) {
		this.alumnos.add(d);
	}
	

	@Override
	public void inscribirAlumnoCursada(Docente d, Alumno a, Materia m, Integer cicloLectivo) throws MateriaYaCursadaException {
		Inscripcion insc = new Inscripcion(cicloLectivo,Inscripcion.Estado.CURSANDO);
		d.agregarInscripcion(insc);
		a.addCursada(insc);
		m.addInscripcion(insc);
		// DESCOMENTAR Y gestionar excepcion
		
		try {
			DB.guardar(insc);
		}
		catch (BaseDeDatosExcepcion e) {
			if(a.materiaYaCursada(m)) {
				
				throw new MateriaYaCursadaException("El alumno ya curso o esta cursando la materia a la que se quiso inscribir");
				
			}
			
		}
	}

	@Override
	public void inscribirAlumnoExamen(Docente d, Alumno a, Materia m) {
		Examen e = new Examen();
		a.addExamen(e);
		d.agregarExamen(e);
		m.addExamen(e);
		// DESCOMENTAR Y gestionar excepcion
		// DB.guardar(e);
	}
	
	public void registrarNota(Examen examen, Integer nota) {
		
		examen.setNota(nota);
		
		if(nota >= 6) {
			Alumno alumno = examen.getAlumno();
			
			alumno.promocionarMateria(examen.getMateria());
			
		}
		
	}
	
	
	public List<Examen> topNExamenes(Alumno a,Integer n,Integer nota){
		
		return a.topNExamenes(n, nota);
				
		
		
	}
	
	

}
