package ee.meriloo.main;

import ee.meriloo.services.Interfaces.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


/**
 * Created by Lauri on 13.10.2015.
 */
@Component
public class Main {

    @Autowired
    DemoService demoService;



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Main main = context.getBean(Main.class);
        main.demoService.runDemos();
    }






}