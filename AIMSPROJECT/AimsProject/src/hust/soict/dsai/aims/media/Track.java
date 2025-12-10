package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() { return title; } // Thêm getter cho title để dùng trong equals

    public int getLength() { return length; }

    @Override
    public void play() throws PlayerException {
        if (this.length <= 0) {
            System.err.println("Error: Track length is non-positive: " + this.title);
            throw new PlayerException("ERROR: Track length is non-positive: " + this.title);
        }

        System.out.println("Playing Track: " + title);
        System.out.println("Track length: " + length);
    }

    // Yêu cầu từ Lab 04/05: override equals() cho Track
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        // Kiểm tra null và ClassCastException
        if (obj == null) return false;
        if (!(obj instanceof Track)) return false;

        Track other = (Track) obj;

        // Hai Track được coi là bằng nhau nếu title VÀ length bằng nhau
        return this.length == other.length &&
                this.title != null &&
                this.title.equalsIgnoreCase(other.title);
    }
}