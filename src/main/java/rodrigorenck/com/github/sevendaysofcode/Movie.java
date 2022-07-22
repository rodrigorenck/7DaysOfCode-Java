package rodrigorenck.com.github.sevendaysofcode;

public class Movie implements Content, Comparable<Content>{
    private String title;
    private String urlImage;
    private String rating;
    private String year;

    public Movie(String title, String urlImage, String rating, String year) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
        this.year = year;
    }


    @Override
    public String toString() {
        String format = String.format("[%s] year:%d rating:%.1f url: %s", title, year, rating, urlImage);
        return format;
    }

    @Override
    public int compareTo(Content o) {
        return this.rating.compareTo(o.rating());
    }

    public String title() {
        return title;
    }

    public String urlImage() {
        return urlImage;
    }

    public String rating() {
        return rating;
    }

    public String year() {
        return year;
    }
}
