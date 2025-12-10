package hust.soict.globalict.aims.media;

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

    @Override
    public String toString() {
        return id + ". " + title + " - " + category + ": $" + cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Media other)) return false;
        return this.title.equalsIgnoreCase(other.title);
    }
}
