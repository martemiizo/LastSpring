package ru.vtb.Last;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.vtb.Last.entity.PremiereEntity;
import ru.vtb.Last.services.PremiereRepoService;

@EnableTransactionManagement
@SpringBootApplication
public class ConcertSpringApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ConcertSpringApplication.class, args);


        PremiereRepoService premiereService = ctx.getBean(PremiereRepoService.class);
        premiereService.deleteAll();
        premiereService.addPremiere(new PremiereEntity("20 лет", "БИ 2", 20, 1000));
        premiereService.addPremiere(new PremiereEntity("Бордерлайн", "Земфира", 18, 500));
        premiereService.addPremiere(new PremiereEntity("Стендап 2022", "Евгений Чебатков", 18, 200));


        /* ConfigurableApplicationContext ctx = SpringApplication.run(ConcertSpringApplication.class, args);


        PremiereRepoService premiereService = ctx.getBean(PremiereRepoService.class);
        premiereService.deleteAll();


        //Добавили 2 концерта
        premiereService.addPremiere(new PremiereEntity("20 лет", "БИ 2", 20, 1000));
        premiereService.addPremiere(new PremiereEntity("Бордерлайн", "Земфира", 18, 500));

        premiereService.printAll();
        System.out.println("======================================================");

        //Добавлен новый концерт
        premiereService.addPremiere(new PremiereEntity("Стендап 2022", "Евгений Чебатков", 18, 200));

        premiereService.printAll();
        System.out.println("======================================================");

        //Удален концерт
        premiereService.deletePremiere("20 лет");
        premiereService.printAll();
        System.out.println("======================================================");

        //Куплено 500 билетов на Земфиру
        if (!premiereService.buyTickets("Бордерлайн", 500)) {
            System.out.println("Все билеты на концерт \"Бордерлайн\" выкуплены");
        }

        premiereService.printAll();
        System.out.println("======================================================");*/
    }

}
