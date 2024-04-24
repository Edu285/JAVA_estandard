// Realizar una nueva versión del programa de las notas. Al iniciar el programa
// aparecerá el siguiente menú:
// 1.- Nueva nota
// 2.- Calcular media
// 3.- Notas extremas
// 4.- Ver todas
// 5.- Salir 
/////////////////// LOGICA DE NEGOCIO ////////////////////////////////////////
package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Exceptions.ErrorFuenteDatosException;


public class NotasService {
	Path pt=Path.of("notas.txt");
	public void borrarNotas() {  // para borrar el fichero
		try {
			Files.deleteIfExists(pt);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void agrergarNota(double nota) {
		try {
			Files.writeString(pt,nota+System.lineSeparator(),StandardOpenOption.APPEND,StandardOpenOption.CREATE);
			System.out.println("Nota guardada en el fichero.");
		}
		catch(IOException ex) { 
			ex.printStackTrace();
		}
	}
	
	public double calcularMedia() throws ErrorFuenteDatosException{//propagamos la excepcion
		/*double media=0;
		int contador=0;
		String linea;
		try(FileReader fr=new FileReader(fichero);
				BufferedReader bf=new BufferedReader(fr);){
			while((linea=bf.readLine())!=null) {
				contador++;
				media+=Double.parseDouble(linea);
			}
		}
		catch(IOException ex) { // excepcion de FileOutputStream
			ex.printStackTrace();
		}
		return contador>0?media/contador:0;  // operador ternario por si esta vacio el fichero devuleve 0*/
		try {
			return Files.lines(pt) //Stream(String)
					.collect(Collectors.averagingDouble(s->Double.parseDouble(s)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorFuenteDatosException();// provocamos la excepcion
		}
	}
	
	public double max() throws ErrorFuenteDatosException{
		/*double max=0;
		String linea;
		try(FileReader fr=new FileReader(fichero);
				BufferedReader bf=new BufferedReader(fr);){
			while((linea=bf.readLine())!=null) {
				if(Double.parseDouble(linea)>max) {
					max=Double.parseDouble(linea);
				}
			}
		}
		catch(IOException ex) { // excepcion de FileOutputStream
			ex.printStackTrace();
		}
		return max;*/
		try {
			return Files.lines(pt) //Stream<String>
					.max(Comparator.comparingDouble(s->Double.parseDouble(s))) //Optional<String>
					.map(s->Double.parseDouble(s))//Optional<Double>
					.orElse(0.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorFuenteDatosException();// provocamos la excepcion
		}
	}
	
	public double min() throws ErrorFuenteDatosException{
		/*double min=Double.MAX_VALUE;// PARA INICIALIZARLO AL MAYOR VALOR POSIBLE
		String linea;
		try(FileReader fr=new FileReader(fichero);
				BufferedReader bf=new BufferedReader(fr);){
			while((linea=bf.readLine())!=null) {
				if(min>Double.parseDouble(linea)) {
					min=Double.parseDouble(linea);
				}
			}
		}
		catch(IOException ex) { // excepcion de FileOutputStream
			ex.printStackTrace();
		}
		return min;*/
		try {
			return Files.lines(pt) //Stream<String>
					.min(Comparator.comparingDouble(s->Double.parseDouble(s))) //Optional<String>
					.map(s->Double.parseDouble(s))//Optional<Double>
					.orElse(0.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorFuenteDatosException();// provocamos la excepcion
		}
	}
	
	public List<Double> obtenerNotas() throws ErrorFuenteDatosException{
		/*ArrayList<Double> aux= new ArrayList<>();
		String linea;
		try(FileReader fr=new FileReader(fichero);
				BufferedReader bf=new BufferedReader(fr);){
			while((linea=bf.readLine())!=null) {
				aux.add(Double.parseDouble(linea));
			}
		}
		catch(IOException ex) { // excepcion de FileOutputStream
			ex.printStackTrace();
		}
		return aux;*/
		
		try {
			return Files.lines(pt) //Stream<String>
					.map(s->Double.parseDouble(s))//Stream<Double>
					.toList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorFuenteDatosException();// provocamos la excepcion
		}		
	}
}
