import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class Household implements Serializable{
    private String uniqueId;
    private String address;
    private String name;
    public List<RecyclingEvent>event;
    private LocalDate joiningDate;
    public double totalPoints;
    public double totalWeight;
    public Household(String uniqueId,String address,String name){
        this.name=name;
        this.uniqueId=uniqueId;
        this.address=address;
        this.joiningDate=LocalDate.now();
        this.event=new ArrayList<>();
        this.totalPoints=0.0;
    }
    public String getId() { return uniqueId; }
    public String getName() { return name; }
    public String getAddress() { return address;}
    public LocalDate getJoinDate(){
        return joiningDate;
    }
    public double getTotalPoints(){
        return totalPoints;
    }
    public List<RecyclingEvent> getEvent(){
        return event;
    }
    public void addEvent(RecyclingEvent recycleEvent){
        this.event.add(recycleEvent);
        this.totalPoints=recycleEvent.getecoPoint();
    } 
    public double getTotalWeight(){
        for(RecyclingEvent singleEvent:event){
            totalWeight+=singleEvent.getWeight();
        }
        return totalWeight;
    }

}
