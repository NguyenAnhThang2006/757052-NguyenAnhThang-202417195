package hust.soict.dsai.aims.media;

public abstract class Disc extends Media {
    protected String director;
    protected int length;

    // Cập nhật Constructor để xử lý Exception Delegation
    public Disc(String title, String category, float cost, String director, int length)
            throws IllegalArgumentException {
        // Gọi constructor của Media (kiểm tra cost)
        super(title, category, cost);

        // Kiểm tra ràng buộc Length (length không được âm)
        if (length < 0) {
            throw new IllegalArgumentException("ERROR: Disc length cannot be negative.");
        }

        this.director = director;
        this.length = length;
    }

    public String getDirector() { return director; }
    public int getLength() { return length; }

    @Override
    public String toString() {
        // Sử dụng toString() của Media và bổ sung thông tin của Disc
        return super.toString() + " - " + director + " - " + length + " phút";
    }
}