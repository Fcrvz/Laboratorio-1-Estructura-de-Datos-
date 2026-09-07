package pokemon;

import java.util.Comparator;

/**
 * representa al pokemon que almacenara la informacion de identificacion y sus estadisticas de combate
 */
public class Pokemon {

    private int id;
    private String name;
    private String type1;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private int totalStats;

    /**
     * Constructor por defecto.
     */
    public Pokemon() {
    }

    /**
     * Constructor que inicializa los atributos del pokemon.Pokemon y calcula su totalStats.
     * @param id identificador del pokemon
     * @param name nombre del pokemon
     * @param type1 tipo primario
     * @param hp puntos de vida del pokemon
     * @param attack puntos de ataque del pokemon
     * @param defense puntos de defensa del pokemon
     * @param speed velocidad del pokemon
     */
    public Pokemon(int id, String name, String type1, int hp, int attack, int defense, int speed){
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        recalcularTotalStats(); // Llamamos a un método interno para evitar repetir código
    }

    /**
     * se asegura de recalcular los totalStats para que esta siempre sea la suma  de sus estadisticas.
     */
    private void recalcularTotalStats() {
        this.totalStats = this.hp + this.attack + this.defense + this.speed;
    }

    // Getters y Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType1() { return type1; }
    public void setType1(String type1) { this.type1 = type1; }

    public int getHp() { return hp; }
    public void setHp(int hp) {
        this.hp = hp;
        recalcularTotalStats();
    }

    public int getAttack() { return attack; }
    public void setAttack(int attack) {
        this.attack = attack;
        recalcularTotalStats();
    }

    public int getDefense() { return defense; }
    public void setDefense(int defense) {
        this.defense = defense;
        recalcularTotalStats();
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) {
        this.speed = speed;
        recalcularTotalStats();
    }

    public int getTotalStats() { return totalStats; }

}