package pokemon;

import edu.princeton.cs.algs4.In;
import java.util.ArrayList;

public class LectorCSVPokemon {
    private ArrayList<String> nombres;
    private ArrayList<String> tipos;

    /**
     * Constructor que inicializa las listas y procesa el archivo CSV.
     * @param rutaArchivo Ruta al archivo pokemon.csv de Kaggle
     */
    public LectorCSVPokemon(String rutaArchivo) {
        nombres = new ArrayList<>();
        tipos = new ArrayList<>();
        cargarDatos(rutaArchivo);
    }

    private void cargarDatos(String rutaArchivo) {
        In in = new In(rutaArchivo);
        
        // Saltamos la primera línea correspondiente a los encabezados
        if (!in.isEmpty()) {
            in.readLine();
        }
        
        while (!in.isEmpty()) {
            String linea = in.readLine();
            // El dataset de Kaggle separa por comas
            String[] columnas = linea.split(",");
            
            // Asumiendo la estructura estándar del CSV de Kaggle:
            // Columna 1: Name, Columna 2: Type 1
            if (columnas.length >= 3) {
                String nombre = columnas[1].trim();
                String tipo1 = columnas[2].trim();
                
                if (!nombre.isEmpty()) {
                    nombres.add(nombre);
                }
                // Añadimos el tipo solo si no está registrado previamente
                if (!tipo1.isEmpty() && !tipos.contains(tipo1)) {
                    tipos.add(tipo1);
                }
            }
        }
    }

    public ArrayList<String> getNombres() {
        return nombres;
    }

    public ArrayList<String> getTipos() {
        return tipos;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        String pathToCSV = args[0];
        LectorCSVPokemon lector = new LectorCSVPokemon(pathToCSV);
        for (String nombre : lector.getNombres())
            System.out.println(nombre);
        System.out.println();
        for (String tipo : lector.getTipos())
            System.out.println(tipo);
    }
}
