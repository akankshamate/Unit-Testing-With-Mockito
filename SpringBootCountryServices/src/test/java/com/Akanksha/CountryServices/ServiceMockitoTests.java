package com.Akanksha.CountryServices;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import com.Akanksha.CountryServices.beans.Country;
import com.Akanksha.CountryServices.repositories.CountryRepository;
import com.Akanksha.CountryServices.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ServiceMockitoTests.class})
public class ServiceMockitoTests {

	@Mock
	CountryRepository countryRep;
	
	@InjectMocks
	CountryService countryService;
	
	public List<Country> myCountries;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		List<Country> myCountries=new ArrayList<Country>();
		myCountries.add(new Country(1,"India","Delhi"));
		myCountries.add(new Country(2,"USA","Washingtone"));
		
		when(countryRep.findAll()).thenReturn(myCountries);
		assertEquals(2,countryService.getAllCountries().size());
	}
	@Test
	@Order(2)
	public void test_getCountryByID() {
		
		List<Country> myCountries=new ArrayList<Country>();
		myCountries.add(new Country(1,"India","Delhi"));
		myCountries.add(new Country(2,"USA","Washingtone"));
		int countryId=1;
		when(countryRep.findAll()).thenReturn(myCountries);
		assertEquals(countryId,countryService.getCountryById(countryId).getId());
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		
		List<Country> myCountries=new ArrayList<Country>();
		myCountries.add(new Country(1,"India","Delhi"));
		myCountries.add(new Country(2,"USA","Washingtone"));
		String countryName="India";
		when(countryRep.findAll()).thenReturn(myCountries);
		assertEquals(countryName,countryService.getCountryByName(countryName).getCountryName());
	}
	
	@Test
	@Order(4)
	public void test_addCountry() {
		Country country=new Country(3,"Germany","Berlin");
		when(countryRep.save(country)).thenReturn(country);
		assertEquals(country,countryService.addCountry(country));
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() {
		Country country=new Country(3,"Germany","Berlin");
		when(countryRep.save(country)).thenReturn(country);
		assertEquals(country,countryService.updateCountry(country));
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() {
		Country country=new Country(3,"Germany","Berlin");
		countryService.deleteCountry(country);
		verify(countryRep,times(1)).delete(country);
	}
}
