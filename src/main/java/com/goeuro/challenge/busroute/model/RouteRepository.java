package com.goeuro.challenge.busroute.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RouteRepository {

	/**
	 * stationsMap is Map of (StationID, Station)</br>
	 * we don't need to use concurrency map as building the map route is done before the accessing it.
	 */
	private Map<Integer, Station> stationsMap = new HashMap<>();

	public void addRoute(Integer routeId, List<Integer> stationsIds) {
		for (int i = 0; i < stationsIds.size(); i++) {
			Station station;
			Integer stationId = stationsIds.get(i);
			station = stationsMap.get(stationId);
			if (station == null) {
				station = new Station(stationId);
				stationsMap.put(stationId, station);
			}
			station.addRoute(routeId, i);
		}
	}

	/**
	 * Returns Boolean indicates whether their is a direct route between the given station.</br>
	 * There exists a direct route between two stations if there is a common route between them and it passes by the
	 * departure station before the arrival station.(i.e. order of depStation in the route is less than the order of the
	 * arrival station in the same route)
	 * 
	 * @param depStationId
	 *            Departure Station ID
	 * @param arrStationId
	 *            Arrival Station ID
	 * @return true if both stations existing in stationsMaps and there is direct route between the given stations,
	 *         false otherwise
	 */
	public boolean isDirectRouteExists(Integer depStationId, Integer arrStationId) {

		Station depStation = stationsMap.get(depStationId);
		if (depStation == null) {
			return false;
		}

		Station arrStation = stationsMap.get(arrStationId);
		if (arrStation == null) {
			return false;
		}

		Map<Integer, StationRoute> arrRoutes = arrStation.getRoutesMap();
		boolean anyMatch =
				depStation.getRoutesMap().values().stream()
				.filter(d -> arrRoutes.containsKey(d.getRouteId()))
				.anyMatch(d -> d.getOrder() < arrRoutes.get(d.getRouteId()).getOrder());

		return anyMatch;
	}

	protected Map<Integer, Station> getStationsMap() {
		return stationsMap;
	}

}
