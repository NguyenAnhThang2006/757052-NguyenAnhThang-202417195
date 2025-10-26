package hust.soict.dsai.aims.media;

public class DigitalVideoDisc {

    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    private int id; // instance member (unique per DVD)

    // Class variable (shared by all instances)
    private static int nbDigitalVideoDiscs = 0;

    // === Constructors ===
    public DigitalVideoDisc(String title) {
        this.title = title;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        this.category = category;
        this.title = title;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        this.director = director;
        this.category = category;
        this.title = title;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    // === Getters ===
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    // === Static getter for class variable ===
    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    // === Display info ===
    public void printInfo() {
        System.out.println("DVD ID: " + id + " | Title: " + title + " | Cost: $" + cost);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DigitalVideoDisc)) return false;
        DigitalVideoDisc other = (DigitalVideoDisc) obj;
        return title != null && title.equalsIgnoreCase(other.title);
    }

    @Override
    public int hashCode() {
        return title == null ? 0 : title.toLowerCase().hashCode();
    }
}
