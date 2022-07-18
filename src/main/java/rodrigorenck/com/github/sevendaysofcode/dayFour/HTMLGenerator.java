package rodrigorenck.com.github.sevendaysofcode.dayFour;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {

    private static PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }


    public static void generate(List<Movie> movieList) {
        writer.println(
                """
                        <html>
                        		<head>
                        			<meta charset=\\"utf-8\\">
                        			<meta name=\\"viewport\\" content=\\"width=device-width, initial-scale=1, shrink-to-fit=no\\">
                        			<link rel=\\"stylesheet\\" href=\\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\\"\s
                        						+ "integrity=\\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\\" crossorigin=\\"anonymous\\">
                        						
                        		</head>
                        		<body>
                        """
        );

        for (Movie movie :
                movieList) {
            String div = """
                    <div class=\\"card text-white bg-dark mb-3\\" style=\\"max-width: 18rem;\\">
                    	<h4 class=\\"card-header\\">%s</h4>
                    	<div class=\\"card-body\\">
                    			<img class=\\"card-img\\" src=\\"%s\\" alt=\\"%s\\">
                    			<p class=\\"card-text mt-2\\">Rating: %s - Year: %s</p>
                    	</div>
                    </div>
                    """;
            writer.println(String.format(div, movie.getTitle(), movie.getUrlImage(), movie.getTitle(), movie.getRating(), movie.getYear()));
        }

        writer.println(
                """
                </body>
            </html"""
        );
    }
}

