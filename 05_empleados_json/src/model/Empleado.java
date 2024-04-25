package model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Empleado {
	@SerializedName(value="nombre") //anotacion de la libreria GSON 
	private String empleado;
	private String email;
	private double salario;
	private String departamento;
	}
}
