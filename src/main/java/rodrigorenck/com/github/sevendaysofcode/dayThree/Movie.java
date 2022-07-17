package rodrigorenck.com.github.sevendaysofcode.dayThree;

public class Movie {
    private String title;
    private String urlImage;
    private double rating;
    private int year;

    public Movie(String title, String urlImage, double rating, int year) {
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

    public double getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }
}
