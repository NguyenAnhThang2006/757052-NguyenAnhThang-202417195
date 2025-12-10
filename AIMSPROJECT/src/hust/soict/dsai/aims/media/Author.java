package hust.soict.dsai.aims.media;

public class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Thêm equals/hashcode nếu cần so sánh Author
}