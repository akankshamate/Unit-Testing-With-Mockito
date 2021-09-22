package com.Akanksha.CountryServices;

import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Akanksha.CountryServices.beans.Country;
import com.Akanksha.CountryServices.controllers.CountryController;

import com.Akanksha.CountryServices.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ControllerMockitoTests.class})
public class ControllerMockitoTests {
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	static CountryController countryController;
	
	static Country country;

	static private int countryId;
	
	/*@BeforeAll
	static void setUp() {
		
		System.out.println("startup");
		country =new Country(2,"USA","Washington");
		countryId = 2;
	}
	@AfterAll
	static void tearDown() {
		System.out.println("In tearDown:After Test");
	}*/
	
	@BeforeEach
    void setUp() {
		
		System.out.println("startup");
		country =new Country(2,"USA","Washington");
		countryId = 2;
	}
	
	@AfterEach
	void tearDown() {
		
		System.out.println("In tearDown:After Test");
	}
	
	@Test
	@Order(1)
	void test_getAllCountries() {
		
		List<Country> mycountries;
		mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(1,"USA","Washington"));
		
		when(countryService.getAllCountries()).thenReturn(mycountries);
		ResponseEntity <List<Country>> res =countryController.getCountries();
		
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(2,res.getBody().size());
		System.out.println("In getAllCountries() test method");
	}
	
	@Test
	@Order(2)
	void test_getCountryById() {
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res =countryController.getCountryById(countryId);
		
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(countryId,res.getBody().getId());
	}
	
	@Test
	@Order(3)
	void test_getCountryByName() {
	
		String countryName="USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> res =countryController.getCountryByName(countryName);
		
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(countryName,res.getBody().getCountryName());
	}
	
	@Test
	@Order(4)
	void test_addCountry() {
		
		when(countryService.addCountry(country)).thenReturn(country);
		ResponseEntity<Country> res =countryController.addCountry(country);
		
		assertEquals(HttpStatus.CREATED,res.getStatusCode());
		assertEquals(country,res.getBody());
	}
	
	@Test
	@Order(5)
	void test_updateCountry() {
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		when(countryService.updateCountry(country)).thenReturn(country);
		ResponseEntity<Country> res =countryController.updateCountry(countryId, country);
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(countryId,res.getBody().getId());
		assertEquals("USA",res.getBody().getCountryName());
		assertEquals("Washington",res.getBody().getCountryCapital());
		
	}
	
	@Test
	@Order(6)
	void test_deleteCountry() {
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res =countryController.deleteCountry(countryId);
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
	}
}
