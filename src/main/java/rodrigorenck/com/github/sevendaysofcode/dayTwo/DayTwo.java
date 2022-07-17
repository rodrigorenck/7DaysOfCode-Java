package rodrigorenck.com.github.sevendaysofcode.dayTwo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Day 2
 * Criar uma lista para cada atributo do filme
 * Atributos: id, rank, title, fullTitle, year, image, crew, imDbRating, imDbRatingCOunt
 */

public class DayTwo {
    private static String regex;
    private static List<String> listaFilmes;

    public static void main(String[] args) throws Exception {
        //Inicializando
        String jsonBody = generateJson();
        String[] filmes = jsonBody.split("}");
        listaFilmes = arrumaPrimeiroElementoLista(filmes);
        regex = "\"" + "," + "\"";

        //Criando uma lista para cada atributo - id, rank, title, year, urlImage, rating
        List<String> ids = parseId();
        List<String> ranks = parseRank();
        List<String> titles = parseTitle();
        List<String> years = parseYear();
        List<String> urlImages = parseUrlImages();
        List<String> ratings = parseRating();

        List<List<String>> listaDeAtributos = new ArrayList<>(Arrays.asList(ids, ranks, titles, years, urlImages, ratings));

        //para verificar que todas as listas possuem o mesmo tamanho
        listaDeAtributos.forEach(list -> {
            System.out.println(list.size());
        });

        //para verificar que os atributos do primeiro filme estao corretos
        String primeiro = ids.get(0) + " " + ranks.get(0) + " " + titles.get(0) + " " + ratings.get(0);
        System.out.println(primeiro);
    }


    private static List<String> parseId(){
        List<String> listId = new ArrayList<>();
        loopTheList(0, 9, listId);
        return listId;
    }

    private static List<String> parseRank(){
        List<String> listRank = new ArrayList<>();
        loopTheList(1, 7, listRank);
        return listRank;
    }

    private static List<String> parseTitle(){
        List<String> listTitle = new ArrayList<>();
        loopTheList(2, 8, listTitle);
        return listTitle;
    }

    private static List<String> parseYear(){
        List<String> listYears = new ArrayList<>();
        loopTheList(4, 7, listYears);
        return listYears;
    }

    private static List<String> parseUrlImages(){
        List<String> listUrlImages = new ArrayList<>();
        loopTheList(5, 8, listUrlImages);
        return listUrlImages;
    }

    private static List<String> parseRating(){
        List<String> listRating = new ArrayList<>();
        loopTheList(7, 13, listRating);
        return listRating;
    }

    //metodo que vai passar pela lista de filmes, separar pelo regex e pegar o atributo solicitado (passando a posicao do atributo)
    private static void loopTheList(int posicaoDoAtributo, int indexInicio, List<String> list) {
        for (String filme :
                listaFilmes) {
            String[] atributos = filme.split(regex);
            String isolaAtributo = atributos[posicaoDoAtributo].substring(indexInicio);
            list.add(isolaAtributo);
        }
    }



    //metodo que retorna o corpo do json em String
    private static String generateJson() throws Exception{
        HttpClient client = HttpClient
                .newBuilder()
                .build();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI("https://imdb-api.com/en/API/Top250Movies/k_f24d3ik6"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    //metodo para remover o "items" do primeiro elemento e remover o "error" que eh o ultimo elemento
    //deixa nossa lista apenas com os 250 filmes - mais consistente
    private static List<String> arrumaPrimeiroElementoLista(String[] listaOriginal){
        listaFilmes = new ArrayList<>(Arrays.asList(listaOriginal));

        listaFilmes.remove(listaFilmes.size()-1);
        String primeiroElemento = listaFilmes.get(0);

        String primeiroElementoCerto = primeiroElemento.substring(8);
        listaFilmes.set(0, primeiroElementoCerto);

        return listaFilmes;
    }

}
