import Modelos.Titulo;
import Modelos.TituloRecord;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do personagem que tu quer sobre ele:");
        String nomePersonagem = sc.nextLine();
        String endereco = "https://dragonball-api.com/api/characters?name=" + nomePersonagem;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);
        List<TituloRecord> tituloRecords = gson.fromJson(json, new TypeToken<List<TituloRecord>>(){}.getType());
        Titulo titulo = new Titulo(tituloRecords);
        System.out.println(titulo.toString());



    }
}