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

	public void crearMateria(String nombre) throws Exception {
		if (nombre.length() < 3)
			throw new IllegalArgumentException("El nombre de la materia debe tener al menos 3 caracteres");
		if (existeMateria(nombre))
			throw new MateriaYaExistenteException("La materia ya existe");
		List<Comision> comisiones = null;
		if (materiasYComisiones.get(nombre) == null)
			comisiones = new ArrayList<Comision>();
		materiasYComisiones.put(nombre, comisiones);
	}

	public boolean existeMateria(String nombre) {
		return materiasYComisiones.containsKey(nombre);
	}

	public void crearComision(String materia, String codigo, int cupoMaximo) throws Exception {
		Comision comision = new Comision(codigo, cupoMaximo);
		if (!existeMateria(materia))
			throw new MateriaInexistenteException("La materia no existe");
		if (materiasYComisiones.get(materia).contains(comision))
			throw new ComisionYaExistenteException("La comisión ya existe");
		List<Comision> comisiones = materiasYComisiones.get(materia);
		comisiones.add(comision);
	}

}
