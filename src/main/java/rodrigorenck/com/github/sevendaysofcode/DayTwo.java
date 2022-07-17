package rodrigorenck.com.github.sevendaysofcode;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Day 1
 * Executar uma requisicao HTTP do tipo GET com a lista dos 250 filmes de maior nota no IMDB
 * Pegar a resposta (o JSON)
 * Imprimir o corpo da resposta no console
 */

public class DayOne {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient
                .newBuilder()
                .build();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI("https://imdb-api.com/en/API/Top250Movies/k_f24d3ik6"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        String jsonBody = response.body();
        String[] filmes = jsonBody.split("}");
        //criamos uma lista dos 250 filmes
        ArrayList<String> listFilmes = new ArrayList<>(Arrays.asList(filmes));

        listFilmes.forEach(System.out::println);


    }
}
