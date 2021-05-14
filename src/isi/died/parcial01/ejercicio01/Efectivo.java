package isi.died.parcial01.ejercicio01;

import java.time.LocalDate;
import java.time.Month;

public class Efectivo extends Empleado {
	
	public ReciboSueldo calcularReciboSueldo() {
		
		ReciboSueldo recibo = new ReciboSueldo(this);
		
		Double totalACobrar = RRHH.getSueldoBasico();
		
		totalACobrar -= RRHH.getSueldoBasico()*0.03;
		totalACobrar -= RRHH.getSueldoBasico()*0.11;
		
		totalACobrar += this.gastos.stream()
							.filter(g -> g.getAprobado())
							.map(g -> g.getTotal())
							.reduce((acum, ant) -> {return acum+ant;})
							.get();
		
		if(LocalDate.now().getMonth() == Month.MARCH) totalACobrar *= 1.5;
		
		recibo.setTotal(totalACobrar);
		
		return recibo;
	}

}
