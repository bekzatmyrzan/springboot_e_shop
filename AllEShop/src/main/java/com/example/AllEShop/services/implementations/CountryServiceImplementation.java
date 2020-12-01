package com.example.AllEShop.services.implementations;

import com.example.AllEShop.entities.Country;
import com.example.AllEShop.repositories.CountryRepository;
import com.example.AllEShop.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImplementation implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountry(Long id) {
        return countryRepository.getOne(id);
    }

    @Override
    public void deleteCountry(Country country) {
        countryRepository.delete(country);
    }

    @Override
    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }
}
