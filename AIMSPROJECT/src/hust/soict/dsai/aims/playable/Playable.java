package hust.soict.dsai.aims.playable;

import hust.soict.dsai.aims.exception.PlayerException;

public interface Playable {

    // Thêm throws PlayerException vào chữ ký phương thức
    void play() throws PlayerException;

}