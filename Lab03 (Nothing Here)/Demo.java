public class Demo {
    
    public static void main() {
        String filename = "test.exe"; // test.exe is the name or path to an executable file
        byte[] inputBytes = { 0 };
        long startTime, end Time;
        
        inputBytes = Files.readAllBytes(Path.get(filename));
        startTime = System.currentTimeMillis();
        String outputString = "";
        for(byte b: inputBytes) {
            outputString += (char)b;
        }
        endTime = System.currentTimeMillis();
        System.out.println(endTime - StartTime);
        
        StringBuilder outputStringBuilder = new StringBuilder();
        for(byte b : inputBytes) {
            outStringBuilder.append((char)b);
        }
    }
}