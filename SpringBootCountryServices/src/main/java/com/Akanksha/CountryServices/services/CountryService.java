package com.Akanksha.CountryServices.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Akanksha.CountryServices.beans.Country;
import com.Akanksha.CountryServices.controllers.AddResponse;
import com.Akanksha.CountryServices.repositories.CountryRepository;

@Component
@Service
public class CountryService {
	
	@Autowired
	CountryRepository countryRep;
	public List<Country> getAllCountries() {
		return countryRep.findAll();
		
	}
	
	public Country getCountryById(int id) {
		List<Country> countries=countryRep.findAll();
		Country country=null;
		
		for(Country con:countries) {
			if(con.getId()==id)
				country=con;
		}
		return country;
	}
	
	public Country getCountryByName(String countryName) {
		
		List<Country> countries=countryRep.findAll();
		Country country=null;
		
		for(Country con:countries) {
			if(con.getCountryName().equalsIgnoreCase(countryName))
				country=con;
		}
		return country;
		
	}
	public Country addCountry(Country country) {
		int id=getMaxId();
		country.setId(id);
		countryRep.save(country);
		countryRep.getById(id);
 		return country;
	}
	
	public int getMaxId() {
		return countryRep.findAll().size()+1;
	}
	
	public Country updateCountry(Country country) {
		
		countryRep.save(country);
		return country;
		
	}
	public void deleteCountry(Country country) {
		countryRep.delete(country);
	}
}
