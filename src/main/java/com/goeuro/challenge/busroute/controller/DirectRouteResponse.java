package com.goeuro.challenge.busroute.controller;

public class DirectRouteResponse {

	private int dep_sid;
	private int arr_sid;
	private boolean direct_bus_route;

	public DirectRouteResponse(int dep_sid, int arr_sid, boolean direct_bus_route) {
		this.setArr_sid(arr_sid);
		this.setDep_sid(dep_sid);
		this.setDirect_bus_route(direct_bus_route);
	}

	public int getDep_sid() {
		return dep_sid;
	}

	public void setDep_sid(int dep_sid) {
		this.dep_sid = dep_sid;
	}

	public int getArr_sid() {
		return arr_sid;
	}

	public void setArr_sid(int arr_sid) {
		this.arr_sid = arr_sid;
	}

	public boolean isDirect_bus_route() {
		return direct_bus_route;
	}

	public void setDirect_bus_route(boolean direct_bus_route) {
		this.direct_bus_route = direct_bus_route;
	}
}
