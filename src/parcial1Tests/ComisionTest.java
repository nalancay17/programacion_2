package parcial1Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parcial1.Alumno;
import parcial1.Comision;

public class ComisionTest {

	private Comision comision;

	@Before
	public void setUp() {
		comision = new Comision("AB123", 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void comisionCodigoVacioTest() {
		String codigo = "";
		comision.setCodigo(codigo);
	}

	@Test
	public void comisionCodigoOkTest() {
		String codigo = "AX98";
		comision.setCodigo(codigo);

		assertEquals(codigo, comision.getCodigo());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setCupoMaximoNegativoTest() {
		int cupoMaximo = -1;
		comision.setCupoMaximo(cupoMaximo);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setCupoCeroTest() {
		int cupoMaximo = 0;
		comision.setCupoMaximo(cupoMaximo);
	}

	@Test
	public void setCupoMaximoOkTest() {
		int cupoMaximo = 5;
		comision.setCupoMaximo(cupoMaximo);

		assertEquals(cupoMaximo, comision.getCupoMaximo());
	}

	@Test
	public void comisionesIgualesOkTest() {
		Comision c2 = new Comision(comision.getCodigo(), 9);
		assertTrue(comision.equals(c2));
	}

	@Test
	public void comisionesDistintasTest() {
		Comision c2 = new Comision("A999", 9);
		assertFalse(comision.equals(c2));
	}

	@Test
	public void inscribirAlumnoOkTest() {
		Alumno a1 = new Alumno(1, "nombre1", "apellido1");
		assertTrue(comision.inscribirAlumno(a1));
	}

	@Test
	public void inscribirAlumnoSinCupoTest() {
		Alumno a1 = new Alumno(1, "nombre1", "apellido1");
		Alumno a2 = new Alumno(2, "nombre2", "apellido2");
		Alumno a3 = new Alumno(3, "nombre3", "apellido3");
		comision.inscribirAlumno(a1);
		comision.inscribirAlumno(a2);

		assertFalse(comision.inscribirAlumno(a3));
	}

	@Test
	public void inscribirAlumnoYaInscriptoTest() {
		Alumno a1 = new Alumno(1, "nombre1", "apellido1");
		comision.inscribirAlumno(a1);

		assertFalse(comision.inscribirAlumno(a1));
	}

	@Test
	public void tieneCupoDisponibleSinAlumnosTest() {
		assertTrue(comision.tieneCupoDisponible());
	}

	@Test
	public void tieneCupoDisponibleConAlumnosTest() {
		Alumno a1 = new Alumno(1, "nombre1", "apellido1");
		comision.inscribirAlumno(a1);

		assertTrue(comision.tieneCupoDisponible());
	}

	@Test
	public void noTieneCupoDisponibleConAlumnosTest() {
		Alumno a1 = new Alumno(1, "nombre1", "apellido1");
		Alumno a2 = new Alumno(2, "nombre2", "apellido2");
		comision.inscribirAlumno(a1);
		comision.inscribirAlumno(a2);

		assertFalse(comision.tieneCupoDisponible());
	}

	@Test
	public void contieneAlumnoOkTest() {
		Alumno a1 = new Alumno(1, "nombre1", "apellido1");
		Alumno a2 = new Alumno(2, "nombre2", "apellido2");
		comision.inscribirAlumno(a1);
		comision.inscribirAlumno(a2);

		assertTrue(comision.contieneAlumno(a1));
		assertTrue(comision.contieneAlumno(a2));
	}

	@Test
	public void noContieneAlumnoTest() {
		Alumno a1 = new Alumno(1, "nombre1", "apellido1");
		Alumno a2 = new Alumno(2, "nombre2", "apellido2");
		comision.inscribirAlumno(a1);

		assertFalse(comision.contieneAlumno(a2));
	}

}
