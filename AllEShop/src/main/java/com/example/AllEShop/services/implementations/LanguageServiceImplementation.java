package com.example.AllEShop.services.implementations;

import com.example.AllEShop.entities.Language;
import com.example.AllEShop.repositories.BrandRepository;
import com.example.AllEShop.repositories.LanguageRepository;
import com.example.AllEShop.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImplementation implements LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public Language addLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language getLanguage(Long id) {
        return languageRepository.getOne(id);
    }

    @Override
    public void deleteLanguage(Language language) {
        languageRepository.delete(language);
    }

    @Override
    public void deleteLanguageById(Long id) {
        languageRepository.deleteById(id);
    }

    @Override
    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }
}
