package com.goeuro.challenge.busroute.controller;


import com.goeuro.challenge.busroute.app.Application;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(properties = {"routesDataFilePath = src/test/resources/test-data/routes-data"})

public class RouteControllerTest {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}


	@Test
	public void testIsDirectBusRouteExists_WhenDirectRouteExists() throws Exception{

		mockMvc.perform(get("/api/direct")
				.param("dep_sid", String.valueOf(1))
				.param("arr_sid", String.valueOf(4))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.dep_sid", Matchers.is(1)))
				.andExpect(jsonPath("$.arr_sid", Matchers.is(4)))
				.andExpect(jsonPath("$.direct_bus_route", Matchers.is(true)));

	}

	@Test
	public void testIsDirectBusRouteExists_WhenDirectRouteNotExists() throws Exception{
		mockMvc.perform(get("/api/direct")
				.param("dep_sid", String.valueOf(2))
				.param("arr_sid", String.valueOf(1))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.dep_sid", Matchers.is(2)))
				.andExpect(jsonPath("$.arr_sid", Matchers.is(1)))
				.andExpect(jsonPath("$.direct_bus_route", Matchers.is(false)));
	}
}
