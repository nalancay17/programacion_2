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

	public void inscribirAlumnoEnComision(int nroLibreta, String nombreMateria, String codigoComision) throws Exception {
		if (!alumnoEstaRegistrado(nroLibreta))
			throw new AlumnoNoRegistradoException("El alumno debe estar registrado para inscribirlo en una comisión");

		int indiceAlumno = alumnos.indexOf(new Alumno(nroLibreta, "nombre", "apellido"));
		Alumno alumno = alumnos.get(indiceAlumno);
		Comision comision = obtenerComisionDeMateria(nombreMateria, codigoComision);

		if (!comision.tieneCupoDisponible())
			throw new ComisionSinCupoDisponibleException("La comisión no tiene cupo disponible");
		if (comision.contieneAlumno(alumno))
			throw new AlumnoYaInscriptoEnComisionException("El alumno ya se encuentra inscripto en la comisión");
		comision.inscribirAlumno(alumno);
	}

	public boolean alumnoEstaInscriptoEnComision(int nroLibreta, String materia, String codigoComision) throws Exception {
		Comision comision = obtenerComisionDeMateria(materia, codigoComision);
		boolean existeAlumno = comision.contieneAlumno(new Alumno (nroLibreta, "nombre", "apellido"));
		return existeAlumno;
	}

	public List<String> codigosComisionesConCupoDisponible(String materia) throws Exception {
		if (!existeMateria(materia))
			throw new MateriaInexistenteException("La materia no existe");
		List<String> codigos = new ArrayList<String>();

		materiasYComisiones.get(materia).forEach(comision -> {
			if (comision.tieneCupoDisponible())
				codigos.add(comision.getCodigo()); 
		});
		return codigos;
	}

	private Comision obtenerComisionDeMateria(String materia, String codigoComision) throws Exception {
		if (!materiasYComisiones.containsKey(materia))
			throw new MateriaInexistenteException("La materia no existe");
		List<Comision> comisiones = materiasYComisiones.get(materia);
		int indiceComision = comisiones.indexOf(new Comision(codigoComision, 1));
		if (indiceComision == -1)
			throw new ComisionInexistenteException("La comisión indicada no existe");
		return comisiones.get(indiceComision);
	}

}
