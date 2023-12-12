package parcial1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Comision {

	private String codigo;
	private int cupoMaximo;
	private Set<Alumno> alumnos;

	public Comision(String codigo, int cupoMaximo) {
		setCodigo(codigo);
		setCupoMaximo(cupoMaximo);
		this.alumnos = new HashSet<Alumno>();
	}

	public boolean inscribirAlumno(Alumno alumno) {
		if (!tieneCupoDisponible())
			return false;
		return alumnos.add(alumno);
	}

	public boolean contieneAlumno(Alumno alumno) {
		return alumnos.contains(alumno);
	}

	public boolean tieneCupoDisponible() {
		return alumnos.size() < cupoMaximo;
	}

	private void setCodigo(String codigo) {
		if (codigo.equals(""))
			throw new IllegalArgumentException("El código no puede estar vacío");
		this.codigo = codigo;
	}

	private void setCupoMaximo(int cupoMaximo) {
		if (cupoMaximo < 0)
			throw new IllegalArgumentException("El cupo no puede ser menor a 0");
		this.cupoMaximo = cupoMaximo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comision other = (Comision) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
