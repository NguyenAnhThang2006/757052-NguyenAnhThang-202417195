package hust.soict.globalict.garbage;

import java.nio.file.*;
import java.io.IOException;

public class NoGarbage {
    public static void main(String[] args) throws IOException {
        String filename = "test.exe"; // hoặc file lớn khác
        byte[] inputBytes = Files.readAllBytes(Paths.get(filename));

        long startTime = System.currentTimeMillis();
        StringBuffer outputBuffer = new StringBuffer();
        for (byte b : inputBytes) {
            outputBuffer.append((char) b);
        }
        String outputString = outputBuffer.toString();
        long endTime = System.currentTimeMillis();

        System.out.println("Time using StringBuffer: " + (endTime - startTime) + " ms");
    }
}
