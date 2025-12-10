package hust.soict.dsai.aims.media;

public abstract class Disc extends Media {
    protected String director;
    protected int length;

    // Constructor đầy đủ (có thể dùng cho DigitalVideoDisc)
    public Disc(String title, String category, float cost, String director, int length) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }

    // Constructor không có length (thích hợp cho CompactDisc khi length được tính)
    public Disc(String title, String category, float cost, String director) {
        super(title, category, cost);
        this.director = director;
        this.length = 0; // Khởi tạo length mặc định là 0
    }

    public String getDirector() { return director; }

    // Giữ nguyên phương thức này, cho phép các lớp con override để tính toán lại (như CompactDisc)
    public int getLength() { return length; }
}