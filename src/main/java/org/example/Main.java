package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static int REQUEST_PER_SECOND = 1000;
    private static String URL = "http://localhost:8080/api/files/download/1";

    public static void main(String[] args) {

        HttpClient httpClient = HttpClient.newHttpClient();

        while (true) {
            //ExecutorService clientExecutor = Executors.newFixedThreadPool(100);
            var clientExecutor = Executors.newVirtualThreadPerTaskExecutor();
            for (int i = 0; i < REQUEST_PER_SECOND; i++) {
                Client client = new Client(httpClient,URL, "Request " + i);
                clientExecutor.submit(client);

            }
            clientExecutor.shutdown();
            try {
                clientExecutor.awaitTermination(1, TimeUnit.MINUTES);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    /*
    public static void sendGetRequest() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .GET()
                .build();
        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Respones:" + httpResponse.statusCode());
    }*/
}