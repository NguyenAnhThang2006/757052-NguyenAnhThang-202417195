package hust.soict.dsai.aims.media;

import java.util.ArrayList;

// Lưu ý: Cần đảm bảo lớp Author tồn tại trong package hust.soict.dsai.aims.media
public class Book extends Media {

    // THAY ĐỔI: Sử dụng ArrayList<Author> thay vì ArrayList<String>
    private ArrayList<Author> authors = new ArrayList<>();

    // THAY ĐỔI QUAN TRỌNG: Thêm constructor nhận danh sách Author
    public Book(String title, String category, float cost, ArrayList<Author> authors)
            throws IllegalArgumentException {
        // Gọi constructor của Media (đã được cập nhật để kiểm tra cost)
        super(title, category, cost);
        this.authors = authors;
    }

    // Constructor cũ (giữ lại nếu cần)
    public Book(String title, String category, float cost) throws IllegalArgumentException {
        super(title, category, cost);
    }

    // --- Cập nhật phương thức thêm/xóa Author ---

    // Cập nhật để nhận đối tượng Author
    public void addAuthor(Author author) {
        if (!authors.contains(author)) {
            authors.add(author);
        }
    }

    // Cập nhật để nhận đối tượng Author
    public void removeAuthor(Author author) {
        authors.remove(author);
    }

    // --- Cập nhật toString() ---

    @Override
    public String toString() {
        StringBuilder authorList = new StringBuilder();
        if (authors.isEmpty()) {
            authorList.append("N/A");
        } else {
            for (Author author : authors) {
                authorList.append(author.getName()).append(", ");
            }
            // Xóa dấu phẩy và khoảng trắng cuối cùng
            authorList.setLength(authorList.length() - 2);
        }

        return super.toString() + " - Authors: [" + authorList.toString() + "]";
    }
}