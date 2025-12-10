package hust.soict.dsai.aims.media;

public abstract class Media {
    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    private static int idCounter = 0;

    // Cập nhật Constructor để kiểm tra ràng buộc Cost và sử dụng Exception Delegation
    public Media(String title, String category, float cost) throws IllegalArgumentException {
        // Ràng buộc: Cost phải là số dương
        if (cost <= 0) {
            throw new IllegalArgumentException("ERROR: Cost must be positive.");
        }

        this.id = ++idCounter;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }

    // Setters (Giữ nguyên, không cần kiểm tra exception ở đây)
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setCost(float cost) { this.cost = cost; }

    @Override
    public String toString() {
        return this.id + ". " + this.title + " - " + this.category + ": " + this.cost + "$";
    }

    // Triển khai phương thức equals() (yêu cầu)
    @Override
    public boolean equals(Object obj) {
        // 1. Kiểm tra đối tượng tham chiếu (Nếu là cùng một đối tượng)
        if (this == obj) {
            return true;
        }

        // 2. Kiểm tra NullPointerException và ClassCastException
        // Hai đối tượng bằng nhau nếu chúng là Media và có cùng title
        if (obj == null || !(obj instanceof Media media)) {
            return false;
        }

        // 3. So sánh theo tiêu chí (title), không phân biệt hoa thường
        return this.title.equalsIgnoreCase(media.title);
    }
}