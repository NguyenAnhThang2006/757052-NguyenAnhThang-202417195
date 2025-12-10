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

    // Giữ các setter cho mục đích GUI/Refactor nếu cần, nhưng nên xem xét loại bỏ trong thực tế
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setCost(float cost) { this.cost = cost; }

    @Override
    public String toString() {
        return this.id + ". " + this.title + " - " + this.category + ": " + this.cost + "$";
    }

    // --- Cập nhật equals() và hashCode() theo Lab 04/05 ---

    @Override
    public boolean equals(Object obj) {
        // 1. Kiểm tra tham chiếu
        if (this == obj) return true;

        // 2. Kiểm tra null và instanceof để tránh ClassCastException/NullPointerException
        if (obj == null) return false;
        if (!(obj instanceof Media)) return false; // Dùng instanceof [cite: 1188]

        Media other = (Media) obj;

        // 3. So sánh theo title (case-insensitive) [cite: 366, 1186]
        // Đảm bảo title không null
        if (this.title == null || other.title == null) {
            return false;
        }
        return this.title.equalsIgnoreCase(other.title);
    }

    @Override
    public int hashCode() {
        // Đảm bảo tính nhất quán với equals()
        return (this.title == null) ? 0 : this.title.toLowerCase().hashCode();
    }
}