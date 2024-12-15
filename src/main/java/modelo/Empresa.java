package modelo;

public class Empresa {

	private String nombre;
	private String cif;
	private String direccion;
	private String ciudad;
	private String telefono;
	
	public Empresa () {
		
	}
	
	public Empresa(String nombre, String cif, String direccion, String ciudad, String telefono) {
		this.nombre = nombre;
		this.cif = cif;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
