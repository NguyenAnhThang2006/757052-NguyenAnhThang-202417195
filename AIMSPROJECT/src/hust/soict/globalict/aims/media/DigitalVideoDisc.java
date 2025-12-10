package hust.soict.globalict.aims.media;

import hust.soict.globalict.aims.playable.Playable;
import hust.soict.globalict.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

    private static int nbMedia = 0;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost, director, length);
        this.setId(++nbMedia);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: DVD cannot be played. Length is non-positive.");
        }
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
}
