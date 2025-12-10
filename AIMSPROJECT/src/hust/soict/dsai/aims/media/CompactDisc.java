package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.exception.PlayerException; // Cần import PlayerException
import java.util.ArrayList;

// CompactDisc kế thừa Disc (giả định Disc kế thừa Media)
public class CompactDisc extends Disc implements Playable {

    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    // Constructor (Giả định Disc/Media constructor xử lý IllegalArgumentException cho cost)
    public CompactDisc(String title, String category, float cost,
                       String director, int length, String artist) throws IllegalArgumentException {
        // Giả định Disc constructor gọi Media constructor và xử lý length ban đầu
        super(title, category, cost, director, length);
        this.artist = artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) tracks.add(track);
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    @Override // Ghi đè lại getLength để tính tổng độ dài Tracks
    public int getLength() {
        int total = 0;
        // Kiểm tra null để tránh lỗi nếu tracks chưa được khởi tạo đúng cách
        if (tracks != null) {
            for (Track t : tracks) {
                total += t.getLength();
            }
        }
        return total;
    }

    // Cập nhật phương thức play() để xử lý PlayerException
    @Override
    public void play() throws PlayerException {
        // 1. Kiểm tra tổng độ dài CD
        if (this.getLength() <= 0) {
            // Ném PlayerException nếu tổng độ dài không dương
            System.err.println("ERROR: CD total length is non-positive!");
            throw new PlayerException("ERROR: CD length is non-positive!");
        }

        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("Artist: " + artist);

        // 2. Lặp qua các Track và xử lý PlayerException
        for (Track track : tracks) {
            try {
                track.play(); // Track có thể ném PlayerException
            } catch (PlayerException e) {
                // Nếu bất kỳ track nào không thể chơi (length <= 0), 
                // in lỗi và ném lại (re-throw) ngoại lệ, dừng phát CD
                System.err.println("CD Play Error: Track '" + track.getTitle() + "' failed to play.");
                throw e;
            }
        }
    }
}