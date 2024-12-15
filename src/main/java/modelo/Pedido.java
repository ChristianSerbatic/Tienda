package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pedido {
	private int id;
	private int usuario_id;
	private String fecha;
	private String 	metodopago;
	private String numfactura;
	private double total;
	private ArrayList<Detalle> listaDetalles;
	
	public Pedido() {
		
	}
	
	public Pedido (ArrayList<Detalle> listaDetalles) {
		this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
		this.listaDetalles = listaDetalles;
		this.total = calcularTotal(listaDetalles);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getMetodopago() {
		return metodopago;
	}
	public void setMetodopago(String metodopago) {
		this.metodopago = metodopago;
	}
	public String getNumfactura() {
		return numfactura;
	}
	public void setNumfactura(String numfactura) {
		this.numfactura = numfactura;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public ArrayList<Detalle> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(ArrayList<Detalle> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}	
	
	public double calcularTotal(ArrayList<Detalle> listaDetalles) {
		double total = 0;
		for(Detalle detalle : listaDetalles){
			total += detalle.getTotal();}
		return total;
	}
}

