package parcial1;

import java.util.Objects;

public class Alumno {

	private int nroLibreta;
	private String nombre;
	private String apellido;

	public Alumno(int nroLibreta, String nombre, String apellido) {
		setNroLibreta(nroLibreta);
		setNombre(nombre);
		setApellido(apellido);
	}

	public int getNroLibreta() {
		return nroLibreta;
	}

	public void setNroLibreta(int nroLibreta) {
		if (nroLibreta <= 0)
			throw new IllegalArgumentException("El númeor de libreta debe ser positivo");
		this.nroLibreta = nroLibreta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre.length() < 3)
			throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres");
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		if (apellido.length() < 3)
			throw new IllegalArgumentException("El apellido debe tener al menos 3 caracteres");
		this.apellido = apellido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nroLibreta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return nroLibreta == other.nroLibreta;
	}

}
