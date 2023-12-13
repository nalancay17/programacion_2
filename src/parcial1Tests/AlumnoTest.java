package parcial1Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import parcial1.Alumno;

public class AlumnoTest {

	private Alumno alumno;

	@Before
	public void setUp() {
		alumno = new Alumno(1, "nombre", "apellido");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nroLibretaNegativoTest() {
		int nroLibreta = -1;
		alumno.setNroLibreta(nroLibreta);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nroLibretaCeroTest() {
		int nroLibreta = 0;
		alumno.setNroLibreta(nroLibreta);
	}

	@Test
	public void nroLibretaOkTest() {
		int nroLibreta = 20;
		alumno.setNroLibreta(nroLibreta);

		assertEquals(nroLibreta, alumno.getNroLibreta());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nombreDeMenos3CaracteresTest() {
		String nombre = "ab";
		alumno.setNombre(nombre);
	}

	@Test
	public void nombreDe3CaracteresOkTest() {
		String nombre = "abc";
		alumno.setNombre(nombre);

		assertEquals(nombre, alumno.getNombre());
	}

	@Test(expected = IllegalArgumentException.class)
	public void apellidoDeMenos3CaracteresTest() {
		String apellido = "ab";
		alumno.setApellido(apellido);
	}

	@Test
	public void apellidoDe3CaracteresOkTest() {
		String apellido = "abc";
		alumno.setApellido(apellido);

		assertEquals(apellido, alumno.getApellido());
	}

	@Test
	public void alumnosIgualesTest() {
		Alumno a2 = new Alumno(alumno.getNroLibreta(), "nombre2", "nombre2");
		assertTrue(alumno.equals(a2));
	}

	@Test
	public void alumnosDistintosTest() {
		Alumno a2 = new Alumno(99, alumno.getNombre(), alumno.getApellido());
		assertFalse(alumno.equals(a2));
	}

}
