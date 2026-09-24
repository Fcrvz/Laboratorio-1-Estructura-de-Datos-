import edu.princeton.cs.algs4.*;
import pokemon.*;
import java.util.*;

public class PruebaLinealBinario {
    public static ArrayList<Pokemon> listaDePrueba() {
        ArrayList<Pokemon> lista = new ArrayList<Pokemon>();

        lista.add(new Pokemon(80, "Slowbro", "Water", 95, 75, 110, 30));
        lista.add(new Pokemon(92, "Gastly", "Ghost", 30, 35, 30, 80));
        lista.add(new Pokemon(93, "Gastly", "Ghost", 30, 35, 30, 80));
        lista.add(new Pokemon(94, "Gastly", "Ghost", 30, 35, 30, 80));
        lista.add(new Pokemon(11, "Metapod", "Bug", 50, 20, 55, 30));
        lista.add(new Pokemon(380, "Latias", "Dragon", 80, 80, 90, 110));
        lista.add(new Pokemon(701, "Hawlucha", "Fighting", 78, 92, 75, 118));

        return lista;
    }

    public static void main(String[] args){
        // se instancia la base de datos con una lista de prueba
        ArrayList<Pokemon> lista = listaDePrueba();
        PokemonDatabase db = new PokemonDatabase(lista);

        System.out.println("Busqueda lineal");
        // Prueba unitaria lineal presente
        ArrayList<Pokemon> linPresente = db.sequentialSearch("Hawlucha", "name");
        imprimirListas("busqueda lineal para pokemon Hawlucha: ", linPresente);
        System.out.println("cantidad en la base de datos = 1 == " + linPresente.size());

        // Prueba unitaria lineal ausente
        ArrayList<Pokemon> linAusente = db.sequentialSearch("Charizard", "name");
        imprimirListas("busqueda lineal para pokemon Charizard: ", linAusente);
        System.out.println("cantidad en la base de datos = 0 == " + linAusente.size());

        System.out.println(" ");

        // Ordenamos la lista por nombre usando Merge
        db.ordenarPorAlgoritmo("merge", "name");
        imprimirListas("Busqueda binaria",lista);

        // Prueba unitaria binaria presente
        ArrayList<Pokemon> binPresente = db.binarySearch("Hawlucha", "name");
        imprimirListas("busqueda lineal para pokemon Hawlucha: ", binPresente);
        System.out.println("cantidad en la base de datos = 1 == " + binPresente.size());

        // Prueba unitaria binaria ausente
        ArrayList<Pokemon> binAusente = db.binarySearch("Charizard", "name");
        imprimirListas("busqueda lineal para pokemon Charizard: ", binAusente);
        System.out.println("cantidad en la base de datos = 0 == " + binAusente.size());

        // Prueba unitaria binaria múltiples
        ArrayList<Pokemon> binMultiples = db.binarySearch("Gastly", "name");
        imprimirListas("busqueda lineal para pokemon Gastly: ", binMultiples);
        System.out.println("cantidad en la base de datos = 3 == " + binMultiples.size());

        // Verificacion final de los datos
        System.out.println(" ");
        System.out.println("datos correctos?");
        boolean pruebasExitosas = (linPresente.size() == 1) && (linAusente.isEmpty()) &&
                (binPresente.size() == 1) && (binAusente.isEmpty()) && (binMultiples.size() == 3);

        if (pruebasExitosas) {
            System.out.println("si");
        } else {
            System.out.println("alguna busqueda dio datos equivocados");
        }
    }
    public static void imprimirListas(String tipoLista, ArrayList<Pokemon> lista) {
        System.out.println(tipoLista);
        for (int i = 0; i < lista.size(); i++) {
            // creamos un "objeto/variable" de tipo pokemon para acceder a los metodos de la clase pokemon como los getters
            Pokemon o = lista.get(i);
            System.out.println("Id " + o.getId() + " Nombre " + o.getName() + " Tipo " + o.getType1() + " Defensa " + o.getDefense());
        }
    }
}
