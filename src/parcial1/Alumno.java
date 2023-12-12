package parcial1;

import java.util.Objects;

public class Alumno {

	private int nroLibreta;
	private String nombre;
	private String apellido;

	public Alumno(int nroLibreta, String nombre, String apellido) {
		this.nroLibreta = nroLibreta;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getNroLibreta() {
		return nroLibreta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
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
