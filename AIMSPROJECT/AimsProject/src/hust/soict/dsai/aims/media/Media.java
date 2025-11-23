package hust.soict.dsai.aims.media;

public abstract class Media {
    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    private static int idCounter = 0;

    public Media(String title, String category, float cost) {
        this.id = ++idCounter;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }

    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setCost(float cost) { this.cost = cost; }

    public String toString() {
        return this.id + ". " + this.title + " - " + this.category + ": " + this.cost + "$";
    }
}
