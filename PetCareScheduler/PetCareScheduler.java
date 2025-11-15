import java.util.Scanner;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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
        System.out.println("Enter Pet id: ");
        String petId=scanner.nextLine();
        Pet petObj=petList.get(petId);
        if(petObj==null){
            System.out.println("Invalid pet id");
            return ;
        }
        String appointmentType=scanner.nextLine();
        if(!appointmentType.equals("vet visit")){
            System.out.println("Invalid appointmentType");
            return;
        }
        String Notes=scanner.nextLine();
        String dateTime=scanner.nextLine();
        LocalDateTime appointmentDate=LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Appointment appointment=new Appointment(appointmentType, Notes,appointmentDate);
        petObj.addAppointment(appointment);
    }

    public static void storeData(){
        ObjectOutputStream out=null;
        try{
            out= new ObjectOutputStream(new FileOutputStream("petCare.ser")) ;
            out.writeObject(petList);
            out.close();
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
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
        }
    }
    
    public static void displayDetails(){
        System.out.println("Enter 1 to list all pets or 2 to list specific pets appointments ");
        String opts=scanner.nextLine();
        switch (opts) {
            case "1":
                for(Pet pets:petList.values()){
                    System.out.println(pets.getPetName());
                }
                break;
            case "2":
                System.out.println("Enter uniqueid:");
                String uniqueid=scanner.nextLine();
                for(String key:petList.keySet()){
                    if (key.equals(uniqueid)){
                        Pet value=petList.get(key);
                        System.out.println(value.getAppointmentList());
                    }
                }
                break;
            case "3":
                LocalDateTime dateTime=LocalDateTime.now();
                for(Pet pet:petList.values()){
                    for(Appointment app:pet.getAppointmentList()){
                        if(app.getDateTime().isAfter(dateTime)){
                            System.out.println(pet.getPetName()+pet.getPetAge()+pet.getBreed());
                        }
                    }
                }
                break;
            case "4":
                LocalDateTime prevDateTime=LocalDateTime.now();
                for(Pet pet:petList.values()){
                    for(Appointment app:pet.getAppointmentList()){
                        if(app.getDateTime().isBefore(prevDateTime)){
                            System.out.println(pet.getPetName()+pet.getPetAge()+pet.getBreed());
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
    
    public static void generateReport(){
        System.out.println("Enter the choice you want :");
        System.out.println("1.Pets with appointment for next week");
        System.out.println("2.Pets with overdue");
        String opts=scanner.nextLine();
        switch (opts) {
            case "1":
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime oneWeekLater = now.plusWeeks(1);
                    System.out.println("Pets with upcoming appointments in the next week:");
                    for (Pet pet : petList.values()) {
                        for (Appointment appt : pet.getAppointmentList()) {
                            LocalDateTime apptDate = appt.getDateTime();
                            if (apptDate.isAfter(now) && apptDate.isBefore(oneWeekLater)) {
                                System.out.println("Pet: " + pet.getPetName() + ", Appointment: " + appt);
                            }
                        }
                    }
                break;
            case "2":
                LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
                    System.out.println("Pets overdue for a vet visit (no visit in last 6 months):");
                    for (Pet pet : petList.values()) {
                        LocalDateTime lastVetVisit = null;
                            for (Appointment appt : pet.getAppointmentList()) {
                                if (appt.getAppointment().equalsIgnoreCase("vet visit")) {
                                    LocalDateTime apptDate = appt.getDateTime();
                                        if (lastVetVisit == null || apptDate.isAfter(lastVetVisit)) {
                                            lastVetVisit = apptDate;
                                        }
                                }
                            }
                            if (lastVetVisit == null || lastVetVisit.isBefore(sixMonthsAgo)) {
                                System.out.println("Pet: " + pet.getPetName() + " is overdue for a vet visit.");
                            }
                    }
            default:
                break;
        }
    }
    public static void main(String[] args) {
        loadOutputFromFile();
        String opts=scanner.nextLine();
        switch(opts){
            case "1":
                registerPet();
            case "2":
                sceduleAppointments();
            case "3":
                storeData();
            case "4":
                displayDetails();
            case "5":
                generateReport();
        }
    }
}
