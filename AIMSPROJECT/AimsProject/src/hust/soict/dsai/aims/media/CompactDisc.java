package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.exception.PlayerException;
import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    // Lưu ý: Constructor này không sử dụng tham số length, vì length của CD được tính tổng từ các tracks.
    // Nếu bạn muốn giữ constructor này, bạn nên đặt length = 0 khi gọi super()
    public CompactDisc(String title, String category, float cost,
                       String director, String artist, ArrayList<Track> tracks) {
        super(title, category, cost, director, 0); // Đặt length = 0 hoặc gọi constructor Disc phù hợp
        this.artist = artist;
        if (tracks != null) {
            this.tracks = tracks;
        }
    }

    // Thêm constructor để dễ dàng khởi tạo hơn
    public CompactDisc(String title, String category, float cost,
                       String director, String artist) {
        super(title, category, cost, director, 0);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track added: " + track.getTitle());
        } else {
            System.out.println("Track already in CD: " + track.getTitle());
        }
    }

    public void removeTrack(Track track) {
        if (tracks.remove(track)) {
            System.out.println("Track removed: " + track.getTitle());
        } else {
            System.out.println("Track not found in CD: " + track.getTitle());
        }
    }

    // Override getLength của Disc (hoặc Media)
    @Override
    public int getLength() {
        int total = 0;
        for (Track t : tracks) total += t.getLength();
        return total;
    }

    @Override
    public void play() throws PlayerException { // Cập nhật throws PlayerException
        // 1. Kiểm tra độ dài tổng thể của CD
        if (this.getLength() <= 0) {
            System.err.println("Error: CD length is non-positive: " + this.getTitle());
            throw new PlayerException("ERROR: CD length is non-positive: " + this.getTitle());
        }

        System.out.println("Playing CD: " + getTitle());
        System.out.println("Artist: " + artist);

        // 2. Chơi từng track và bắt Exception
        for (Track t : tracks) {
            try {
                t.play();
            } catch (PlayerException e) {
                // Nếu track nào không play được (do length <= 0),
                // ta ném lại (throw) PlayerException để báo lỗi cho toàn bộ CD
                throw e;
            }
        }
    }
}