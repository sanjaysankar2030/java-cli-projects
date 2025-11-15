import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
public class Main {

        public static void main(String[] args) {
            StringBuilder current=new StringBuilder();
            try{
                BufferedInputStream bin=new BufferedInputStream(new FileInputStream("message.txt"));
                byte[] bits=new byte[8];
                int n;
                while((n=bin.read(bits))!=-1){
                    String buffer=new String(bits,0,n,StandardCharsets.UTF_8);
                    String[] parts=buffer.split("\n",-1);
                    for(int i=0;i<parts.length-1;i++){
                        System.out.println("next Line:"+current+parts[i]);
                        current.setLength(0);
                    }
                    current.append(parts[parts.length-1]);
                }
                if (current.length() > 0) {
                    System.out.println("read: " + current);
                }
                bin.close();
            }catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }
        }
}
