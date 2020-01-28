package aleUsers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class app {
    public static void main(String[] args){
        SpringApplication.run(app.class, args);
    }

}

