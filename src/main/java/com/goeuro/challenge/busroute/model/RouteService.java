package com.goeuro.challenge.busroute.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

	@Autowired
	private RouteRepository busRoutesRepository;

	/**
	 * busRoutesCache caches the results of queries inquired on RouteRepository to avoid lookup the Repository if the
	 * same query was requested before. It can be enhanced by applying expiry mechanism, where entries are removed from
	 * cache if reached maximum life time or based on least usage.
	 */
	private Map<String, Boolean> busRoutesCache = new HashMap<>();

	public boolean isDirectBusRouteExists(int dep_sid, int arr_sid) {
		if (dep_sid == arr_sid) {
			return true;
		}

		Boolean cachedResult = findInCache(dep_sid, arr_sid);
		if (null != cachedResult) {
			return cachedResult;
		}

		boolean isDirectRouteExist = busRoutesRepository.isDirectRouteExists(dep_sid, arr_sid);
		addToCache(dep_sid, arr_sid, isDirectRouteExist);
		return isDirectRouteExist;
	}

	private Boolean findInCache(int dep_sid, int arr_sid) {
		return busRoutesCache.get(getCacheUniqueKey(dep_sid, arr_sid));
	}

	private Boolean addToCache(int dep_sid, int arr_sid, Boolean isDirectRouteExist) {
		return busRoutesCache.put(getCacheUniqueKey(dep_sid, arr_sid), isDirectRouteExist);
	}

	private String getCacheUniqueKey(int dep_sid, int arr_sid) {
		return dep_sid + "-" + arr_sid;
	}

}
