package rodrigorenck.com.github.sevendaysofcode.imdb;

import rodrigorenck.com.github.sevendaysofcode.JsonParser;
import rodrigorenck.com.github.sevendaysofcode.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImdbMovieJsonParser implements JsonParser {

    //not the real key
    private static String apiKey = "<your apiKey>";

    private static List<String> movieListString;
    private static String regex = "\"" + "," + "\"";

    //method that creates the movie List
    public List<Movie> parse(){
        try{
            String body = ImdbApiClient.generateJson(apiKey);
            String[] movies = body.split("}");
            movieListString = fixList(movies);
            return createMovieList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //metodo para remover o "items" do primeiro elemento e remover o "error" que eh o ultimo elemento
    //deixa nossa lista apenas com os 250 filmes - mais consistente
    private static List<String> fixList(String[] listaOriginal) {
        movieListString = new ArrayList<>(Arrays.asList(listaOriginal));

        movieListString.remove(movieListString.size() - 1);
        String primeiroElemento = movieListString.get(0);

        String primeiroElementoCerto = primeiroElemento.substring(8);
        movieListString.set(0, primeiroElementoCerto);
        return movieListString;
    }

    //metodo para criar nossa lista de objetos Movie
    private static List<Movie> createMovieList() {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < movieListString.size(); i++) {
            Movie movie = new Movie(parseTitle().get(i), parseUrlImages().get(i), parseRating().get(i), parseYear().get(i));
            movieList.add(movie);
        }
        return movieList;
    }

    private static List<String> parseTitle() {
        List<String> listTitle = new ArrayList<>();
        loopTheList(2, 8, listTitle);
        return listTitle;
    }

    private static List<String> parseYear() {
        List<String> listYears = new ArrayList<>();
        loopTheList(4, 7, listYears);
        return listYears;
    }

    private static List<String> parseUrlImages() {
        List<String> listUrlImages = new ArrayList<>();
        loopTheList(5, 8, listUrlImages);
        return listUrlImages;
    }

    private static List<String> parseRating() {
        List<String> listRating = new ArrayList<>();
        loopTheList(7, 13, listRating);
        return listRating;
    }

    //metodo que vai passar pela lista de filmes, separar pelo regex e pegar o atributo solicitado (passando a posicao do atributo)
    private static void loopTheList(int posicaoDoAtributo, int indexInicio, List<String> list) {
        for (String filme :
                movieListString) {
            String[] atributos = filme.split(regex);
            String isolaAtributo = atributos[posicaoDoAtributo].substring(indexInicio);
            list.add(isolaAtributo);
        }
    }
}
