package hust.soict.globalict.aims.media;

import hust.soict.globalict.aims.playable.Playable;
import hust.soict.globalict.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

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
    public void play() throws PlayerException { 
        if (this.length > 0) {
            System.out.println("Playing Track: " + title);
            System.out.println("Track length: " + length);
        } else {
            System.err.println("ERROR: Track length is non-positive!");
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Track track)) {
            return false;
        }


        return this.length == track.length && this.title.equalsIgnoreCase(track.title);
    }
}
