package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceTest {

    @Test
    void testRussianLocale() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    void testEnglishLocale() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Welcome", localizationService.locale(Country.USA));
    }
}