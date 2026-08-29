package pokemon;

import java.util.ArrayList;
import java.util.Comparator;
import edu.princeton.cs.algs4.*;

/**
 * Gestiona la colección de Pokémones y sus operaciones de ordenamiento y búsqueda.
 */
public class PokemonDatabase {

    private ArrayList<Pokemon> pokemons;

    public PokemonDatabase() {
        this.pokemons = new ArrayList<>();
    }

    public PokemonDatabase(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    /**
     * Ordena la lista interna de Pokémones según el algoritmo y atributo indicados.
     */
    public void ordenarPorAlgoritmo(String algoritmo, String atributo) {
        if (algoritmo == null || this.pokemons.isEmpty()) {
            return;
        }

        Comparator<Pokemon> comparador = obtenerComparador(atributo);

        if (algoritmo.equalsIgnoreCase("selectionSort") || algoritmo.equalsIgnoreCase("selection")) {
            Selection.sort(this.pokemons, comparador);
        } else if (algoritmo.equalsIgnoreCase("mergeSort") || algoritmo.equalsIgnoreCase("merge")) {
            Merge.sort(this.pokemons, comparador);
        }
    }

    /**
     * Búsqueda secuencial. No requiere que la lista esté ordenada.
     */
    public ArrayList<Pokemon> sequentialSearch(String clave, String atributo) {
        ArrayList<Pokemon> resultados = new ArrayList<>();

        if (clave == null) {
            return resultados;
        }

        for (Pokemon p : this.pokemons) {
            if (clave.equals(obtenerValorAtributo(p, atributo))) {
                resultados.add(p);
            }
        }

        return resultados;
    }

    /**
     * Búsqueda binaria O(log n + k). Asume que la lista ya está ordenada.
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

            int comparacion;
            try {
                comparacion = Integer.compare(Integer.parseInt(clave), Integer.parseInt(valorMedio));
            } catch (NumberFormatException e) {
                comparacion = clave.compareToIgnoreCase(valorMedio);
            }

            if (comparacion == 0) {
                indiceEncontrado = medio;
                break;
            } else if (comparacion < 0) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }

        if (indiceEncontrado != -1) {
            int limiteIzq = indiceEncontrado;
            int limiteDer = indiceEncontrado;

            while (limiteIzq > 0 && clave.equalsIgnoreCase(obtenerValorAtributo(this.pokemons.get(limiteIzq - 1), atributo))) {
                limiteIzq--;
            }
            while (limiteDer < this.pokemons.size() - 1 && clave.equalsIgnoreCase(obtenerValorAtributo(this.pokemons.get(limiteDer + 1), atributo))) {
                limiteDer++;
            }

            for (int i = limiteIzq; i <= limiteDer; i++) {
                resultados.add(this.pokemons.get(i));
            }
        }

        return resultados;
    }

    private Comparator<Pokemon> obtenerComparador(String atributo) {
        if (atributo == null) {
            return Comparator.comparingInt(Pokemon::getTotalStats);
        }

        switch (atributo.toLowerCase()) {
            case "id":
                return Comparator.comparingInt(Pokemon::getId);
            case "name":
                return Comparator.comparing(Pokemon::getName, String.CASE_INSENSITIVE_ORDER);
            case "type1":
                return Comparator.comparing(Pokemon::getType1, String.CASE_INSENSITIVE_ORDER);
            case "hp":
                return Comparator.comparingInt(Pokemon::getHp);
            case "attack":
                return Comparator.comparingInt(Pokemon::getAttack);
            case "defense":
                return Comparator.comparingInt(Pokemon::getDefense);
            case "speed":
                return Comparator.comparingInt(Pokemon::getSpeed);
            default:
                return Comparator.comparingInt(Pokemon::getTotalStats);
        }
    }

    private String obtenerValorAtributo(Pokemon p, String atributo) {
        if (atributo == null) {
            return String.valueOf(p.getTotalStats());
        }

        switch (atributo.toLowerCase()) {
            case "id": return String.valueOf(p.getId());
            case "name": return p.getName();
            case "type1": return p.getType1();
            case "hp": return String.valueOf(p.getHp());
            case "attack": return String.valueOf(p.getAttack());
            case "defense": return String.valueOf(p.getDefense());
            case "speed": return String.valueOf(p.getSpeed());
            default: return String.valueOf(p.getTotalStats());
        }
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }
}