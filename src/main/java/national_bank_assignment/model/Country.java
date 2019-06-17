package national_bank_assignment.model;

public class Country {
    int id;
    String name;
    Continent continent;
    long population;

    public enum Continent{
        ASIA,
        NORTHAMERICA,
        SOUTHAMERICA,
        EUROPE,
        ANTARTICA,
        AUSTRALIA,
        AFRICA
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }


    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    //constructor
    public Country(int id, String name, Continent continent, long population){
        setId(id);
        setName(name);
        setContinent(continent);
        setPopulation(population);
    }


}
