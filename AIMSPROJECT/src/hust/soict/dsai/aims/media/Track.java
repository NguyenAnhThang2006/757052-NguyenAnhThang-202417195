package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.exception.PlayerException; // Cần import PlayerException

public class Track implements Playable {
    private String title;
    private int length;

    // Constructor cần kiểm tra ràng buộc length (không âm)
    public Track(String title, int length) throws IllegalArgumentException {
        if (length < 0) {
            throw new IllegalArgumentException("ERROR: Track length cannot be negative.");
        }
        this.title = title;
        this.length = length;
    }

    public String getTitle() { return title; }
    public int getLength() { return length; }

    @Override
    public void play() throws PlayerException { // Cập nhật chữ ký phương thức
        if (this.length > 0) {
            System.out.println("Playing Track: " + title);
            System.out.println("Track length: " + length);
        } else {
            // Ném PlayerException nếu length không dương
            System.err.println("ERROR: Track length is non-positive!");
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
    }

    // Triển khai phương thức equals() (Yêu cầu)
    @Override
    public boolean equals(Object obj) {
        // 1. Kiểm tra tham chiếu và loại
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Track track)) {
            return false;
        }

        // 2. So sánh theo tiêu chí (Title và Length)
        return this.length == track.length && this.title.equalsIgnoreCase(track.title);
    }
}