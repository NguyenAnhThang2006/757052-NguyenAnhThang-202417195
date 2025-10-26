package hust.soict.globalict.garbage;

import java.nio.file.*;
import java.io.IOException;

public class GarbageCreator {
    public static void main(String[] args) throws IOException {
        String filename = "test.exe"; // hoặc file bất kỳ, ví dụ "largefile.txt"
        byte[] inputBytes = Files.readAllBytes(Paths.get(filename));

        long startTime = System.currentTimeMillis();
        String outputString = "";
        for (byte b : inputBytes) {
            outputString += (char) b;
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Time using '+' operator: " + (endTime - startTime) + " ms");
    }
}
