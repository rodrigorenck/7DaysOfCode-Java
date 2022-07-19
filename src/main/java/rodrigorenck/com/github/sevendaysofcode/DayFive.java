package rodrigorenck.com.github.sevendaysofcode;

import java.io.PrintWriter;
import java.util.List;

/**
 * Day 5
 * Criar uma classe que faz o parseamento do JSON
 * Encapsular a chamada da API dento de uma nova classe - ImdbApiClient
 */

public class DayFive {

    public static void main(String[] args) throws Exception {
        String apiKey = "<sua chave>";
        String json = ImdbApiClient.generateJson(apiKey);
        List<Movie> movieList = ImdbMovieJsonParser.parse();
        PrintWriter writer = new PrintWriter("content.html");
        new HTMLGenerator(writer).generate(movieList);
        writer.close();
    }

}
