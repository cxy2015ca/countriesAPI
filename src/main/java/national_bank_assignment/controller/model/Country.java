package national_bank_assignment.controller.model;

public class Country {
    int id;
    String name;
    Continent continent;
    int population;


    enum Continent{
        ASIA,
        NORTHAMERICA,
        SOUTHAMERICA,
        EUROPE,
        ANTARTICA,
        AUSTRALIA,
        AFRICA
    }

}
