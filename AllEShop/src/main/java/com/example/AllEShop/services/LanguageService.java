package com.example.AllEShop.services;

import com.example.AllEShop.entities.Language;

import java.util.List;

public interface LanguageService {
    Language addLanguage(Language language);
    List<Language> getAllLanguages();
    Language getLanguage(Long id);
    void deleteLanguage(Language language);
    void deleteLanguageById(Long id);
    Language saveLanguage(Language language);
}
