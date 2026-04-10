package edu.eci.dosw.DOSW_Library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@EnableJpaRepositories(basePackages = "edu.eci.dosw.DOSW_Library.Persistence.Repositorios")
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class})

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@EnableMongoRepositories(basePackages = "edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository")
public class DoswLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoswLibraryApplication.class, args);
	}

}
