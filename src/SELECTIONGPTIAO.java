package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.Comparator;
import pokemon.Pokemon;

/**
 *  La clase {@code Selection} proporciona métodos estáticos para ordenar un
 *  ArrayList utilizando el algoritmo de selection sort.
 */
public class Selection {

    // No se debe instanciar esta clase.
    private Selection() { }

    /**
     * Reorganiza la lista en orden ascendente utilizando el comparador especificado.
     * @param lista el ArrayList de Pokemon a ordenar
     * @param comparador el comparador que define el criterio de ordenamiento
     */
    public static void sort(ArrayList<Pokemon> lista, Comparator<Pokemon> comparador) {
        int n = lista.size();
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(lista.get(j), lista.get(min), comparador)) min = j;
            }
            exch(lista, i, min);
            assert isSorted(lista, comparador, 0, i);
        }
        assert isSorted(lista, comparador);
    }

    /***************************************************************************
     *  Funciones de ayuda para el ordenamiento y validación.
     ***************************************************************************/

    // ¿Es v < w según el comparador?
    private static boolean less(Pokemon v, Pokemon w, Comparator<Pokemon> comparador) {
        return comparador.compare(v, w) < 0;
    }

    // Intercambia los elementos en los índices i y j
    private static void exch(ArrayList<Pokemon> lista, int i, int j) {
        Pokemon swap = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, swap);
    }

    // Verifica si la lista completa está ordenada
    private static boolean isSorted(ArrayList<Pokemon> lista, Comparator<Pokemon> comparador) {
        return isSorted(lista, comparador, 0, lista.size() - 1);
    }

    // Verifica si la lista está ordenada en el subarreglo lista[lo..hi]
    private static boolean isSorted(ArrayList<Pokemon> lista, Comparator<Pokemon> comparador, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(lista.get(i), lista.get(i-1), comparador)) return false;
        return true;
    }
}