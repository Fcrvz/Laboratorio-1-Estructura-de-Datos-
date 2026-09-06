package pokemon;

import java.util.ArrayList;
import java.util.Comparator;
import edu.princeton.cs.algs4.*;

/**
 * Gestiona la colección de Pokémones y sus operaciones de ordenamiento y búsqueda.
 */
public class PokemonDatabase {

    private ArrayList<Pokemon> pokemons;

    /**
     * Contructor vacio que inicializa la base de datos vacia.
     */
    public PokemonDatabase() {
        this.pokemons = new ArrayList<>();
    }

    /**
     * Contructor que inicializa la base de datos a partir de una lista ya existente.
     * @param pokemons Arraylist de Pokemons
     */
    public PokemonDatabase(ArrayList<Pokemon> pokemons) {

        this.pokemons = pokemons;
    }

    /**
     * Ordena la lista interna de Pokémones según el algoritmo y atributo indicados.
     * @param algoritmo El nombre que indica que algorirmo se usara("Selection" o "Merge")
     * @param atributo la Caracteristica del pokemon por la cual se deasea ordenar
     */
    public void ordenarPorAlgoritmo(String algoritmo, String atributo) {
        //si el algoritmo o la lista esta vacia se corta el proceso.
        if (algoritmo == null || this.pokemons.isEmpty()) {
            return;
        }
        //se obtiene el comparador segun el atributo que se selecciono
        Comparator<Pokemon> comparador = obtenerComparador(atributo);

        if (algoritmo.equals("selectionSort") || algoritmo.equals("selection")) {
            Selection.sort(this.pokemons, comparador);
        } else if (algoritmo.equals("mergeSort") || algoritmo.equals("merge")) {
            Merge.sort(this.pokemons, comparador);
        }
    }

    /**
     * Búsqueda secuencial. No requiere que la lista esté ordenada.
     * @param clave el valor buscado (ej:"Slowbro" o "80")
     * @param atributo el atributo que se elegira sobre el que se comparara la clave
     * @return un ArrayList con todo los pokemons que coincidan con la clave buscada
     */
    public ArrayList<Pokemon> sequentialSearch(String clave, String atributo) {
        ArrayList<Pokemon> resultados = new ArrayList<>();

        if (clave == null) {
            return resultados;
        }

        for (Pokemon p : this.pokemons) {
            // se compara el texto exacto si este coincide se guarada en la lista de resultados
            if (clave.equals(obtenerValorAtributo(p, atributo))) {
                resultados.add(p);
            }
        }

        return resultados;
    }

    /**
     * Búsqueda binaria O(log n + k). Asume que la lista ya está ordenada. localiza una coincidencia inicial
     * dividiendo la lista a la mitad y luego expande la busqueda linealmente hacia sus elementos que tiene a su lado
     * y retorna las coincidencias
     * @param clave El valor que se desea buscar.
     * @param atributo El atributo del Pokémon sobre el cual comparar.
     * @return un Arraylist con todas las coincidencias.
     */
    public ArrayList<Pokemon> binarySearch(String clave, String atributo) {
        ArrayList<Pokemon> resultados = new ArrayList<>();

        if (clave == null || this.pokemons.isEmpty()) {
            return resultados;
        }

        int inicio = 0;
        int fin = this.pokemons.size() - 1;
        int indiceEncontrado = -1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            String valorMedio = obtenerValorAtributo(this.pokemons.get(medio), atributo);

            int comparacion; // si esto es positivo va a la derecha y es negativo a la izquierda y si es 0 es porque encontro lo que busca
            //compara si el texto buscado es igual al del atributo "name" o "type1"
            if (atributo.equals("name") || atributo.equals("type1")){
                comparacion=clave.compareTo(valorMedio);//compara los dox textos en orden alfabetico
            }else{
                comparacion=Integer.compare(Integer.parseInt(clave), Integer.parseInt(valorMedio));
            }
            // si el resultado es 0 se encuentra al pokemon y rompemos el ciclo
            if (comparacion == 0) {
                indiceEncontrado = medio;
                break;
            } else if (comparacion < 0) {
                // Si es negativo, descartamos la mitad derecha
                fin = medio - 1;
            } else {
                // Si es positivo, descartamos la mitad izquierda
                inicio = medio + 1;
            }
        }

        if (indiceEncontrado != -1) {
            int limiteIzq = indiceEncontrado;
            int limiteDer = indiceEncontrado;


            while (limiteIzq > 0 && clave.equals(obtenerValorAtributo(this.pokemons.get(limiteIzq - 1), atributo))) {
                limiteIzq--;
            }
            while (limiteDer < this.pokemons.size() - 1 && clave.equals(obtenerValorAtributo(this.pokemons.get(limiteDer + 1), atributo))) {
                limiteDer++;
            }

            for (int i = limiteIzq; i <= limiteDer; i++) {
                resultados.add(this.pokemons.get(i));
            }
        }

        return resultados;
    }

    /**
     * varios comparadores que segun el atributo que le llega te devuelve la instruccion necesaria que java
     * nececita para ordenar a los pokemon por ese dato en especifico
     * alfabetico para textos
     * @param atributo el nombre del atributo por el que se ordenara
     * @return el valor del dato convertido a texto si el texto es nulo o invalido retorar el totlaStats por defecto.
     */
    private Comparator<Pokemon> obtenerComparador(String atributo) {
        if (atributo == null) {
            Comparator<Pokemon> comparadorTotalStats= (p1,p2)-> Integer.compare(p1.getTotalStats(), p2.getTotalStats());
            return comparadorTotalStats;
        }

        if (atributo.equals("id")) {
            Comparator<Pokemon> comparadorID= (p1,p2)-> Integer.compare(p1.getId(), p2.getId());
            return comparadorID;
        } else if (atributo.equals("name")) {
            Comparator<Pokemon> comparadorName= (p1,p2)->p1.getName().compareTo(p2.getName() );
            return comparadorName;
        } else if (atributo.equals("type1")) {
            Comparator<Pokemon>comparadorType= (p1,p2)->p1.getType1().compareTo(p2.getType1() );
            return comparadorType;
        }
        else if (atributo.equals("hp")) {
            Comparator<Pokemon> comparadorHp= (p1,p2)-> Integer.compare(p1.getHp(), p2.getHp());
           return comparadorHp;

        } else if (atributo.equals("attack")) {
            Comparator<Pokemon> comparadorAttack= (p1,p2)-> Integer.compare(p1.getAttack(), p2.getAttack());
            return comparadorAttack;
        } else if (atributo.equals("defense")) {
            Comparator<Pokemon> comparadorDefense= (p1,p2)-> Integer.compare(p1.getDefense(), p2.getDefense());
            return comparadorDefense;
        } else if (atributo.equals("speed")) {
            Comparator<Pokemon> comparadorSpeed= (p1,p2)-> Integer.compare(p1.getSpeed(), p2.getSpeed());
            return comparadorSpeed;
        } else {
            // Regla de fallback: si no reconoce la palabra, va por totalStats
            Comparator<Pokemon> comparadorTotalStats= (p1,p2)-> Integer.compare(p1.getTotalStats(), p2.getTotalStats());
            return comparadorTotalStats;
        }


    }

    /**
     *
     * @param p es el objeto Pokemon del cual se va a extraer su dato
     * @param atributo el nombnre del atributo que se quiere leer
     * @return el valor del atributo en formato String.
     */
    private String obtenerValorAtributo(Pokemon p, String atributo) {
        // Integer.toString() toma un número entero primitivo (int) y lo transforma formalmente en un objeto de texto (String).

        if (atributo == null) {
            return Integer.toString(p.getTotalStats());
        }

        if (atributo.equals("id")) {
            return Integer.toString(p.getId());
        }
        else if (atributo.equals("name")) {
            return p.getName();
        }
        else if (atributo.equals("type1")) {
            return p.getType1();
        }
        else if (atributo.equals("hp")) {
            return Integer.toString(p.getHp());
        }
        else if (atributo.equals("attack")) { 
            return Integer.toString(p.getAttack());
        }
        else if (atributo.equals("defense")) {
            return Integer.toString(p.getDefense());
        }
        else if (atributo.equals("speed")) {
            return Integer.toString(p.getSpeed());
        }
        else {
            // Regla de fallback exigida por el laboratorio
            return Integer.toString(p.getTotalStats());
        }
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }
}