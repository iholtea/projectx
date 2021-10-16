package projectx.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {
	
	@Bean(name = "dateFormat")
	public SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat("dd/MM/yyyy");
	}
	
	@Bean(name = "timestampFormat")
	public SimpleDateFormat getTimestampFormat() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
}
