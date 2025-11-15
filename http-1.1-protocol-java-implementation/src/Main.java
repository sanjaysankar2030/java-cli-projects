import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
// import java.io.Thread;
public class Main {
    public static BlockingQueue<String> getLinesChannels(BufferedInputStream bin){
        BlockingQueue<String> queue= new LinkedBlockingQueue<>();
        new Thread(() ->{
            StringBuilder current=new StringBuilder();
            byte[] bits=new byte[8];
            int n;
            try{
                while((n=bin.read(bits))!=-1){
                    String buffer=new String(bits,0,n,StandardCharsets.UTF_8);
                    String[] parts=buffer.split("\n",-1);
                    for(int i=0;i<parts.length-1;i++){
                        queue.put(current.toString()+parts[i]);
                        current.setLength(0);
                    }
                    current.append(parts[parts.length-1]);
                }
                if (current.length() > 0) {
                    System.out.println("read: " + current);
                }
                bin.close();
            }catch(IOException| InterruptedException ioe){
                ioe.printStackTrace();
            }
            try {
                queue.put(""); 
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
        }).start();
    return queue; 
    }

public static void main(String[] args) {
    try{
        BufferedInputStream bin=new BufferedInputStream(new FileInputStream("message.txt"));
        BlockingQueue<String>queue=getLinesChannels(bin);
        String line;
        while (!(line = queue.take()).equals("")) { // "" is EOF indicator
            System.out.println("read: " + line);
        }
    }catch(FileNotFoundException | InterruptedException fnf){
        fnf.getStackTrace();
    }
}
}
