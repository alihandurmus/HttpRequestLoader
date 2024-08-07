package org.example;

import java.net.http.HttpClient;

public class Client implements Runnable{
    private final HttpClient httpClient;
    private final String url;
    private final String request;

    public Client(HttpClient httpClient,String url, String request ) {
        this.httpClient = httpClient;
        this.url = url;
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("Client " + request + " started");
        Server.submitRequest(httpClient,url,request);

    }
}
