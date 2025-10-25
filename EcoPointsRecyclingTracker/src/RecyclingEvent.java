import java.io.Serializable;
import java.time.LocalDate;
public class RecyclingEvent implements Serializable{
    private String material;
    private double weight;
    private LocalDate date;
    private double ecoPoint;
public RecyclingEvent(String material,double weight,LocalDate date,double ecoPoint){
        this.material=material;
        this.weight=weight;
        this.date=LocalDate.now();
        this.ecoPoint=weight*10;
    }
    public String getMaterial(){
        return this.material;
    }
    public double getWeight(){
        return this.weight;
    }
    public double getecoPoint(){
        return this.ecoPoint;
    }
    public LocalDate getDate(){
        return this.date;
    }
    @Override
    public String toString(){
        return "Date: " + this.date +
                "\nMaterial Type: " + this.material +
                "\nWeight: " + this.weight +
                "\nEcopoints: " + this.ecoPoint;
    }
}
