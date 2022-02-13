package com.sinha.learn;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {
        try{
            asynchronousGetRequest();
        }catch (URISyntaxException e){
            e.getMessage();
        }


        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .uri(URI.create("http://jsonplaceholder.typicode.com/posts/1"))
                    .header("Accept-Encoding","gzip, deflate")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            int responseCode = response.statusCode();
            System.out.println("response code :" + responseCode);
            System.out.println("response body :" + responseBody);
        }catch (InterruptedException e){
            e.getMessage();
        }catch (IOException e){
            e.getMessage();
        }
    }
    public static void asynchronousGetRequest() throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        //URI httpURI = new URI("http://jsonplaceholder.typicode.com/posts/1");
        URI httpURI = new URI("https://community-open-weather-map.p.rapidapi.com/weather");
        HttpRequest request = HttpRequest.newBuilder(httpURI)
                .version(HttpClient.Version.HTTP_2)
                .build();
        CompletableFuture<Void> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofLines())
                .thenAccept(resp -> {
                    System.out.println("Got pushed response " + resp.uri());
                    System.out.println("Response statuscode: " + resp.statusCode());
                    System.out.println("Response body: " + resp.body());
                });
        System.out.println("futureResponse" + futureResponse);
    }
}
