package pokemon;

import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

public class PokemonGenerator {

    public static ArrayList<Pokemon> generateDatabase(int n, long seed){

        StdRandom.setSeed(seed);

        // debemos importar los datos del archivo csv de pokemon para poder usar lectorcsvpokemon usamos
        LectorCSVPokemon lector = new LectorCSVPokemon("src/pokemon/Pokemon.csv");
        //debemos obtener las listas disponibles de nombres y tipos para generar las instancias
        ArrayList<String> nombresCSV = lector.getNombres();
        ArrayList<String> tiposCSV = lector.getTipos();
        //con nombres y tipos debemos generar un vocabulario
        // hacemos este arraylist vacio para guardar el vocabulario posteriormente
        ArrayList<String> vocabulario = new ArrayList<>();
        //este vocabulario debe cumplir la condicion de que debe tener m nombres unicos
        // tambien si se llegan a necesitar más nombres de los disponibles en el archivo csv pokemon
        // se le debe agregar un sufijo numerico al nombre base
        // ningun nombre puede aparecer más de t veces
        //calculamos t mediante n
        int t = (int) (Math.log(n)/Math.log(2));
        //luego debemos calcular el valor de m utilizamos Math.ceil en vez de Math.round debido a las condiciones descritas
        //en el manual del laboratorio ya que se nos pide que ⌈n/m⌉ ≤ t, si despejamos la ecuacion en base a m
        // nos queda que m debe ser mayor o igual que n/t, por lo que round no nos sirve, ya que en round redondea
        // al numero mas cercano, si este llegara a redondear hacia abajo , no cumpliria la condicion de m a diferencia
        // de Math.ceil
        int m = (int) Math.ceil((double)n/t);

        //creamos el sufijo que debe llevar el nombre del pokemon cuando cumple la condicion
        //y creamos una variable auxiliar que vaya contando los ciclos del while
        int sufijo = 0;
        int ciclos = 0;

        //este ciclo cumple con estar activo hasta que el tamaño del vocabulario alcance el tamaño de m
        while (vocabulario.size()<m){
            //creamos otro ciclo para recorrer la lista del CSV
            for(int i=0;i<nombresCSV.size();i++){
                // ponemos nuestra primera condicion que si el tamaño del vocabulario alcanza
                // la cantidad m de nombres se detenga, cuando ya no cumpla con la condicion de nombres unicos
                //debemos implementar los sufijos en los nombres de la lista, por ejemplo
                // si tenemos 700 pokemons originales en el csv y queremos agregar 1100, agregaria 700 unicos
                // y en el momento que llegue al 701 empezarian los pokemons de la lista original pero con el sufijo
                // hasta llegar al tamaño de 1100 que con el break romperia el ciclo
                if(vocabulario.size()>=m){
                    break;
                }

                if(ciclos == 0){
                    vocabulario.add(nombresCSV.get(i));
                }
                else{
                    vocabulario.add(nombresCSV.get(i)+"_"+sufijo);
                }

            }
            ciclos++;

            if(ciclos>1){
                sufijo++;
            }
        }

        //se retorna un ArrayList vacio para comprobar posibles errores
        return new ArrayList<>();

    }

}
