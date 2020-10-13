package by.bknst.console;

import by.bknst.AppConfig;
import by.bknst.MessageGenerator;
import by.bknst.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        log.info("Start");

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        NumberGenerator numberGenerator = context.getBean("numberGenerator", NumberGenerator.class);

        int num = numberGenerator.next();
        log.info("number = {}", num);

        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);
        log.info("getMainMessage() = {}", messageGenerator.getMainMessage());
        log.info("getResultMessage() = {}", messageGenerator.getResultMessage());

        context.close();
    }
}
