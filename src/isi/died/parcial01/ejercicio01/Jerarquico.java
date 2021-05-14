package isi.died.parcial01.ejercicio01;

import java.time.LocalDate;
import java.time.Month;

public class Jerarquico extends Empleado {
	
	public ReciboSueldo calcularReciboSueldo() {
			
			ReciboSueldo recibo = new ReciboSueldo(this);
			
			Double totalACobrar = RRHH.getSueldoBasico();
			
			totalACobrar -= RRHH.getSueldoBasico()*0.05;
			totalACobrar += RRHH.getSueldoBasico()*0.08;
			
			totalACobrar += this.gastos.stream()
								.map(g -> g.getTotal())
								.map(g -> g = g*0.10)
								.reduce((acum, ant) -> {return acum+ant;})
								.get();
			
			if(LocalDate.now().getMonth() == Month.MARCH) totalACobrar *= 1.5;
			
			recibo.setTotal(totalACobrar);
			
			return recibo;
	}

}
