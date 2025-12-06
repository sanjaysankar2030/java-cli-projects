import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LineReader {

    // Function that returns a BlockingQueue<String> like a Go channel
    public static BlockingQueue<String> getLinesQueue(InputStream input) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Start a new thread to simulate Go goroutine
        new Thread(() -> {
            StringBuilder currentLine = new StringBuilder();
            byte[] buffer = new byte[8];
            int n;

            try {
                while ((n = input.read(buffer)) != -1) {
                    String chunk = new String(buffer, 0, n, StandardCharsets.UTF_8);
                    String[] parts = chunk.split("\n", -1); // split on newline

                    for (int i = 0; i < parts.length - 1; i++) {
                        // Combine with currentLine and add to queue
                        queue.put(currentLine.toString() + parts[i]);
                        currentLine.setLength(0); // reset
                    }

                    // Last part might be incomplete, keep it for next read
                    currentLine.append(parts[parts.length - 1]);
                }

                // If anything left after EOF
                if (currentLine.length() > 0) {
                    queue.put(currentLine.toString());
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Poison pill to indicate EOF
                try {
                    queue.put(""); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return queue;
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        BlockingQueue<String> queue = getLinesQueue(new FileInputStream("message.txt"));

        String line;
        while (!(line = queue.take()).equals("")) { // "" is EOF indicator
            System.out.println("read: " + line);
        }
    }
}
