package com.goeuro.challenge.busroute.model;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {

    @InjectMocks
    private RouteService routeService;

    @Mock
    private RouteRepository routeRepository;


    @Test
    public void testIsDirectBusRouteExist() {
        when(routeRepository.isDirectRouteExists(1, 2)).thenReturn(false);
        when(routeRepository.isDirectRouteExists(2, 1)).thenReturn(true);


        assertFalse(routeService.isDirectBusRouteExists(1, 2)); //from repository
        assertFalse(routeService.isDirectBusRouteExists(1, 2)); //from cache
        assertTrue(routeService.isDirectBusRouteExists(2, 1)); //from repository
        assertTrue(routeService.isDirectBusRouteExists(2, 1)); //from cache


        verify(routeRepository, times(1)).isDirectRouteExists(1, 2);
        verify(routeRepository, times(1)).isDirectRouteExists(2, 1);

    }
}
