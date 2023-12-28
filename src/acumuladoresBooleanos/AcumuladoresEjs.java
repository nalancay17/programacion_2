package acumuladoresBooleanos;

public class AcumuladoresEjs {

	public static boolean mayor10(int[] lista) {
		if (lista.length == 0)
			return false;
		boolean ret = true;
		for (int i = 0; i < lista.length; i ++) {
			ret = ret && lista[i] > 10;
		}
		return ret;
	}

	public static boolean pertenecenTodos(int[] elems, int[] arreglo) {
		boolean ret = true;
		for (int i = 0; i < elems.length; i++) {
			ret = ret && contieneElem(elems[i], arreglo);
		}
		return ret;
	}

	public static boolean tieneNegativos(int[][] mat) {
		boolean ret = true;
		ret = ret && mat.length != 0;
		for (int i = 0; i < mat.length; i++) {
			ret = ret && arregloContieneNegativo(mat[i]);
		}
		return ret;
	}

	private static boolean contieneElem(int elem, int[] arreglo) {
		boolean ret = false;
		for (int i = 0; i < arreglo.length; i++) {
			ret = ret || arreglo[i] == elem;
		}
		return ret;
	}

	private static boolean arregloContieneNegativo(int[] arreglo) {
		boolean ret = false;
		for (int i = 0; i < arreglo.length; i++) {
			ret = ret || arreglo[i] < 0;
		}
		return ret;
	}

}
