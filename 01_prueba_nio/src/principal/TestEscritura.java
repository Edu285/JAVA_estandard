package principal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TestEscritura {
/////comentario
	public static void main(String[] args) {
		String ruta="nombres.txt";
		Path pt=Path.of(ruta);
		try {
			/*if(Files.notExists(pt)) {
				Files.createFile(pt);
			}*//// NO HACE FALTA SI EN EL PRIMERO AGREGAMOS StandardOpenOption.CREATE
			// StandardOpenOption.APPEND con esto añade si no lo ponemos se sobre escribe y machaca lo anterior
			// +System.lineSeparator() para hacer un \n mejor que ponerlo para que funcione en otros sistemas
			Files.writeString(pt, "cadena1"+System.lineSeparator(),StandardOpenOption.APPEND,StandardOpenOption.CREATE); 
			Files.writeString(pt, "cadena2"+System.lineSeparator(),StandardOpenOption.APPEND);
			Files.writeString(pt, "cadena3"+System.lineSeparator(),StandardOpenOption.APPEND);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
