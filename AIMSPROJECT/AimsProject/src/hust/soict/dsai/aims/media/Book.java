package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<>(); // Thay ArrayList bằng List interface

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    // Thêm constructor đầy đủ hơn để sử dụng trong Aims.java static block
    public Book(String title, String category, float cost, List<String> authors) {
        super(title, category, cost);
        if (authors != null) {
            this.authors = new ArrayList<>(authors);
        }
    }

    // Setter/Getter cho authors (cần thiết cho một số logic quản lý)
    public List<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String authorName) {
        // Kiểm tra xem tác giả đã có trong danh sách chưa trước khi thêm
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Author added: " + authorName);
        } else {
            System.out.println("Author is already listed: " + authorName);
        }
    }

    public void removeAuthor(String authorName) {
        // Kiểm tra xem tác giả có tồn tại trong danh sách không trước khi xóa
        if (authors.remove(authorName)) {
            System.out.println("Author removed: " + authorName);
        } else {
            System.out.println("Author not found in list: " + authorName);
        }
    }

    // Kế thừa logic equals() từ Media (so sánh theo title)

    @Override
    public String toString() {
        return "Book - " + super.toString() + " - Authors: " + authors.toString();
    }
}