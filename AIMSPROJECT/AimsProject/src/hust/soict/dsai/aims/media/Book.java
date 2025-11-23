package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<>();

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) authors.add(authorName);
    }

    public void removeAuthor(String authorName) {
        authors.remove(authorName);
    }

    public String toString() {
        return super.toString() + " - Authors: " + authors;
    }
}
