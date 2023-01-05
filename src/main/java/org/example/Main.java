package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hey! Input 3 letter index of currency you want to convert\n" +
                "(for example UAH): ");
        String from = scanner.next();

        System.out.println("Alright, to what currency you want to convert " + from + "?\n" +
                "Type 3 letter index: ");
        String to = scanner.next();

        System.out.println("How much " + from + " you want to convert?\n" +
                "Input there:");
        int amount = scanner.nextInt();

        final String REQUEST_URL = "https://api.apilayer.com/currency_data/convert?to=" + to
                + "&from=" + from
                + "&amount=" + amount;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(REQUEST_URL))
                .header("apikey", ApiKeys.CURRENCY_API.toString())
                .GET()
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());


        ObjectMapper mapper = new ObjectMapper();

        var jsonResult = mapper.readValue(response.body(), CurrencyValue.class);

        System.out.println("Result of converting is: " + jsonResult.getResult());
    }
}