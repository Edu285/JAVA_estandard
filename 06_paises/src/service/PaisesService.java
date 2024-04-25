/*Metodos
-Lista de continentes
-Lista de paises a partir del continente
-Pais más poblado
-Tabla con paises agurpados por continente
-Pais a partir de su capital */
package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Pais;



public class PaisesService {
	String ruta="paises.json";
	private Stream<Pais> getPaises(){//metodo para no tener que meterlo en todos los sitios
		try {
			Gson gson=new Gson();
			return Arrays.stream(gson.fromJson(new FileReader(ruta), Pais[].class));
		}
		catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
			return Stream.empty();// si no devolvemos un Stream vacio
		}
	}
	
	public List<String> listarContinentes(){
		return getPaises()
				.filter(p->p.getRegion()) //Steam<Pais>
				.distinct()
				.toList(); //Lista
	}
	
	public List<String> listarPaisesDeContinente(){
		
	} 
	
	public Pais paisMasPoblado() {
		return getPaises()
				.max(Comparator.comparing(p->p.getPopulation()))
				.orElse(null);
	}
	
	public Map<String, String> paisesAgrupadosPorContinente(){
		
	}
	
	public Pais paisAPartirDeUnaCapital(String capital) {
		return getPaises()
	}
}
