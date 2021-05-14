package isi.died.parcial01.ejercicio01;

import java.util.Date;
import java.util.List;

public abstract class Empleado {
	Integer DNI;
	String Nombre;
	Date fechaContratacion;
	String correoElectronico;
	List <Gasto> gastos;
	
	public abstract ReciboSueldo calcularReciboSueldo();
	
}
