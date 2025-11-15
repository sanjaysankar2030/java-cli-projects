
import java.time.LocalDateTime;

class Appointment {
    private String AppointmentType;
    private LocalDateTime dateTime;
    private String Notes;
    public Appointment(String AppointmentType,String Notes,LocalDateTime appointmentDate){
        this.AppointmentType=AppointmentType;
        this.dateTime=appointmentDate;
        this.Notes=Notes;
    }
    public String getAppointment(){
        return this.AppointmentType;
    } 
    public LocalDateTime getDateTime(){
        return this.dateTime;
    }
    public String getNotes(){
        return this.Notes;
    }
    public void setAppointmentType(String AppointmentType){
        this.AppointmentType=AppointmentType;
    }
    public void setDateTime(LocalDateTime dateTime){
        this.dateTime=dateTime;
    }
    public void setNotes(String Notes){
        this.Notes=Notes;
    }
    @Override
    public String toString(){
        return "Date: " + this.dateTime +
                "\nAppointmentType: " + this.AppointmentType +
                "\nNotes: " + this.Notes;
    }
}
