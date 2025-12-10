package hust.soict.globalict.aims.media;

import hust.soict.globalict.aims.playable.Playable;
import hust.soict.globalict.aims.exception.PlayerException;
import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(String title, String category, float cost,
                       String director, int length, String artist) throws IllegalArgumentException {
        super(title, category, cost, director, length);
        this.artist = artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) tracks.add(track);
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    @Override
    public int getLength() {
        int total = 0;
        if (tracks != null) {
            for (Track t : tracks) {
                total += t.getLength();
            }
        }
        return total;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            System.err.println("ERROR: CD total length is non-positive!");
            throw new PlayerException("ERROR: CD length is non-positive!");
        }

        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("Artist: " + artist);

        for (Track track : tracks) {
            try {
                track.play();
            } catch (PlayerException e) {
                System.err.println("CD Play Error: Track '" + track.getTitle() + "' failed to play.");
                throw e;
            }
        }
    }
}
