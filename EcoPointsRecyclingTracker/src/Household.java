import java.time.LocalDate;
import java.io.Serializable;
public class Household implements Serializable{

    private String uniqueId;
    private String address;
    private String name;
    private LocalDate joiningDate;
    public Household(String uniqueId,String address,String name, LocalDate joiningDate){
        this.name=name;
        this.uniqueId=uniqueId;
        this.address=address;
        LocalDate joiningDate=LocalDate.now();

    }
}
