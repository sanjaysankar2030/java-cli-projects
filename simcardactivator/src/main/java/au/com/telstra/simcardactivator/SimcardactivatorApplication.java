package au.com.telstra.simcardactivator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimcardactivatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimcardactivatorApplication.class, args);
        //1. Create a POST endpoint /activate
        //
        //Define a Spring Boot controller.
        //
        //Accept JSON containing iccid and customerEmail.
        //
        //Map it to a request DTO.
        //
        //2. Extract the ICCID and build actuator payload
        //
        //Ignore customerEmail for now (not needed yet).
        //
        //Prepare JSON:
        //{ "iccid": "<value>" }
        //
        //3. Send POST request to actuator
        //
        //Use RestTemplate or WebClient.
        //
        //Call http://localhost:8444/actuate.
        //
        //Receive response JSON:
        //{ "success": true/false }
        //
        //4. Log and return the activation result
        //
        //Print "Activation success: true/false" to console.
        //
        //Return the same in your /activate controller response.
        //
        //5. Run actuator JAR + run your service + test + push to GitHub
        //
        //Start the actuator with Java 11.
        //
        //Run your Spring service.
        //
        //Test using Postman.
        //
        //Commit, push, and submit your GitHub link.
	}

}
