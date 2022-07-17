package rodrigorenck.com.github.sevendaysofcode.dayThree;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Day 3
 * Modelagem do projeto
 * Criar a classe movie que possui title, urlImage, rating, year
 * Usar uma lista de Movie
 */

public class DayThree {
    private static String regex;
    private static List<String> listaFilmes;

    public static void main(String[] args) throws Exception {
        //Inicializando
        String jsonBody = generateJson();
        String[] filmes = jsonBody.split("}");
        listaFilmes = fixList(filmes);
        regex = "\"" + "," + "\"";

        List<Movie> movieList = createMovieList();
        movieList.forEach(System.out::println);
    }

    //metodo para criar nossa lista de objetos Movie
    private static List<Movie> createMovieList(){
        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i<listaFilmes.size(); i++){
            Movie movie = new Movie(parseTitle().get(i), parseUrlImages().get(i), parseRating().get(i), parseYear().get(i));
            movieList.add(movie);
        }
        return movieList;
    }

    private static List<String> parseTitle(){
        List<String> listTitle = new ArrayList<>();
        loopTheList(2, 8, listTitle);
        return listTitle;
    }

    private static List<Integer> parseYear(){
        List<String> listYears = new ArrayList<>();
        loopTheList(4, 7, listYears);
        List<Integer> listYearsInteger = listYears.stream().map(value -> Integer.valueOf(value)).collect(Collectors.toList());
        return listYearsInteger;
    }

    private static List<String> parseUrlImages(){
        List<String> listUrlImages = new ArrayList<>();
        loopTheList(5, 8, listUrlImages);
        return listUrlImages;
    }

    private static List<Double> parseRating(){
        List<String> listRating = new ArrayList<>();
        loopTheList(7, 13, listRating);
        List<Double> listRatingDouble = listRating.stream().map(value -> Double.valueOf(value)).collect(Collectors.toList());
        return listRatingDouble;
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
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://imdb-api.com/en/API/Top250Movies/k_f24d3ik6")).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    //metodo para remover o "items" do primeiro elemento e remover o "error" que eh o ultimo elemento
    //deixa nossa lista apenas com os 250 filmes - mais consistente
    private static List<String> fixList(String[] listaOriginal){
        listaFilmes = new ArrayList<>(Arrays.asList(listaOriginal));

        listaFilmes.remove(listaFilmes.size()-1);
        String primeiroElemento = listaFilmes.get(0);

        String primeiroElementoCerto = primeiroElemento.substring(8);
        listaFilmes.set(0, primeiroElementoCerto);

        return listaFilmes;
    }

}
