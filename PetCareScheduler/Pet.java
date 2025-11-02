package PetCareScheduler;

import java.util.List;
import java.time.LocalDate;

public class Pet {
    private String uniquePetId;
    private String petName;
    private String breed;
    private String petAge;
    private String ownerName;
    private String contactInfo;
    private LocalDate regDate;
    private List<Appointment> appointmentList;

    // Constructor
    public Pet(String uniquePetId, String petName, String breed, String petAge, String ownerName, String contactInfo) {
        this.uniquePetId = uniquePetId;
        this.petName = petName;
        this.breed = breed;
        this.petAge = petAge;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.regDate = LocalDate.now();
    }

    // Getters and Setters
    public String getUniquePetId() {
        return uniquePetId;
    }

    public void setUniquePetId(String uniquePetId) {
        this.uniquePetId = uniquePetId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public void addAppointment(Appointment appointment){
        this.appointmentList.add(appointment);
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
