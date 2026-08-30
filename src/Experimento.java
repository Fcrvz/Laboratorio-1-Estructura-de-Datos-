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

    public static void experimentoSelectionVSMerge(){
        //Utilizamos la clase Out de la libreria algs para crear el CSV
        Out Experimento1 = new Out("Experimento1_SelectionVsMerge.csv");
        //Con println hacemos que dentro del archivo CSV definamos los titulos de las columnas
        //como se solicita en la guia del laboratorio
        Experimento1.println("instancia, selectionSort, mergeSort");

        //hacemos el primer ciclo para que se ejecute por cada tamaño t {10,11,12,13,14,15}
        for(int t=10;t<=15;t++) {
            //Math pow funciona con datos tipo double, para poder adaptarlo a ese formato podemos usar
            // (int) que hace de conversor (usando TypeCasting) que trunca los valores decimales
            int n = (int)Math.pow(2,t);

            //creamos el segundo ciclo que se encarga de ejecutar las 100 repeticiones para cada experimento y tamaño
            for(int i=0;i<100;i++){
                //utilizamos la semilla n+i especificada en el enunciado del laboratorio
                long seed = n+i;
            }
        }
    }

    public static void experimentoSecuencialVSBinaria(){

        Out Experimento2 = new Out("Experimento2_SecuencialVsBinaria.csv");

        Experimento2.println("instancia, nombre, t_ordenamiento, t_lineal, t_binaria");

        for(int t=10;t<=15;t++){
            int n=(int) Math.pow(2,t);

            for(int i=0;i<100;i++){
                long seed = n+i;

            }
        }
    }






    /**
     * Por ahora se crea un objeto llamado prueba para comprobar
     * la compatibilidad de las clases.
     */


    public static void main(String[] args){
        
        //objeto de prueba para comprobar posibles errores
        PokemonDatabase prueba = new PokemonDatabase();

    }
}
