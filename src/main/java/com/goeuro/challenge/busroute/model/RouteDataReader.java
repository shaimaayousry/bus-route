package com.goeuro.challenge.busroute.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RouteDataReader {

	@Value("${routesDataFilePath}")
	private String routesDataFilePath;

	@Autowired
	private RouteRepository routeRepository;

	@PostConstruct
	protected void init() throws FileNotFoundException {
		this.readData(routesDataFilePath);
	}

	protected void readData(String filePath) throws FileNotFoundException {
		try {
			File file = new File(filePath);

			Scanner fileScanner = new Scanner(file);
			int numberOfRoutes = Integer.parseInt(fileScanner.nextLine());
			for (int i = 0; i < numberOfRoutes; i++) {
				String nextLine = fileScanner.nextLine();
				Scanner lineScanner = new Scanner(nextLine);

				Integer routeId = lineScanner.nextInt();
				List<Integer> stationsIds = new ArrayList<>();
				while (lineScanner.hasNext()) {
					stationsIds.add(new Integer(lineScanner.nextInt()));
				}

				routeRepository.addRoute(routeId, stationsIds);
			}
		} catch (InputMismatchException e) {
			throw new IllegalArgumentException(" Invalid ID Value, ID must be integer");
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(" Invalid ID Value, ID must be integer");
		}
	}

}
