package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Media {

    private String director;
    private int length;

    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title) {
        super(title, null, 0f);
        nbDigitalVideoDiscs++;
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        super(title, category, cost);
        nbDigitalVideoDiscs++;
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super(title, category, cost);
        this.director = director;
        nbDigitalVideoDiscs++;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
        nbDigitalVideoDiscs++;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DigitalVideoDisc)) return false;
        DigitalVideoDisc other = (DigitalVideoDisc) obj;
        return getTitle() != null && getTitle().equalsIgnoreCase(other.getTitle());
    }

    public int hashCode() {
        return getTitle() == null ? 0 : getTitle().toLowerCase().hashCode();
    }

    public String toString() {
        return "DVD - "
                + getTitle() + " - "
                + getCategory() + " - "
                + director + " - "
                + length + ": "
                + getCost() + " $";
    }

    public boolean isMatch(String title) {
        if (getTitle() == null || title == null) return false;
        return getTitle().equalsIgnoreCase(title);
    }
}
