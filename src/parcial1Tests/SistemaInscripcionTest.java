package parcial1Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parcial1.AlumnoExistenteException;
import parcial1.AlumnoNoRegistradoException;
import parcial1.AlumnoYaInscriptoEnComisionException;
import parcial1.ComisionInexistenteException;
import parcial1.ComisionSinCupoDisponibleException;
import parcial1.ComisionYaExistenteException;
import parcial1.MateriaInexistenteException;
import parcial1.MateriaYaExistenteException;
import parcial1.SistemaInscripcion;

public class SistemaInscripcionTest {

	private SistemaInscripcion sistema;

	@Before
	public void setUp() {
		sistema = new SistemaInscripcion();
	}

	@Test
	public void alumnoNoEstaRegistradoTest() throws Exception {
		int nroLibreta = 1;

		assertFalse(sistema.alumnoEstaRegistrado(nroLibreta));
	}

	@Test
	public void registrarAlumnoOkTest() throws Exception {
		int nroLibreta = 1;

		sistema.registrarAlumno(nroLibreta, "Carlos", "Fuentes");
		assertTrue(sistema.alumnoEstaRegistrado(nroLibreta));
	}

	@Test(expected = AlumnoExistenteException.class)
	public void registrarAlumnoExistenteTest() throws Exception {
		int nroLibreta = 1;

		sistema.registrarAlumno(nroLibreta, "Carlos", "Fuentes");
		sistema.registrarAlumno(nroLibreta, "Carlos", "Fuentes");
	}

	public void crearMateriaOkTest() throws Exception {
		String materia = "Bases de Datos II";
		sistema.crearMateria(materia);
	
		assertTrue(sistema.existeMateria(materia));
	}

	@Test(expected = IllegalArgumentException.class)
	public void crearMateriaMenos3CaracteresTest() throws Exception {
		String materia = "ab";

		sistema.crearMateria(materia);
	}

	@Test(expected = MateriaYaExistenteException.class)
	public void crearMateriaYaExistenteTest() throws Exception {
		String materia = "Bases de Datos II";

		sistema.crearMateria(materia);
		sistema.crearMateria(materia);
	}

	@Test
	public void crearComisionOkTest() throws Exception {
		String materia = "Bases de Datos II";
		String codigoComision = "abc1";
		int cupoMaximo = 1;

		sistema.crearMateria(materia);
		sistema.crearComision(materia, codigoComision, cupoMaximo);
	}

	@Test(expected = MateriaInexistenteException.class)
	public void crearComisionMateriaNoExistenteTest() throws Exception {
		String materia = "Bases de Datos II";
		String codigoComision = "abc";
		int cupoMaximo = 1;

		sistema.crearComision(materia, codigoComision, cupoMaximo);
	}

	@Test(expected = ComisionYaExistenteException.class)
	public void crearComisionExistenteTest() throws Exception {
		String materia = "Bases de Datos II";
		String codigoComision = "abc";
		int cupoMaximo = 1;
		sistema.crearMateria(materia);

		sistema.crearComision(materia, codigoComision, cupoMaximo);
		sistema.crearComision(materia, codigoComision, cupoMaximo);
	}

	@Test(expected = AlumnoNoRegistradoException.class)
	public void inscribirAlumnoEnComisionNoRegistradoTest() throws Exception {
		int nroLibreta = 1;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";

		sistema.inscribirAlumnoEnComision(nroLibreta, materia, codigoComision);
	}

	@Test(expected = MateriaInexistenteException.class)
	public void inscribirAlumnoEnComisionMateriaNoExistenteTest() throws Exception {
		int nroLibreta = 1;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";
		sistema.registrarAlumno(nroLibreta, "nombre", "apellido");

		sistema.inscribirAlumnoEnComision(nroLibreta, materia, codigoComision);
	}

	@Test(expected = ComisionInexistenteException.class)
	public void inscribirAlumnoEnComisionNoExistenteTest() throws Exception {
		int nroLibreta = 1;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";
		sistema.registrarAlumno(nroLibreta, "nombre", "apellido");
		sistema.crearMateria(materia);

		sistema.inscribirAlumnoEnComision(nroLibreta, materia, codigoComision);
	}

	@Test(expected = ComisionSinCupoDisponibleException.class)
	public void inscribirAlumnoEnComisionSinCupoDisponibleTest() throws Exception {
		int nroLibreta1 = 1;
		int nroLibreta2 = 2;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";
		int cupoMaximo = 1;
		sistema.registrarAlumno(nroLibreta1, "nombre1", "apellido1");
		sistema.registrarAlumno(nroLibreta2, "nombre2", "apellido2");
		sistema.crearMateria(materia);
		sistema.crearComision(materia, codigoComision, cupoMaximo);

		sistema.inscribirAlumnoEnComision(nroLibreta1, materia, codigoComision);
		sistema.inscribirAlumnoEnComision(nroLibreta2, materia, codigoComision);
	}

	@Test(expected = AlumnoYaInscriptoEnComisionException.class)
	public void inscribirAlumnoEnComisionYaInscriptoEnComisionTest() throws Exception {
		int nroLibreta1 = 1;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";
		int cupoMaximo = 2;
		sistema.registrarAlumno(nroLibreta1, "nombre1", "apellido1");
		sistema.crearMateria(materia);
		sistema.crearComision(materia, codigoComision, cupoMaximo);

		sistema.inscribirAlumnoEnComision(nroLibreta1, materia, codigoComision);
		sistema.inscribirAlumnoEnComision(nroLibreta1, materia, codigoComision);
	}

	@Test
	public void inscribirAlumnoEnComisionOkTest() throws Exception {
		int nroLibreta = 1;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";
		int cupoMaximo = 1;
		sistema.registrarAlumno(nroLibreta, "nombre", "apellido");
		sistema.crearMateria(materia);
		sistema.crearComision(materia, codigoComision, cupoMaximo);

		sistema.inscribirAlumnoEnComision(nroLibreta, materia, codigoComision);
		assertTrue(sistema.alumnoEstaInscriptoEnComision(nroLibreta, materia, codigoComision));
	}

	@Test(expected = MateriaInexistenteException.class)
	public void alumnoEstaInscriptoEnComisionMateriaInexistenteTest() throws Exception {
		int nroLibreta = 1;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";
		sistema.registrarAlumno(nroLibreta, "nombre", "apellido");

		sistema.inscribirAlumnoEnComision(nroLibreta, materia, codigoComision);
	}

	@Test(expected = ComisionInexistenteException.class)
	public void alumnoEstaInscriptoEnComisionInexistenteTest() throws Exception {
		int nroLibreta = 1;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";
		sistema.registrarAlumno(nroLibreta, "nombre", "apellido");
		sistema.crearMateria(materia);

		sistema.inscribirAlumnoEnComision(nroLibreta, materia, codigoComision);
	}

	@Test
	public void alumnoNoEstaInscriptoEnComisionTest() throws Exception {
		int nroLibreta = 1;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";
		int cupoMaximo = 1;
		sistema.registrarAlumno(nroLibreta, "nombre", "apellido");
		sistema.crearMateria(materia);
		sistema.crearComision(materia, codigoComision, cupoMaximo);

		assertFalse(sistema.alumnoEstaInscriptoEnComision(nroLibreta, materia, codigoComision));
	}

	@Test
	public void alumnoEstaInscriptoEnComisionOkTest() throws Exception {
		int nroLibreta = 1;
		String materia = "Bases de Datos 2";
		String codigoComision = "abc";
		int cupoMaximo = 1;
		sistema.registrarAlumno(nroLibreta, "nombre", "apellido");
		sistema.crearMateria(materia);
		sistema.crearComision(materia, codigoComision, cupoMaximo);

		sistema.inscribirAlumnoEnComision(nroLibreta, materia, codigoComision);
		assertTrue(sistema.alumnoEstaInscriptoEnComision(nroLibreta, materia, codigoComision));
	}

}
