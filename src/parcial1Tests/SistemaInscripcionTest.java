package parcial1Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parcial1.AlumnoExistenteException;
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

}
