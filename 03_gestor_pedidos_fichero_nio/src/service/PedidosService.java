package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Pedido;
import utilidades.Util;

public class PedidosService {
	Path pt=Path.of("pedidos.csv");
	//cada pedido se graba en una línea:
	//producto,unidades,fechaPedido
	public void borrarPedidos() {
		try {
			Files.deleteIfExists(pt);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	public void nuevoPedido(Pedido pedido) {
		try {
			Files.writeString(pt,Util.convertirPedidoACadena(pedido)+System.lineSeparator(),
					StandardOpenOption.APPEND,StandardOpenOption.CREATE);
		}
		catch(IOException ex) { 
			ex.printStackTrace();
		}
	}
		
	public Pedido pedidoMasReciente() {
		try {
			return Files.lines(pt) // Stream<String>
					.map(p->Util.convertirCadenaAPedido(p)) // Stream<Pedido>//.map(Util::convertirCadenaPedido)
					.max(Comparator.comparing(p->p.getFechaPedido()))
					.orElse (null);
		}catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}			
	}
	
	public List<Pedido> pedidosEntreFechas(LocalDate f1, LocalDate f2) {
		try {
			return Files.lines(pt) //Stream<String>
					.map(p->Util.convertirCadenaAPedido(p)) // Stream<Pedido>
					.filter(p->p.getFechaPedido().compareTo(f1)>=0 && p.getFechaPedido().compareTo(f2)<=0) //Steam<Pedido>
					.toList(); // devuelve la lista de pedidos entre fechas
					
		} catch (IOException e) {
			e.printStackTrace();
			return List.of(); // lista vacia
		}
	}
	
	public Pedido pedidoProximoFecha(LocalDate fecha) {
		try {
			return Files.lines(pt) //Stream<String>
					.map(p->Util.convertirCadenaAPedido(p)) // Stream<Pedido>
					.min(Comparator.comparingLong(p->Math.abs(ChronoUnit.DAYS.between(p.getFechaPedido(), fecha)))) //Stream<Pedido>
					.orElse (null);	
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void eliminarPedido(String producto) {
		try {
			List<String> pedidos= Files.lines(pt)
			.map(p->Util.convertirCadenaAPedido(p)) // Stream<Pedido> .map(Util::convertirCadenaAPedido)
			.filter(p->!p.getProducto().equals(producto)) // Stream <Pedido>
			.map(p->Util.convertirPedidoACadena(p)) // Stream<String> .map(Util::convertirPedidoACadena)
            .toList();
            
			Files.write(pt, pedidos);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Pedido> pedidos(){
		try {
			return Files.lines(pt) //Stream<String>
					.map(p->Util.convertirCadenaAPedido(p)) // Stream<Pedido>
					.toList(); // lista de Pedido
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
	}
}
