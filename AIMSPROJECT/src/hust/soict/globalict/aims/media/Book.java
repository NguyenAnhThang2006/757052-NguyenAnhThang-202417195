package hust.soict.globalict.aims.media;

import java.util.ArrayList;

public class Book extends Media {

    private ArrayList<Author> authors = new ArrayList<>();

    public Book(String title, String category, float cost, ArrayList<Author> authors)
            throws IllegalArgumentException {
        super(title, category, cost);
        this.authors = authors;
    }

    public Book(String title, String category, float cost) throws IllegalArgumentException {
        super(title, category, cost);
    }


    public void addAuthor(Author author) {
        if (!authors.contains(author)) {
            authors.add(author);
        }
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
    }


    @Override
    public String toString() {
        StringBuilder authorList = new StringBuilder();
        if (authors.isEmpty()) {
            authorList.append("N/A");
        } else {
            for (Author author : authors) {
                authorList.append(author.getName()).append(", ");
            }
            authorList.setLength(authorList.length() - 2);
        }

        return super.toString() + " - Authors: [" + authorList.toString() + "]";
    }
}