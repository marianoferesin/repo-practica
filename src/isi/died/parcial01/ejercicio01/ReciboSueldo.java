package isi.died.parcial01.ejercicio01;

import java.time.*;

public class ReciboSueldo {
	Empleado empleado;
	Integer numero;
	Month mes;
	Double total;
	
	public ReciboSueldo(Empleado empleado) {
		this.empleado = empleado;
		this.mes = LocalDate.now().getMonth();
		this.total = 0.0;
		this.numero = 1;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
}
