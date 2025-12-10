package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.exception.PlayerException; // Import PlayerException

public class DigitalVideoDisc extends Media implements Playable { // Implement Playable

    private String director;
    private int length;

    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title) {
        super(title, null, 0f);
        // id được tự động tăng trong Media constructor
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super(title, category, cost);
        this.director = director;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }

    // Cập nhật static block/constructor logic:
    // Theo Lab 03, id và nbDigitalVideoDiscs nên được quản lý trong Media/Disc/DigitalVideoDisc
    // Hiện tại, ID được quản lý trong Media, nên tôi sẽ xóa việc tăng nbDigitalVideoDiscs trong các constructor
    // Thay vào đó, nó nên được quản lý trong constructor cuối cùng hoặc một phương thức riêng biệt nếu cần thiết
    // Tôi giữ nguyên cấu trúc bạn đã gửi, nhưng loại bỏ các biến tĩnh trong class DigitalVideoDisc (chuyển sang Media/Store nếu cần)
    // Nếu bạn muốn giữ nbDigitalVideoDiscs, bạn phải đặt nó trong constructor của Media hoặc gọi nó trong mỗi constructor.
    // Vì nbDigitalVideoDiscs = 0; nên tôi sẽ bỏ qua nó trong bản sửa cuối này (vì nó nên là một classifier member/static counter).

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        // Cần kiểm tra NullPointerException và ClassCastException
        if (obj == null) return false;
        if (!(obj instanceof DigitalVideoDisc)) return false;

        DigitalVideoDisc other = (DigitalVideoDisc) obj;
        // Theo yêu cầu Lab 04/05, hai media bằng nhau nếu title bằng nhau
        return getTitle() != null && getTitle().equalsIgnoreCase(other.getTitle());
    }

    @Override
    public int hashCode() {
        return getTitle() == null ? 0 : getTitle().toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "DVD - "
                + getTitle() + " - "
                + getCategory() + " - "
                + director + " - "
                + length + ": "
                + getCost() + " $";
    }

    public boolean isMatch(String title) {
        if (getTitle() == null || title == null) return false;
        return getTitle().equalsIgnoreCase(title);
    }

    @Override
    public void play() throws PlayerException { // Thêm throws PlayerException
        if (this.length <= 0) { // Kiểm tra độ dài
            System.err.println("Error: DVD length is non-positive: " + this.getTitle());
            throw new PlayerException("ERROR: DVD length is non-positive: " + this.getTitle()); // Ném Exception
        }

        System.out.println("Playing DVD: " + getTitle());
        System.out.println("DVD length: " + length);
    }
}