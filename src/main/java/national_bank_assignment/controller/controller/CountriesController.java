package national_bank_assignment.controller.controller;

import national_bank_assignment.controller.model.Country;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

public class CountriesController {
    public ArrayList<Country> countryList;

    @PostConstruct
    public void init(){ //load hard coded data into country list
        countryList.add(new Country(1, "Canada", Country.Continent.NORTHAMERICA, 37000000));
        countryList.add(new Country(2, "US", Country.Continent.NORTHAMERICA, 327000000));
        countryList.add(new Country(3, "France", Country.Continent.EUROPE, 67000000));
        countryList.add(new Country(4, "China", Country.Continent.ASIA, 1386000000));
        countryList.add(new Country(5, "Australia", Country.Continent.AUSTRALIA, 24600000));
        countryList.add(new Country(6, "Egypt", Country.Continent.AFRICA, 95500000));
        countryList.add(new Country(7, "Brazil", Country.Continent.SOUTHAMERICA, 209300000));
    }

    @RequestMapping("/countries")
    public String allCountries() {
        return "Countries API";
    }
}
