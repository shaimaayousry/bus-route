package com.goeuro.challenge.busroute.model;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouteDataReaderTest {

    private static final String TEST_FILES_FOLDER = "src/test/resources/test-data/";

    @InjectMocks
    private RouteDataReader routeDataReader;

    @Mock
    private RouteRepository routeRepository;

    @Before
    public void setup() throws Exception {
        reset(routeRepository);
    }

    @Test(expected = FileNotFoundException.class)
    public void testReadBusRouteData_CaseFileNotExist() throws Exception {
        routeDataReader.readData(TEST_FILES_FOLDER + "data_invalid_path");
        assertTrue(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadBusRouteData_CaseInvalidContent1() throws Exception {
        routeDataReader.readData(TEST_FILES_FOLDER + "route_data_invalid_1");
        assertTrue(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadBusRouteData_CaseInvalidContent2() throws Exception {
        routeDataReader.readData(TEST_FILES_FOLDER + "route_data_invalid_2");
        assertTrue(false);
    }

    @Test
    public void testReadBusRouteData() throws Exception {
        routeDataReader.readData(TEST_FILES_FOLDER + "routes-data");
        verify(routeRepository, times(3)).addRoute(any(), anyList());
        verify(routeRepository, times(1)).addRoute(0, Arrays.asList(0, 1, 2, 3, 4));
        verify(routeRepository, times(1)).addRoute(1, Arrays.asList(3, 1, 6, 5));
        verify(routeRepository, times(1)).addRoute(2, Arrays.asList(0, 6, 4));

    }
}
