package Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class Application {
    @Bean
    public DataSource dataSource() {
      DriverManagerDataSource ds = new DriverManagerDataSource();
      ds.setDriverClassName(oracle.jdbc.driver.OracleDriver.class.getName());
      ds.setUrl("jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle");
      ds.setUsername("aswindle");
      ds.setPassword("a5524");
      return ds;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
