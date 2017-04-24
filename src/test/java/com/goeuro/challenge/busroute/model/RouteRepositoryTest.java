package com.goeuro.challenge.busroute.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class RouteRepositoryTest {


    private RouteRepository routeRepository;

    @Before
    public void setUp() throws Exception {
        routeRepository = new RouteRepository();
        routeRepository.addRoute(0, Arrays.asList(1, 2, 3));
        routeRepository.addRoute(1, Arrays.asList(2, 4, 1));
    }

    @Test
    public void testAddRoute() {

        Map<Integer, Station> stationsMap = routeRepository.getStationsMap();
        assertEquals(4, stationsMap.size());

        Station station1 = stationsMap.get(1);
        assertNotNull(station1);
        assertEquals(2, station1.getRoutesMap().size());
        assertEquals(0, station1.getRoutesMap().get(0).getOrder());
        assertEquals(2, station1.getRoutesMap().get(1).getOrder());

        Station station2 = stationsMap.get(2);
        assertNotNull(station2);
        assertEquals(2, station2.getRoutesMap().size());
        assertEquals(1, station2.getRoutesMap().get(0).getOrder());
        assertEquals(0, station2.getRoutesMap().get(1).getOrder());

        Station station3 = stationsMap.get(3);
        assertNotNull(station3);
        assertEquals(1, station3.getRoutesMap().size());
        assertEquals(2, station3.getRoutesMap().get(0).getOrder());

        Station station4 = stationsMap.get(4);
        assertNotNull(station4);
        assertEquals(1, station4.getRoutesMap().size());
        assertEquals(1, station4.getRoutesMap().get(1).getOrder());

    }

    @Test
    public void testIsDirectRouteExist_WhenExists() {

        assertTrue(routeRepository.isDirectRouteExists(1, 2));
        assertTrue(routeRepository.isDirectRouteExists(1, 3));
        assertTrue(routeRepository.isDirectRouteExists(2, 3));


        assertTrue(routeRepository.isDirectRouteExists(2, 4));
        assertTrue(routeRepository.isDirectRouteExists(2, 1));
        assertTrue(routeRepository.isDirectRouteExists(4, 1));
    }

    @Test
    public void testIsDirectRouteExist_WhenNoCommonRoutes() {
        assertFalse(routeRepository.isDirectRouteExists(1, 4));
        assertFalse(routeRepository.isDirectRouteExists(4, 3));
    }

    @Test
    public void testIsDirectRouteExist_WhenCommonRoutesButOppositeDirection() {
        assertFalse(routeRepository.isDirectRouteExists(1, 4));
        assertFalse(routeRepository.isDirectRouteExists(4, 3));
    }

    @Test
    public void testIsDirectRouteExist_WhenStationNotExists() {
        assertFalse(routeRepository.isDirectRouteExists(3, 1));
        assertFalse(routeRepository.isDirectRouteExists(3, 2));
        assertFalse(routeRepository.isDirectRouteExists(1, 4));
        assertFalse(routeRepository.isDirectRouteExists(4, 2));
    }


}
