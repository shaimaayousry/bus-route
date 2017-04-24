package com.goeuro.challenge.busroute.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.goeuro.challenge.busroute" })
public class Application {


	public static void main(String[] args) {
		if (null == args || args.length == 0 || args[0].isEmpty()) {
			throw new IllegalArgumentException("BusRouteData file path is missing!");
		}
		System.setProperty("routesDataFilePath", args[0]);
		SpringApplication.run(Application.class, args);
	}
}
