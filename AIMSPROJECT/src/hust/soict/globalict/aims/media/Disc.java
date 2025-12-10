package hust.soict.globalict.aims.media;

public abstract class Disc extends Media {
    protected String director;
    protected int length;

    public Disc(String title, String category, float cost, String director, int length)
            throws IllegalArgumentException {
        super(title, category, cost);

        if (length < 0) {
            throw new IllegalArgumentException("ERROR: Disc length cannot be negative.");
        }

        this.director = director;
        this.length = length;
    }

    public String getDirector() { return director; }
    public int getLength() { return length; }

    @Override
    public String toString() {
        return super.toString() + " - " + director + " - " + length + " minutes";
    }
}
