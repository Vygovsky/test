package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TestApplication /*implements CommandLineRunner*/ {

  /*  private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepositoryImpl repository;*/


    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception {

        // logger.info("repos->{}", repository.findAll());

        //logger.info("repos->{}", repository.findById(3L));

        //Date date=new java.sql.Date()
     *//*  logger.info("Insert->{}", repository.
                save(new User(
                        "Petr",
                        "Petr@ukr.net",
                        new java.sql.Date(2000, 3, 12))));*//*

        //repository.delete(3L);

    }*/
}
