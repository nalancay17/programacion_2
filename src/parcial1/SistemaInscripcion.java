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
		verificarAlumnoYaExiste(nroLibreta);
		alumnos.add(alumno);
	}

	public void crearMateria(String nombre) throws Exception {
		verificarNombreMateria(nombre);
		verificarMateriaYaExiste(nombre);

		List<Comision> comisiones = null;
		if (materiasYComisiones.get(nombre) == null)
			comisiones = new ArrayList<Comision>();
		materiasYComisiones.put(nombre, comisiones);
	}

	public void crearComision(String materia, String codigo, int cupoMaximo) throws Exception {
		verificarMateriaNoExiste(materia);

		Comision comision = new Comision(codigo, cupoMaximo);
		if (materiasYComisiones.get(materia).contains(comision))
			throw new ComisionYaExistenteException("La comisión ya existe");
		List<Comision> comisiones = materiasYComisiones.get(materia);
		comisiones.add(comision);
	}

	public void inscribirAlumnoEnComision(int nroLibreta, String nombreMateria, String codigoComision) throws Exception {
		verificarAlumnoNoRegistrado(nroLibreta);
		verificarAlumnoInscriptoEnMateria(nroLibreta, nombreMateria);

		int indiceAlumno = alumnos.indexOf(new Alumno(nroLibreta, "nombre", "apellido"));
		Alumno alumno = alumnos.get(indiceAlumno);
		Comision comision = obtenerComisionDeMateria(nombreMateria, codigoComision);

		if (!comision.tieneCupoDisponible())
			throw new ComisionSinCupoDisponibleException("La comisión no tiene cupo disponible");
		comision.inscribirAlumno(alumno);
	}

	public boolean alumnoEstaInscriptoEnComision(int nroLibreta, String materia, String codigoComision) throws Exception {
		Comision comision = obtenerComisionDeMateria(materia, codigoComision);
		boolean existeAlumno = comision.contieneAlumno(new Alumno (nroLibreta, "nombre", "apellido"));
		return existeAlumno;
	}

	public List<String> codigosComisionesConCupoDisponible(String materia) throws Exception {
		verificarMateriaNoExiste(materia);

		List<String> codigos = new ArrayList<String>();
		materiasYComisiones.get(materia).forEach(comision -> {
			if (comision.tieneCupoDisponible())
				codigos.add(comision.getCodigo()); 
		});
		return codigos;
	}

	public boolean alumnoEstaRegistrado(Alumno alumno) {
		return alumnos.contains(alumno);
	}

	public boolean existeMateria(String nombre) {
		return materiasYComisiones.containsKey(nombre);
	}

	private Comision obtenerComisionDeMateria(String materia, String codigoComision) throws Exception {
		verificarMateriaNoExiste(materia);

		List<Comision> comisiones = materiasYComisiones.get(materia);
		int indiceComision = comisiones.indexOf(new Comision(codigoComision, 1));
		if (indiceComision == -1)
			throw new ComisionInexistenteException("La comisión indicada no existe");
		return comisiones.get(indiceComision);
	}

	private boolean alumnoEstaInscriptoEnMateria(int nroLibreta, String materia) throws Exception {
		verificarAlumnoNoRegistrado(nroLibreta);
		verificarMateriaNoExiste(materia);

		Alumno alumno = new Alumno(nroLibreta, "nombre", "apellido");
		for (Comision comision : materiasYComisiones.get(materia)) {
			if (comision.contieneAlumno(alumno))
				return true;
		}
		return false;
	}

	private void verificarAlumnoNoRegistrado(int nroLibreta) throws AlumnoNoRegistradoException {
		if (!alumnoEstaRegistrado(new Alumno(nroLibreta, "nombre", "apellido")))
			throw new AlumnoNoRegistradoException("El alumno debe estar registrado");
	}

	private void verificarAlumnoYaExiste(int nroLibreta) throws AlumnoExistenteException {
		if (alumnoEstaRegistrado(new Alumno(nroLibreta, "nombre", "apellido")))
			throw new AlumnoExistenteException("El alumno ya se encuentra registrado");
	}

	private void verificarMateriaNoExiste(String materia) throws MateriaInexistenteException {
		if (!existeMateria(materia))
			throw new MateriaInexistenteException("La materia no existe");
	}

	private void verificarMateriaYaExiste(String materia) throws MateriaYaExistenteException {
		if (existeMateria(materia))
			throw new MateriaYaExistenteException("La materia ya existe");
	}

	private void verificarAlumnoInscriptoEnMateria(int nroLibreta, String materia) throws AlumnoYaInscriptoEnMateriaException, Exception {
		if (alumnoEstaInscriptoEnMateria(nroLibreta, materia))
			throw new AlumnoYaInscriptoEnMateriaException("El alumno ya se encuentra inscripto en esta materia");
	}

	private void verificarNombreMateria(String nombre) {
		if (nombre.length() < 3)
			throw new IllegalArgumentException("El nombre de la materia debe tener al menos 3 caracteres");
	}

}
