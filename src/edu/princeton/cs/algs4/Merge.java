/******************************************************************************
 *  Compilation:  javac Merge.java
 *  Execution:    java Merge < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/22mergesort/tiny.txt
 *                https://algs4.cs.princeton.edu/22mergesort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using mergesort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Merge < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Merge < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 ******************************************************************************/

package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.Comparator;

import pokemon.*;

/**
 * merge fue adaptada para que ahora esta opere sobre Arraylist
 */
public class Merge{

    // This class should not be instantiated.
    private Merge() { }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    /*aqui cambiamos los arreglos tradicionales por arraylist para adaptarlo y que funcione con
    * las clases pokemon, como pide el enunciado del laboratorio, se cambiaron las variables justas y necesarias
    * para que esto funcionara, ademas de cambiar los arreglos tradicionales implementamos Comparator<Pokemon> de la
    * libreria de java.util al igual que arraylist
    * de los parametros originales del arreglo tradicional "a" paso a ser "lista", aux se mantuvo al igual
    * que las demas variables originales
    *

    * */

    /**
     *
     * @param lista la lista orignal de Pokemon
     * @param aux respaldo/ copia de la lista
     * @param lo inidice  de inicio
     * @param mid indice de la mitad
     * @param hi inidice del termino
     * @param comparador determina la posicion
     */
    private static void merge(ArrayList<Pokemon> lista, ArrayList<Pokemon> aux, int lo, int mid, int hi, Comparator<Pokemon> comparador) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(lista, lo, mid, comparador);
        assert isSorted(lista, mid+1, hi, comparador);

        // copy to aux[]
        // el cambio realizado al codigo fue cambiar la forma de acceder al indice del arreglo tradicional por aux.set(k) para
        // que este funcione en arraylists
        for (int k = lo; k <= hi; k++) {
            aux.set(k, lista.get(k));
        }

        // merge back to a[]
        /* para adaptar este ciclo para que funcione con arraylist utilizamos el mismo procedimiento
        *  cambiar todo lo que este relacionado con arreglos tradicionales a la manera de arraylists
        *  que en este caso seria utilizar gets para acceder a las posiciones de los arreglos dinamicos
        *  y set para modificar los datos del mismo
        *
        * */
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid){
                lista.set(k,aux.get(j++));
            }
            else if (j > hi){
                lista.set(k,aux.get(i++));
            }
            else if (less(aux.get(j), aux.get(i), comparador)){
                lista.set(k,aux.get(j++));
            }
            else{
                lista.set(k,aux.get(i++));
            }
        }

        // postcondition: a[lo .. hi] is sorted
        assert isSorted(lista, lo, hi, comparador);
    }

    /* mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    * Comparable[] podemos desglozarlo como "Comparable" la cual es equivalente a nuestra variable pokemon
     *pero este pierde la capacidad de comparar por si solo por lo cual debemos implementar un nuevo comparador
     *el que decidimos usar fue Comparator dentro de este pusimos nuestra variable Pokemon
     *y el arreglo "[]" seria nuestro ArrayList
     **/
    /**
     *
     * @param lista la lista original de Pokemon
     * @param aux copia de la lista
     * @param lo indicie inicial
     * @param hi indice final
     * @param comparador lo que determinara el orden
     */
    private static void sort(ArrayList<Pokemon>lista, ArrayList<Pokemon> aux, int lo, int hi, Comparator<Pokemon> comparador) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(lista, aux, lo, mid,comparador);
        sort(lista, aux, mid + 1, hi,comparador);
        merge(lista, aux, lo, mid, hi,comparador);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */


    /*
    *  en este caso la solucion que vimos para adaptar el sort
    *  fue en primer lugar cambiar los parametros Comparable[] a
    *  por el estandar que llevamos usando para los ArrayList
    *  luego necesitabamos adaptar Comparable[] aux = new Comparable[a.length]
    *  el cual cumplia con la funcion de declarar la variable aux que era un arreglo tradicional
    *  y crear el arreglo del mismo tamaño que el arreglo original, el cual era "a" pero en nuestro caso sera
    *  "lista" para lograr esta adaptacion la solucion que vimos fue usar un condicional if para que si la lista
    *  que se ingresa es null, esta vacia o tiene solo 1 pokemon la funcion termina, por lo cual este es nuestro
    *  caso base ya que estamos utilizando recursividad. Para sustituir Comparable[] aux = new Comparable[a.length]
    *  utilizamos ArrayList<Pokemon>aux=new ArrayList<Pokemon>(lista); el cual cumple la misma funcion
    *  ya que el primer arraylist se encarga de declarar el aux para luego crearlo con los datos de la lista,
    *  esto lo hacemos para que ambos poseaan el mismo tamaño ya que los datos utilizados no son relevantes
    *  ya que en merge luego seran sobrescribidos (por algo es nuestro auxiliar)
    *
    * */

    /**
     *
     * @param lista el Arraylist que contiene los objetos que se ordenaran
     * @param comparador la caracteristica por la cual se comparara(ej: hp,nombre,speed)
     */
    public static void sort(ArrayList<Pokemon>lista, Comparator<Pokemon> comparador){
        if(lista==null||lista.size()<=1){
            return;
        }
        ArrayList<Pokemon>aux=new ArrayList<Pokemon>(lista);
        sort(lista, aux, 0, lista.size()-1,comparador);
        //Devuelve true si la lista está perfectamente ordenada, o false si encuentra algún elemento fuera de lugar.
        //esto es adaptado del codigo original
        assert isSorted(lista,comparador);
    }


   /***************************************************************************
    *  Helper sorting function.
    ***************************************************************************/

    // is v < w ?
   //Comparable es nuestro Pokemon
    private static boolean less(Pokemon v, Pokemon w, Comparator<Pokemon> comparador) {
        return comparador.compare(v,w)<0;
    }

   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
   /**
   * cambiamos del metodo original todos los Comparable[] por ArralyList<Pokemon>
   * para adaptarlo y que merge y las demas clases que contienen arraylist funcionen correctamente
   * (inchequeable) el antiguo lista.length fue cambiado por un lista.size ya que los arreglos dinamicos no funcionan con
   * un tamaño fijo por lo que no pueden utilizar el .length ().
   * ademas de usar el arraylist debemos usar el Comparator<Pokemon> para poder ordenar en el arreglo que pokemon
   * va antes dentro del mismo arraylist, a diferencia del algoritmo original este tiene que tener un comparador externo
   * */

   private static boolean isSorted(ArrayList<Pokemon> lista, Comparator<Pokemon> comparador) {
        return isSorted(lista, 0, lista.size() - 1, comparador);
    }

    private static boolean isSorted(ArrayList<Pokemon> lista, int lo, int hi, Comparator<Pokemon> comparador) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(lista.get(i), lista.get(i-1),comparador)) return false;
        return true;
    }


   /***************************************************************************
    *  Index mergesort.
    ***************************************************************************/
   /*
   * index mergesort funciona de manera resumida ordenando los datos en orden ascendente de su indice
   * por lo cual para el desarrollo del laboratorio todos los metodos vinculado a index mergesort
   * no son necesarios debido a que en el enunciado del laboratorio se nos pide ordenar por POKEMONS
   * no por el indice de los mismos, todo esto gracias al la clase LectorCSVPokemon
   * */
   // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
//    private static void merge(ArrayList<Pokemon> lista, int[] index, int[] aux, int lo, int mid, int hi,Comparator<Pokemon> comparador) {
//
//
//        // copy to aux[]
//        for (int k = lo; k <= hi; k++) {
//            aux[k] = index[k];
//        }
//
//        // merge back to a[]
//        int i = lo, j = mid+1;
//        for (int k = lo; k <= hi; k++) {
//            if      (i > mid)                    index[k] = aux[j++];
//            else if (j > hi)                     index[k] = aux[i++];
//            else if (less(lista.get(aux[j]), lista.get(aux[i]), comparador)) index[k] = aux[j++];
//            else                                 index[k] = aux[i++];
//        }
//    }
//
    /**
     * Returns a permutation that gives the elements in the array in ascending order.
     * @param a the array
     * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]},
     *    ..., {@code a[p[n-1]]} are in ascending order
     */
//    public static int[] indexSort(ArrayList<Pokemon> lista, Comparator<Pokemon> comparador) {
//        int n = lista.size();
//        int[] index = new int[n];
//        for (int i = 0; i < n; i++)
//            index[i] = i;
//
//        int[] aux = new int[n];
//        sort(lista, index, aux, 0, n-1,comparador);
//        return index;
//    }

//    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
//    private static void sort(ArrayList<Pokemon> lista, int[] index, int[] aux, int lo, int hi, Comparator<Pokemon> comparador) {
//        if (hi <= lo) return;
//        int mid = lo + (hi - lo) / 2;
//        sort(lista, index, aux, lo, mid, comparador);
//        sort(lista, index, aux, mid + 1, hi,comparador);
//        merge(lista, index, aux, lo, mid, hi, comparador);
//    }

//    // print array to standard output
//    private static void show(ArrayList<Pokemon> lista) {
//        for(int i = 0; i < lista.size(); i++) {
//            StdOut.println(lista.get(i));
//        }
//    }

    /*
     * Reads in a sequence of strings from standard input; mergesorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    /*public static void main(String[] args) {
        String[] lista = StdIn.readAllStrings();
        Merge.sort(lista);
        show(lista);
    }*/
}
