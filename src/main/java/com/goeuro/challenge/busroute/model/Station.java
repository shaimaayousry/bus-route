package com.goeuro.challenge.busroute.model;

import java.util.HashMap;
import java.util.Map;

public class Station {

	private Integer id; // station id

	private Map<Integer, StationRoute> routesMap; // routes passing by this station

	public Station(Integer id) {
		this.id = id;
		this.routesMap = new HashMap<>();
	}

	public void addRoute(Integer routeId, Integer order) {
		StationRoute stationRoute = new StationRoute(routeId, order);
		routesMap.put(routeId, stationRoute);
	}

	public Map<Integer, StationRoute> getRoutesMap() {
		return routesMap;
	}

}
