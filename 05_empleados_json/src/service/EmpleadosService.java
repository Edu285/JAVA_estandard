/* Implementar lógica de negocio de una aplicación que proporcione los siguientes métodos
 *
 * -Lista de empleados de un determinado departamento
 * -Empleado con mayor salario
 * -Lista de departamentos 
 */
package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Empleado;

public class EmpleadosService {
	String ruta="empleados.json";
	private Stream<Empleado> getEmpleados(){//metodo para no tener que meterlo en todos los sitios
		try {
			Gson gson=new Gson();
			return Arrays.stream(gson.fromJson(new FileReader(ruta), Empleado[].class));
		}
		catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
			return Stream.empty();// si no devolvemos un Stream vacio
		}
	} 
	public List<Empleado> ListadoPorDepartamento(String departamento) {
		return getEmpleados()
				.filter(e->e.getDepartamento().equals(departamento)) //Steam<Empleado>
				.toList(); //Lista
	}
	
	public Empleado empleadoMayorSalario() {
		return getEmpleados()
				.max(Comparator.comparingDouble(e->e.getSalario()))
				.orElse(null);
	}
	
	public List<String> listarDepartamentos(){
		return getEmpleados()
				.map(e->e.getDepartamento())
				.distinct()
				.toList();
	}
	
}
