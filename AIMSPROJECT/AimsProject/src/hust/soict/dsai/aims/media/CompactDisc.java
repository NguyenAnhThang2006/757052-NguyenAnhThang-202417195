package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.playable.Playable;
import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(String title, String category, float cost,
                       String director, int length, String artist) {
        super(title, category, cost, director, length);
        this.artist = artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) tracks.add(track);
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    public int getLength() {
        int total = 0;
        for (Track t : tracks) total += t.getLength();
        return total;
    }

    public void play() {
        System.out.println("Artist: " + artist);
        for (Track t : tracks) {
            t.play();
        }
    }
}
