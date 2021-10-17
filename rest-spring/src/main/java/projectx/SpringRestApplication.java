package projectx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringRestApplication {
	
	public static void main(String[] args) {
		
		ApplicationContext appContext = 
				SpringApplication.run(SpringRestApplication.class, args);
		
		System.out.println("--------------------------------");
		System.out.println( "context type: " + appContext.getClass().getName());
		System.out.println("--------------------------------");
		
	}
	
}
