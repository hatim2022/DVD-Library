package com.dvdlibrary;

import com.dvdlibrary.controller.DvdLibraryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        DvdLibraryController controller =
                ctx.getBean("controller", DvdLibraryController.class);
        controller.run();
    }
}
