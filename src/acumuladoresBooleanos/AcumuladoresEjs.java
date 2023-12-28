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

	public static boolean mayorDiversidad(int[][] mtx) {
		return matrizConTodasFilasOrdenadasAscen(mtx) && matrizConTodasColConAlMenosUnElemParYOtroImpar(mtx);
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

	private static boolean matrizConTodasFilasOrdenadasAscen(int[][] mtx) {
		boolean todasFilConOrdenAscen = true;

		for (int fil = 0; fil < mtx.length; fil++) {
			for (int col = 0; col < mtx.length - 1; col++) {
				todasFilConOrdenAscen = todasFilConOrdenAscen && mtx[fil][col] < mtx[fil][col + 1];
			}
		}
		return todasFilConOrdenAscen;
	}

	private static boolean matrizConTodasColConAlMenosUnElemParYOtroImpar(int[][] mtx) {
		boolean todasColConElemImpar = true;
		boolean todasColConElemPar = true;
		boolean colConElemImpar;
		boolean colConElemPar;

		for (int col = 0; col < mtx[0].length; col++) {
			colConElemImpar = false;
			colConElemPar = false;

			for (int fil = 0; fil < mtx.length; fil++) {
				colConElemImpar = colConElemImpar || mtx[fil][col] % 2 != 0;
				colConElemPar = colConElemPar || mtx[fil][col] % 2 == 0;
			}
			todasColConElemImpar = todasColConElemImpar && colConElemImpar;
			todasColConElemPar = todasColConElemPar && colConElemPar;
		}
		return todasColConElemImpar && todasColConElemPar;
	}

}
