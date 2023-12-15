package parcial1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaInscripcion {

	private List<Alumno> alumnos;
	private Map<String, List<Comision>> materiasYComisiones;

	public SistemaInscripcion() {
		this.alumnos = new ArrayList<Alumno>();
		this.materiasYComisiones = new HashMap<String, List<Comision>>();
	}

	public void registrarAlumno(int nroLibreta, String nombre, String apellido) throws Exception {
		Alumno alumno = new Alumno(nroLibreta, nombre, apellido);
		if (alumnoEstaRegistrado(nroLibreta))
			throw new AlumnoExistenteException("El alumno ya se encuentra registrado");
		alumnos.add(alumno);
	}

	public boolean alumnoEstaRegistrado(int nroLibreta) {
		return alumnos.contains(new Alumno(nroLibreta, "nombre", "apellido"));
	}

}
