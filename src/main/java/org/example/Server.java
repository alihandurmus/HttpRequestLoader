package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void submitRequest(HttpClient httpClient, String url, String request) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Respones:" + httpResponse.statusCode()+" "+request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
