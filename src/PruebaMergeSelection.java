import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Selection;
import pokemon.Pokemon;

import java.util.*;


//creamos esta clase auxiliar para realizar pruebas unitarias y verificacion de correctitud
public class PruebaMergeSelection {
    //Primero creamos una lista con algunos pokemons al azar para realizar una prueba con pocos datos
    public static ArrayList<Pokemon> listaDePrueba(){
        ArrayList<Pokemon> lista = new ArrayList<Pokemon>();

        lista.add(new Pokemon(80,"Slowbro","Water",95,75,110,30));
        lista.add(new Pokemon(92,"Gastly","Ghost",30,35,30,80));
        lista.add(new Pokemon(11,"Metapod","Bug",50,20,55,30));
        lista.add(new Pokemon(380,"Latias","Dragon",80,80,90,110));
        lista.add(new Pokemon(701,"Hawlucha","Fighting",78,92,75,118));

        return lista;
    }

    public static void main(String[] args){
        // hacemos un arraylist para cada tipo de ordenamiento
        ArrayList<Pokemon> listaMerge = listaDePrueba();
        ArrayList<Pokemon> listaSelection = listaDePrueba();
        //Usamos algun comparador de la clase pokemon, puede ser cualquier "estadistica"
        Comparator<Pokemon> compararPokemon = new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                // Integer.compare devuelve un negativo si o1<o2
                // devuelve un 0 si ambos son iguales y un negativo si o1>o2
                // necesitamos obtener estos valores para posteriormente compararlos con un boolean
                // si desearamos comparar por otro atributo que no sea defensa debemos cambiar el get
                // por el parametro a comparar, velocidad, vida, etc
                return Integer.compare(o1.getDefense(), o2.getDefense());
            }
        };
        // le damos la lista correspondiente a cada algoritmo y usamos el comparador
        Selection.sort(listaSelection,compararPokemon);
        Merge.sort(listaMerge,compararPokemon);
        //luego debemos verificar si ambas listas quedaron iguales
        boolean sonIguales = verificarIguales(listaMerge,listaMerge);

        //ahora podemos imprimir los datos para verificar si se ejecuto todo correctamente
        if(sonIguales==true){
            System.out.println("Ambas listas son iguales");
        }
        else{
            System.out.println("error en las listas");
        }
        //al ejecutar el codigo asi, nos imprime que ambas son iguales, pero para que la demostracion se más visual
        //decidimos implementar un metodo que imprima ambas listas

        imprimirListas("Selection Sort", listaSelection);
        imprimirListas("Merge Sort", listaMerge);

    }

    public static boolean verificarIguales(ArrayList<Pokemon> lista1, ArrayList<Pokemon> lista2) {
        // Caso base, que ambas listas tengan el mismo tamaño, ya que si no es el caso, no serian iguales
        if (lista1.size() != lista2.size()) {
            return false;
        }
        // el segundo caso debe ser que ambas listas contengan los mismos datos en el mismo orden,
        // para hacer esto debemos comparar por algun dato especifico como su id o su nombre
        // en este caso usaremos el ID por que cada pokemon tiene un id UNICO
        // para poder comprobar ambas listas y sus posiciones debemos usar un ciclo y obtener los datos de las listas
        for (int i = 0; i < lista1.size(); i++) {
            //con este if comparamos que la posicion i de abmbas listas si son distintas se detiene el ciclo
            //debido a que no serian iguales
            if (lista1.get(i).getId() != lista2.get(i).getId()){
                return false;
            }
        }
        //si el ciclo termina por completo sin encontrar ninguna diferencia es porque ambas lsitas son iguales
        return true;
    }

    public static void imprimirListas(String tipoLista, ArrayList<Pokemon> lista){
        System.out.println(tipoLista);
        for(int i=0;i<lista.size();i++){
            // creamos un "objeto/variable" de tipo pokemon para acceder a los metodos de la clase pokemon como los getters
            Pokemon o = lista.get(i);
            System.out.println("Id "+ o.getId() +" Nombre "+o.getName()+" Tipo "+o.getType1()+" Defensa "+ o.getDefense());
        }
    }
}
