import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
public class Simple{
    public static void main(String[] args) {
        String line;
        try{
            File file=new File("example.txt");
            FileReader reader=new FileReader(file);
            BufferedReader buffer=new BufferedReader(reader);
            while((line=buffer.readLine())!=null){
                System.out.println(line);
        }
        buffer.close();
        }catch(IOException e){
            System.out.println(e);

        }

}}
