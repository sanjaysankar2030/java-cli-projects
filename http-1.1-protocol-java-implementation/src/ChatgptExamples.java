import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class ChatgptExamples {

    // Your original line-channel logic, unchanged
    public static BlockingQueue<String> getLinesChannel(InputStream in) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        new Thread(() -> {
            try (BufferedInputStream bin = new BufferedInputStream(in)) {
                byte[] bits = new byte[8];
                int n;
                StringBuilder current = new StringBuilder();
                
                while ((n = bin.read(bits)) != -1) {
                    String buffer = new String(bits, 0, n, StandardCharsets.UTF_8);
                    String[] parts = buffer.split("\n", -1);
                    for (int i = 0; i < parts.length - 1; i++) {
                        queue.put(current.toString() + parts[i]);
                        current.setLength(0);
                    }

                    current.append(parts[parts.length - 1]);
                }

                if (current.length() > 0) {
                    queue.put(current.toString());
                }

                queue.put(""); // EOF marker
            } catch (Exception ignore) {}
        }).start();

        return queue;
    }

    public static void main(String[] args) throws Exception {

        // Equivalent to net.Listen(":42069")
        ServerSocket listener = new ServerSocket(42069);
        System.out.println("Listening on :42069");

        // Close listener on ctrl+c
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try { listener.close(); } catch (Exception ignored) {}
        }));

        // Infinite accept loop
        while (true) {
            Socket conn = listener.accept();
            System.out.println("Connection accepted");

            new Thread(() -> handleConnection(conn)).start();
        }
    }

    private static void handleConnection(Socket conn) {
        try {
            BlockingQueue<String> q = getLinesChannel(conn.getInputStream());

            String line;
            while (!(line = q.take()).equals("")) {
                System.out.println(line);  // print EXACT line, nothing added
            }

            System.out.println("Connection closed");
        } catch (Exception ignore) {
        } finally {
            try { conn.close(); } catch (Exception ignored) {}
        }
    }
}

