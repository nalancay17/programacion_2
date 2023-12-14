package parcial1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SistemaInscripcion {

	private Set<Alumno> alumnos;
	private Map<String, List<Comision>> materiasYComisiones;

	public SistemaInscripcion() {
		this.alumnos = new HashSet<Alumno>();
		this.materiasYComisiones = new HashMap<String, List<Comision>>();
	}

	public void registrarAlumno(int nroLibreta, String nombre, String apellido) throws Exception {
		Alumno alumno = new Alumno(nroLibreta, nombre, apellido);
		if (!alumnos.add(alumno))
			throw new AlumnoExistenteException("El alumno ya se encuentra registrado");
	}

	public boolean alumnoEstaRegistrado(int nroLibreta) {
		return alumnos.contains(new Alumno(nroLibreta, "nombre", "apellido"));
	}

}
