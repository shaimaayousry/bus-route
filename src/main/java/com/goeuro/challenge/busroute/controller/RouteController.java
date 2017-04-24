package com.goeuro.challenge.busroute.controller;

import com.goeuro.challenge.busroute.model.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

	@Autowired
	private RouteService routeService;

	@RequestMapping(value = "/api/direct", method = RequestMethod.GET)
	public DirectRouteResponse isDirectBusRouteExists(@RequestParam Integer dep_sid,
													  @RequestParam Integer arr_sid) {

		boolean isDirectBusRoute = routeService.isDirectBusRouteExists(dep_sid, arr_sid);

		return new DirectRouteResponse(dep_sid, arr_sid, isDirectBusRoute);

	}

}
