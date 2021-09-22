package com.Akanksha.CountryServices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Akanksha.CountryServices.beans.Country;

public interface CountryRepository extends JpaRepository<Country,Integer>{

}
