package modelo;

public class Compra {
	
	private int id;
	private int pedido_id;
	private int producto_id;
	private int usuario_id;
	private int unidades;
	private double precioUnidad;
	private double total;
	
	public Compra() {
		
	}
	
	public Compra( int pedido_id, int producto_id, int usuario_id, int unidades, double precioUnidad) {

		this.pedido_id = pedido_id;
		this.producto_id = producto_id;
		this.usuario_id = usuario_id;
		this.unidades = unidades;
		this.precioUnidad = precioUnidad;
		this.total = precioUnidad * unidades;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPedido_id() {
		return pedido_id;
	}
	public void setPedido_id(int pedido_id) {
		this.pedido_id = pedido_id;
	}
	public int getProducto_id() {
		return producto_id;
	}
	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public double getPrecioUnidad() {
		return precioUnidad;
	}
	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
