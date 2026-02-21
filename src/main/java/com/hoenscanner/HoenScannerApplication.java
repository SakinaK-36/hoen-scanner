package com.hoenscanner;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.hoenscanner.SearchResult;
import com.hoenscanner.SearchResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {

    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) throws Exception {
        // Create a list to hold all search results
        List<SearchResult> searchResults = new ArrayList<>();

        // Create ObjectMapper for JSON processing
        ObjectMapper mapper = new ObjectMapper();

        // Load and process hotels.json
        InputStream hotelsStream = HoenScannerApplication.class.getResourceAsStream("/hotels.json");
        List<SearchResult> hotels = mapper.readValue(hotelsStream,
                mapper.getTypeFactory().constructCollectionType(List.class, SearchResult.class));

        // Set kind="hotel" for each hotel result
        for (SearchResult hotel : hotels) {
            hotel.setKind("hotel");
            searchResults.add(hotel);
        }

        // Load and process rental_cars.json
        InputStream carsStream = HoenScannerApplication.class.getResourceAsStream("/rental_cars.json");
        List<SearchResult> cars = mapper.readValue(carsStream,
                mapper.getTypeFactory().constructCollectionType(List.class, SearchResult.class));

        // Set kind="rental-car" for each car result
        for (SearchResult car : cars) {
            car.setKind("rental-car");
            searchResults.add(car);
        }

        // Print to verify (optional - can remove later)
        System.out.println("Loaded " + searchResults.size() + " total results:");
        for (SearchResult result : searchResults) {
            System.out.println(result.getCity() + " - " + result.getKind() + " - " + result.getTitle());
        }

        // Register the SearchResource with the searchResults list
        environment.jersey().register(new SearchResource(searchResults));
    }

}
