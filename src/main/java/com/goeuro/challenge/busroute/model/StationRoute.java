package com.goeuro.challenge.busroute.model;


public class StationRoute {

    private Integer routeId; // BusRoute ID

    private int order; // order of the station within that route

    public StationRoute(Integer routeId, int order) {
        this.routeId = routeId;
        this.order = order;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public int getOrder() {
        return order;
    }
}
