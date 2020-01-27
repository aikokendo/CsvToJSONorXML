package aleUsers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableCaching
@EnableResourceServer
public class app {
    public static void main(String[] args){
        SpringApplication.run(app.class, args);
    }

}

