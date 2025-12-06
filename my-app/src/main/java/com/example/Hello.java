import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Hello{
    @Bean
    public HelloWorld helloWorld(){
        return new HelloWorld;
    }

}
