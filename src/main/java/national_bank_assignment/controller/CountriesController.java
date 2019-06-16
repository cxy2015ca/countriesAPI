package national_bank_assignment.controller;

import national_bank_assignment.exceptions.DuplicateCountryException;
import national_bank_assignment.exceptions.NoSuchCountryException;
import national_bank_assignment.model.Country;
import national_bank_assignment.model.CountryList;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CountriesController {
    public CountryList countryList;

    @PostConstruct
    public void init() {
        countryList = new CountryList();
        countryList.init();
    }


    @RequestMapping(value = "/countries/allCountries", method = RequestMethod.GET)
    public ArrayList<Country> allCountries() {
        return countryList.countries;
    }

    @RequestMapping(value = "/countries/findCountryById", method = RequestMethod.POST)
    public Map<String, Object> findCountryById( @RequestBody Map<String, Object> request){
        Map<String, Object> response = new HashMap<>();
        int id = (int) request.get("id");
        Country c = countryList.findCountryById(id);
        if(c == null){
            response.put("status", 404);
            response.put("message", "country with id " + id + " does not exist");
        } else {
            response.put("status", 200);
            response.put("country", c);
        }

        return response;
    }

    @RequestMapping(value = "/countries/addCountry", method = RequestMethod.POST)
    public Map<String, Object> addCountry( @RequestBody Map<String, Object> request){
        int id = -1;
        Map<String, Object> response = new HashMap<>();
        try{
            if (!(request.containsKey("name") && request.containsKey("continent") && request.containsKey("population"))){
                response.put("status", 400);
                response.put("message", "missing parameters");
            } else {
                String name = (String) request.get("name");
                String cont = (String) request.get("continent");
                Country.Continent continent = determineContinent(cont);
                long pop = Long.valueOf( (int) request.get("population"));
                id = countryList.addCountry(name, continent, pop);
                response.put("status", 200);
                response.put("countryId", id);
                response.put("message", name+ " successfully added");
                response.put("allCountries", countryList.countries);
            }

        } catch (DuplicateCountryException e){
            response.put("status", 400);
            response.put("message", e.getLocalizedMessage());
        } catch (Exception e){
            response.put("status", 400);
            response.put("message", e.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/countries/deleteCountry/{name}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteCountry(@PathVariable("name") String name){
        Map<String, Object> response = new HashMap<>();
        try{
            int id = countryList.deleteCountryByName(name);
            response.put("status", 200);
            response.put("countryId", id);
            response.put("message", name+ " successfully deleted");

        } catch (NoSuchCountryException e){
            response.put("status", 404);
            response.put("message", e.getLocalizedMessage());
        } catch (Exception e){
            response.put("status", 400);
            response.put("message", e.getLocalizedMessage());
        }
        return response;
    }







    // since cannot cast string to enum
    public Country.Continent determineContinent(String cont) throws Exception
    {
        if(cont.equals("ASIA")){
            return Country.Continent.ASIA;
        } else if(cont.equals("NORTHAMERICA")){
            return Country.Continent.NORTHAMERICA;
        }  else if(cont.equals("SOUTHAMERICA")){
            return Country.Continent.SOUTHAMERICA;
        } else if(cont.equals("EUROPE")){
            return Country.Continent.EUROPE;
        } else if(cont.equals("ANTARTICA")){
            return Country.Continent.ANTARTICA;
        } else if(cont.equals("AUSTRALIA")){
            return Country.Continent.AUSTRALIA;
        } else if(cont.equals("AFRICA")){
            return Country.Continent.AFRICA;
        } else {
            throw new Exception("invalid continent " + cont);
        }
    }


}
