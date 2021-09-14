package org.uran.practice.springboot.soapconsumer;

import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.wsdl.GetCountryResponse;

@SpringBootApplication
public class SoapConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapConsumerApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient countryClient) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            System.err.println("Enter country: ");
            input = scanner.nextLine();
            if ("quit".equalsIgnoreCase(input)) {
                break;
            }
            System.err.println("Input: " + input);
            String country = input;
            GetCountryResponse response = countryClient.getCountry(country);
            if (response.getCountry() != null) {
                System.err.println("Capital: " + response.getCountry().getCapital());
                System.err.println("Currency: " + response.getCountry().getCurrency());
            } else {
                System.err.println("Country \"" + country + "\" not found!");
            }
        }
        return null;
    }

}
