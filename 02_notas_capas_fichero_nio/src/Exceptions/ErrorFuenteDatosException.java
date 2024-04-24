package Exceptions;
/////////CREAMOS NUESTRA PROPIA EXCEPCION
public class ErrorFuenteDatosException extends RuntimeException {//

	@Override
	public String getMessage() {
		return "No se ha podido acceder al fichero donde estan las notas.";
	}

}
