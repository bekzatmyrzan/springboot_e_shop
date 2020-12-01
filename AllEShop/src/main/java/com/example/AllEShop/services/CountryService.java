package com.example.AllEShop.services;

import com.example.AllEShop.entities.Country;

import java.util.List;

public interface CountryService {
    Country addCountry(Country country);
    List<Country> getAllCountries();
    Country getCountry(Long id);
    void deleteCountry(Country country);
    void deleteCountryById(Long id);
    Country saveCountry(Country country);
}
