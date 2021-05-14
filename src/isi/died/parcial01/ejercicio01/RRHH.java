package isi.died.parcial01.ejercicio01;

import java.util.List;
import java.util.stream.Collectors;

public class RRHH {
	
	static Double sueldoBasico;
	
	public static Double getSueldoBasico() {
		return sueldoBasico;
	}
	
	public static List<ReciboSueldo> recibosSueldos(List <Empleado> lista){
		return lista.stream()
				.map(e -> e.calcularReciboSueldo())
				.collect(Collectors.toList());
	}
}
