package rodrigorenck.com.github.sevendaysofcode.dayFour;

public class Movie {
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

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }
}
