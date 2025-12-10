package hust.soict.globalict.aims.media;

import hust.soict.globalict.aims.playable.Playable;
import hust.soict.globalict.aims.exception.PlayerException; 

public class DigitalVideoDisc extends Media implements Playable {

    private String director;
    private int length;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost)
            throws IllegalArgumentException {
        super(title, category, cost);

        if (length < 0) {
            throw new IllegalArgumentException("ERROR: DVD length cannot be negative.");
        }

        this.director = director;
        this.length = length;
    }

    public String getDirector() { return director; }
    public int getLength() { return length; }

    @Override
    public void play() throws PlayerException { 
        if (this.length > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            System.err.println("ERROR: DVD length is non-positive!");
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }
}
