package rodrigorenck.com.github.sevendaysofcode;

import rodrigorenck.com.github.sevendaysofcode.imdb.ImdbApiClient;
import rodrigorenck.com.github.sevendaysofcode.imdb.ImdbMovieJsonParser;

import java.io.PrintWriter;
import java.util.List;

/**
 * Day 6
 * Deixar o codigo mais generico para que ele possa receber dados de outras APIs
 * Criar duas abstracoes -> Content e JsonParser
 */

public class DaySix {

    public static void main(String[] args) throws Exception {
        String apiKey = "<sua chave>";
        //1- Chamar a API
        String json = ImdbApiClient.generateJson(apiKey);
        //2- Parsear o Json
        ImdbMovieJsonParser jsonParser = new ImdbMovieJsonParser();
        List<Movie> movieList = jsonParser.parse();
        //3- Gerar o HTML
        PrintWriter writer = new PrintWriter("content.html");
        new HTMLGenerator(writer).generate(movieList);
        writer.close();
    }

}
