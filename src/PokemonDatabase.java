import java.util.ArrayList;
import edu.princeton.cs.algs4.Merge;

/**
 * Clase que almacenara una lista con todos los Pokemon.
 */
public class PokemonDatabase{
    private ArrayList<Pokemon> pokemons = new ArrayList<>();

    // constructor vacio
    public PokemonDatabase(){}

    /**
     * Esta funcion se encargara de ordenar la lista de pokemons.
     * @param algoritmo este parametro acepta selection sort y mergesort.
     * @param atributo este parametro determinara el orden de las caracteristicas de los pokemons.
     */
    public void ordenarPorAlgoritmo(String algoritmo, String atributo){

    }

    /**
     * esta funcion retornara todos los pokemons cuyo valor coincida con la clave(la lista no debe estar ordenada)
     * @param clave el valor que se buscara
     * @param atributo la caracteristica en la cual buscaremos
     * @return lista que retorna las coincidencias(por el momento retorna vacio)
     */
    public ArrayList<Pokemon> sequentialSearch(String clave, String atributo){
        return new ArrayList<>();
    }

    /**
     * se debe asumir que la lista esta ordenada
     * @param clave el valor que se buscara
     * @param atributo la caracteristica en la cual buscaremos
     * @return lista que retorna las coincidencias(por el momento retorna vacio)
     */
    public ArrayList<Pokemon> binarySearch(String clave, String atributo){
        return new ArrayList<>();
    }



}



