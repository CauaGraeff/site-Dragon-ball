package Services;

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
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    
    private static final String API_URL = "https://dragonball-api.com/api/characters?name=";
    private final Gson gson;
    private final HttpClient httpClient;
    
    public CharacterService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.httpClient = HttpClient.newHttpClient();
    }
    
    public Titulo fetchCharacter(String name) throws IOException, InterruptedException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do personagem não pode estar vazio");
        }
        
        String url = API_URL + name;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new RuntimeException("Personagem não encontrado: " + name);
        }
        
        List<TituloRecord> records = gson.fromJson(response.body(), 
                new TypeToken<List<TituloRecord>>(){}.getType());
        
        if (records == null || records.isEmpty()) {
            throw new RuntimeException("Personagem não encontrado: " + name);
        }
        
        return new Titulo(records);
    }
}
