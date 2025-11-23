package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.playable.Playable;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public int getLength() { return length; }

    @Override
    public void play() {
        System.out.println("Playing Track: " + title);
        System.out.println("Track length: " + length);
    }
}
