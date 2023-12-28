package acumuladoresBooleanosTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import acumuladoresBooleanos.AcumuladoresEjs;

public class AcumuladoresEjsTest {

	@Test
	public void mayor10ListaVaciaTest() {
		int[] lista = {};
		boolean resultado = AcumuladoresEjs.mayor10(lista);
		assertFalse(resultado);
	}

	@Test
	public void mayor10ListaConElemsNoMayores10Test() {
		int[] lista = {10, 22};
		boolean resultado = AcumuladoresEjs.mayor10(lista);
		assertFalse(resultado);
	}

	@Test
	public void mayor10ListaConElemsMayores10Test() {
		int[] lista = {11, 22, 101};
		boolean resultado = AcumuladoresEjs.mayor10(lista);
		assertTrue(resultado);
	}

	@Test
	public void pertenecenTodosElemsVacioTest() {
		int[] elems = {};
		int[] arreglo = {11, 22, 101};

		boolean resultado = AcumuladoresEjs.pertenecenTodos(elems, arreglo);

		assertTrue(resultado);
	}

	@Test
	public void pertenecenTodosArregloVacioTest() {
		int[] elems = {11, 22, 101};
		int[] arreglo = {};

		boolean resultado = AcumuladoresEjs.pertenecenTodos(elems, arreglo);

		assertFalse(resultado);
	}

	@Test
	public void pertenecenTodosFalsoTest() {
		int[] elems = {11, 22, 101};
		int[] arreglo = {11, 22, 9098, 63};

		boolean resultado = AcumuladoresEjs.pertenecenTodos(elems, arreglo);

		assertFalse(resultado);
	}

	@Test
	public void pertenecenTodosOkTest() {
		int[] elems = {11, 22, 101};
		int[] arreglo = {11, 22, 9098, 63, 101};

		boolean resultado = AcumuladoresEjs.pertenecenTodos(elems, arreglo);

		assertTrue(resultado);
	}

	@Test
	public void tieneNegativosMatrizVaciaTest() {
		int[][] mat = {};

		boolean resultado = AcumuladoresEjs.tieneNegativos(mat);

		assertFalse(resultado);
	}

	@Test
	public void tieneNegativosMatrizSinNegativosTest() {
		int[][] mat = {{1, 2, 3}, {9, 0, 10}};

		boolean resultado = AcumuladoresEjs.tieneNegativos(mat);

		assertFalse(resultado);
	}

	@Test
	public void tieneNegativosSoloUnaColumnaConNegativoTest() {
		int[][] mat = {{1, 2, 3}, {9, 0, -10}};

		boolean resultado = AcumuladoresEjs.tieneNegativos(mat);

		assertFalse(resultado);
	}

	@Test
	public void tieneNegativosOkTest() {
		int[][] mat = {{1, -2, 3}, {9, 0, -10}};

		boolean resultado = AcumuladoresEjs.tieneNegativos(mat);

		assertTrue(resultado);
	}

}
