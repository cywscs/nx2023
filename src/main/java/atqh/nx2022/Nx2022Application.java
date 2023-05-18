package atqh.nx2022;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("atqh.nx2022.mapper")
public class Nx2022Application {

    public static void main(String[] args) {
        SpringApplication.run(Nx2022Application.class, args);
    }

}
