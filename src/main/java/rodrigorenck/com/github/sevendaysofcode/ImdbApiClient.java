package rodrigorenck.com.github.sevendaysofcode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient {

    //Metodo que faz a ligacao com a API e devolve o Json em String dos top250Movies
    public static String generateJson(String apiKey) throws Exception{
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://imdb-api.com/en/API/Top250Movies/" + apiKey)).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
