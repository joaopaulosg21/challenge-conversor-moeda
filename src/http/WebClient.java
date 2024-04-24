package http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import env.ApiKey;
import model.ExchangeResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebClient {
    private String path;

    private final ApiKey apiKey;

    private final String defaultPath;

    private HttpRequest request;

    public WebClient() {
        this.apiKey = new ApiKey();
        this.defaultPath = "https://v6.exchangerate-api.com/v6/" + apiKey.getKey()+"/pair/";
        this.path = defaultPath;
    }


    public ExchangeResponse converter(int opcao,double valor) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String body = "";
        switch (opcao) {
            case 1:
                this.changePath("BRL","ARS",valor);
                this.setRequest();
                body = client.send(this.request, HttpResponse.BodyHandlers.ofString()).body();
                return gson.fromJson(body,ExchangeResponse.class);
            case 2:
                this.changePath("ARS","BRL",valor);
                this.setRequest();
                body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
                return gson.fromJson(body,ExchangeResponse.class);
            case 3:
                this.changePath("USD","COP",valor);
                this.setRequest();
                body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
                return gson.fromJson(body,ExchangeResponse.class);
            case 4:
                this.changePath("COP","USD",valor);
                this.setRequest();
                body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
                return gson.fromJson(body,ExchangeResponse.class);
            case 5:
                this.changePath("CLP","BOB",valor);
                this.setRequest();
                body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
                return gson.fromJson(body,ExchangeResponse.class);
            case 6:
                this.changePath("BOB","CLP",valor);
                this.setRequest();
                body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
                return gson.fromJson(body,ExchangeResponse.class);
        }
        return null;
    }

    private void changePath(String base, String target,double valor) {
        this.path = defaultPath + base + "/" + target + "/" + valor;
    }

    private void setRequest() {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.path))
                .GET()
                .build();
    }
}
