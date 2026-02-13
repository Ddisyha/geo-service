package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderTest {

    @Test
    void sendMessageRussian() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        String ip = "172.123.45.67";

        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 10));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = sender.send(headers);
        assertEquals("Добро пожаловать", result);
    }

    @Test
    void sendMessageEnglish() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        String ip = "96.44.183.149";

        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = sender.send(headers);
        assertEquals("Welcome", result);
    }

    @Test
    void geoServiceRussia() {
        GeoService geoService = new GeoServiceImpl();
        assertEquals(Country.RUSSIA, geoService.byIp("172.33.12.19").getCountry());
    }

    @Test
    void geoServiceUSA() {
        GeoService geoService = new GeoServiceImpl();
        assertEquals(Country.USA, geoService.byIp("96.44.183.149").getCountry());
    }

    @Test
    void localizationRussian() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    void localizationEnglish() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals("Welcome", localizationService.locale(Country.USA));
    }

    @Test
    void send() {
    }

    @Test
    void testSend() {
    }
}