package view;

import java.util.Arrays;
import java.util.Scanner;

import Exceptions.ErrorFuenteDatosException;
import service.NotasService;

public class NotasMenu {
	static NotasService service = new NotasService();
	public static void main(String[] args) {
		int opcion=9;
		Scanner sc = new Scanner (System.in);
		do {
			presentarMenu();
			opcion=sc.nextInt();
			switch(opcion) {
			case 1:
				agregarNota();
				break;
			case 2:
				presentarMedia();
				break;
			case 3:
				presentarExtremas();
				break;
			case 4:
				mostrarNotas();
				break;
			case 5:
				System.out.println("Gracias por usar este programa!!!");
				System.out.println("Que pases un gran dia!!!");
				break;
			default:
				System.out.println("Opcion no valida!");
			}
		}while(opcion!=5);
	}

	static void presentarMenu() {
		System.out.println("++++++++++++++  MENU   +++++++++++++");
		System.out.println("+    1.- NUEVA NOTA                +");
		System.out.println("+    2.- CALCULAR MEDIA            +");
		System.out.println("+    3.- MOSTRAR NOTAS EXTREMAS    +");		
		System.out.println("+    4.- MOSTRAR TODAS             +");		
		System.out.println("+    5.- SALIR                     +");
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println(" Introduce una de las opciones(1-5):");
	}
	
	static void agregarNota() {
		Scanner e = new Scanner(System.in);
		double nota;
		System.out.println("Introduce una Nota:");
		nota=e.nextDouble();
		service.agrergarNota(nota);
	}
	
	static void presentarMedia() {
		try {
			System.out.println("La nota media es:" + service.calcularMedia());
		} catch (ErrorFuenteDatosException e) {
			System.out.println("Hubo un ERROR al leer las notas!"); //mensaje personalizado
			System.out.println(e.getMessage()); // mensaje de la excepcion
		}
	}
	
	static void presentarExtremas() {
		try {
			System.out.println("La nota mas Alta es:" + service.max());
		} catch (ErrorFuenteDatosException e) {
			System.out.println("Hubo un ERROR al leer las notas!");
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("La noma mas Baja es:" + service.min());
		} catch (ErrorFuenteDatosException e) {
			System.out.println("Hubo un ERROR al leer las notas!");
			System.out.println(e.getMessage());
		}
	}
	
	static void mostrarNotas() {
		/////////// forma tradicional //////////////
		/*Double[] notas = service.obtenerNotas();
		System.out.println("TOTAL DE NOTAS:");
		for(Double n: notas) {
			System.out.println(n);
		}*/
		////////////// otra forma ////////////////////
		System.out.println("TOTAL DE NOTAS:");
		try {
			for(Double n: service.obtenerNotas()){
				 System.out.println(n);
			}
		} catch (ErrorFuenteDatosException e) {
			System.out.println("Hubo un ERROR al leer las notas!");
			System.out.println(e.getMessage());
		}
		//System.out.println("TOTAL DE NOTAS:" + Array.toString(service.obtenerNotas()));	
	}
}

