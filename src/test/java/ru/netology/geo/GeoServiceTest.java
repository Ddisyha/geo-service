package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceTest {

    @Test
    void testRussiaIp() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.33.12.19");
        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    void testUsaIp() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("96.44.183.149");
        assertEquals(Country.USA, location.getCountry());
    }
}