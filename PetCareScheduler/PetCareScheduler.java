package PetCareScheduler;
import java.util.Scanner;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

class PetCareScheduler {
    private static  Scanner scanner = new Scanner(System.in);
    private static Map<String,Pet> petList=new HashMap<>();
    public static void  registerPet(){
        String petName=scanner.nextLine();
        String petAge=scanner.nextLine();
        String uniquePetId=scanner.nextLine();
        String breed=scanner.nextLine();
        String ownerName=scanner.nextLine();
        String contactInfo=scanner.nextLine();
        Pet singlePet  =new Pet(uniquePetId,petName,breed,petAge,ownerName,contactInfo);
        petList.put(uniquePetId, singlePet);
        System.out.println("Pet registered successfully on " + singlePet.getRegDate());
    }
    public static void sceduleAppointments(){
        String appointmentType;
        String Notes;
        appointmentType=scanner.nextLine();
        Notes=scanner.nextLine();
        TODO:add the appointment to the pet class which is in the HashMap;

    }
    @SuppressWarnings("unchecked") // Suppresses unchecked cast warning when reading the object
    public static void loadOutputFromFile(){
        ObjectInputStream in=null;
        try{
         in=new ObjectInputStream(new FileInputStream("petCare.ser"));
        petList=(Map<String,Pet>)in.readObject();
        System.out.println("Data loaded ..");
        in.close();
        }catch(FileNotFoundException fne){
            System.out.println("File Not Found.....");
        }
        catch(IOException|ClassNotFoundException cnf){
            System.out.println("Error while loading data ");
        }}
    

    public static void main(String[] args) {
        loadOutputFromFile();
        String opts=scanner.nextLine();
        switch(opts){
            case "1":
                registerPet();
            case "2":
                sceduleAppointments();
        }
    }
}
