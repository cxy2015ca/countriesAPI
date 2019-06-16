package national_bank_assignment.model;

import national_bank_assignment.exceptions.DuplicateCountryException;
import national_bank_assignment.exceptions.NoSuchCountryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CountryList {

    public ArrayList<Country> countries;


    public void init(){ //load hard coded data into country list
        countries = new ArrayList<>();
        countries.add(new Country(1, "Canada", Country.Continent.NORTHAMERICA, 37000000));
        countries.add(new Country(2, "US", Country.Continent.NORTHAMERICA, 327000000));
        countries.add(new Country(3, "France", Country.Continent.EUROPE, 67000000));
        countries.add(new Country(4, "China", Country.Continent.ASIA, 1386000000));
        countries.add(new Country(5, "Australia", Country.Continent.AUSTRALIA, 24600000));
        countries.add(new Country(6, "Egypt", Country.Continent.AFRICA, 95500000));
        countries.add(new Country(7, "Brazil", Country.Continent.SOUTHAMERICA, 209300000));
    }

    // if country not found, returns null
    public Country findCountryById(int id)
    {
        for(Country country : countries){
            if(country.getId() == id){
                return country;
            }
        }
        return null;
    }

    // if country not found, returns null
    public Country findCountryByName(String name){
        for(Country country : countries){
            if(name.equals(country.getName())){
                return country;
            }
        }
        return null;
    }

    // if countries not found, returns empty array
    public ArrayList<Country> findCountryByContinent(Country.Continent continent){
        ArrayList<Country> inContinent = new ArrayList<>();
        for(Country country : countries){
            if(country.getContinent() == continent){
                inContinent.add(country);
            }
        }
        return inContinent;
    }

    // if countries not found, returns empty array
    public ArrayList<Country> findCountryPopulationUnder(long pop){
        ArrayList<Country> underPop = new ArrayList<>();
        for(Country country : countries){
            if(country.getPopulation() <= pop){
                underPop.add(country);
            }
        }
        return underPop;
    }

    //add country, returns ID if success, else throws exception
    public int addCountry(String name, Country.Continent continent, long population) throws Exception
    {
        int newId = countries.size() + 1;
        if(findCountryByName(name)!= null){
            throw new DuplicateCountryException("Country " + name+ "already exists");
        } else {
            countries.add(new Country(newId, name, continent, population));
        }
        return newId;
    }

    //deletes the country and returns the id, throws exception if country does not exist
    public int deleteCountryByName(String name) throws NoSuchCountryException
    {
        int deletedId = -1;
        if(findCountryByName(name)== null){
            throw new NoSuchCountryException("Country " + name + " does not exists");
        } else {
            Iterator itr = countries.iterator();
            while (itr.hasNext())
            {
                Country c = (Country) itr.next();
                if(name.equals(c.name)){
                    deletedId = c.getId();
                    countries.remove(itr);
                }
            }
        }
        return deletedId;
    }



}
