package parcial1Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parcial1.AlumnoExistenteException;
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

}
