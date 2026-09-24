import pokemon.*;
import edu.princeton.cs.algs4.*;
import java.util.*;

/**
 * Clase principal del laboratorio.
 * para ejecutar los experimentos de busqueda y orden, para luego
 * medir y exportar los tiempos de cada algoritmo.
 * Los datos de los archivos CSV se guardaran con los siguientes nombres
 * Ordenamiento: "instancia, selectionSort, mergeSort.
 * Busqueda: "instancia, nombre, t_ordenamiento, t_lineal, t_binaria.
 */

public class Experimento {

    public static void experimentoSelectionVSMerge() {
        //Utilizamos la clase Out de la libreria algs para crear el CSV
        Out Experimento1 = new Out("Experimento1_SelectionVsMerge.csv");
        //Con println hacemos que dentro del archivo CSV definamos los titulos de las columnas
        //como se solicita en la guia del laboratorio
        Experimento1.println("instancia, selectionSort, mergeSort");

        //hacemos el primer ciclo para que se ejecute por cada tamaño t {10,11,12,13,14,15}
        for (int t = 10; t <= 15; t++) {
            //Math pow funciona con datos tipo double, para poder adaptarlo a ese formato podemos usar
            // (int) que hace de conversor (usando TypeCasting) que trunca los valores decimales
            int n = (int) Math.pow(2, t);

            //creamos el segundo ciclo que se encarga de ejecutar las 100 repeticiones para cada experimento y tamaño
            for (int i = 0; i < 100; i++) {
                //utilizamos la semilla n+i especificada en el enunciado del laboratorio
                long seed = n + i;

                //creamos un arraylist para luego guardar los datos generados por merge y selection
                //esto para utilizar los metodos de la clase generator
                ArrayList<Pokemon> datosGenerados = PokemonGenerator.generateDatabase(n, seed);

                // primero creamos copias para cada algoritmo de datos generados
                ArrayList<Pokemon> copiaSelection = new ArrayList<Pokemon>(datosGenerados);
                ArrayList<Pokemon> copiaMerge = new ArrayList<Pokemon>(datosGenerados);

                // aqui instanciamos la base de datos para guardarlos en las copias
                PokemonDatabase dbSelection = new PokemonDatabase(copiaSelection);
                PokemonDatabase dbMerge = new PokemonDatabase(copiaMerge);

                //creamos el temporizador con newStopwatchCPU para cada algoritmo
                StopwatchCPU medicionSelection = new StopwatchCPU();
                //utilizamos el metodo ordenar por algoritmo de database para seleccionar el algoritmo y atributo
                dbSelection.ordenarPorAlgoritmo("selection", "totalStats");
                //con double creamos una variable para guardar el tiempo de medicion, con elapsedtime que
                //retorna el tiempo de medicion desde que se crea el temporizador
                double tiempoSelection = medicionSelection.elapsedTime();

                StopwatchCPU medicionMerge = new StopwatchCPU();
                dbMerge.ordenarPorAlgoritmo("merge", "totalStats");
                double tiempoMerge = medicionMerge.elapsedTime();
                //escribe en el archivo csv n es el tamaño de la instancia
                Experimento1.println(n + ", " + tiempoSelection + ", " + tiempoMerge);

            }
        }
        //cierra el archivo para guardar los datos en el csv
        Experimento1.close();

    }

    public static void experimentoSecuencialVSBinaria() {
        Out Experimento2 = new Out("Experimento2_SecuencialVsBinaria.csv");
        Experimento2.println("instancia, nombre, t_ordenamiento, t_lineal, t_binaria");

        for (int t = 10; t <= 15; t++) {
            int n = (int) Math.pow(2, t);
            int[] indicesFijos = {0, n / 200, n / 100, (3 * n) / 200, n / 50};

            for (int i = 0; i < 100; i++) {
                long seed = n + i;
                ArrayList<Pokemon> datosGenerados = PokemonGenerator.generateDatabase(n, seed);

                // 1. Mantener una DB desordenada para la secuencial y clonar otra para la ordenada
                PokemonDatabase dbDesordenada = new PokemonDatabase(new ArrayList<>(datosGenerados));
                PokemonDatabase dbOrdenada = new PokemonDatabase(new ArrayList<>(datosGenerados));

                // 2. Medir el tiempo de ordenamiento en la copia independiente
                StopwatchCPU timerOrdenamiento = new StopwatchCPU();
                dbOrdenada.ordenarPorAlgoritmo("merge", "name");
                double t_ordenamiento = timerOrdenamiento.elapsedTime();

                for (int j = 0; j < indicesFijos.length; j++) {
                    int id = indicesFijos[j];
                    int safeIdx = Math.min(id, datosGenerados.size() - 1);
                    String nombreBuscado = datosGenerados.get(safeIdx).getName();

                    // 3. Secuencial sobre la lista original desordenada
                    StopwatchCPU timerLineal = new StopwatchCPU();
                    dbDesordenada.sequentialSearch(nombreBuscado, "name");
                    double t_lineal = timerLineal.elapsedTime();

                    // 4. Binaria (100 veces) sobre la copia ordenada
                    StopwatchCPU timerBinaria = new StopwatchCPU();
                    for (int k = 0; k < 100; k++) {
                        dbOrdenada.binarySearch(nombreBuscado, "name");
                    }
                    double t_binaria = timerBinaria.elapsedTime() / 100.0;

                    Experimento2.println(n + ", " + nombreBuscado + ", " + t_ordenamiento + ", " + t_lineal + ", " + t_binaria);
                }
            }
        }
        Experimento2.close();
    }


    public static void main(String[] args) {

        experimentoSelectionVSMerge();

        experimentoSecuencialVSBinaria();
    }

}