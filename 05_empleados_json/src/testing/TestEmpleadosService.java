package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import service.EmpleadosService;

class TestEmpleadosService {
	static EmpleadosService service;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		service=new EmpleadosService();
	}

	@Test
	void testListadoPorDepartamento(String departamento) {
		assertEquals(2,service.ListadoPorDepartamento("RRHH").size());
	}
	
	@Test
	void testEmpleadoMayorSalario() {
		assertEquals("Javier",service.empleadoMayorSalario().getNombre());
	}
	
	@Test
	void testListarDepartamentos() {
		assertEquals(3,service.listarDepartamentos().size());
	}
}
