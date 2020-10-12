package by.bknst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Start");

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
        NumberGenerator numberGenerator = context.getBean("numberGenerator", NumberGenerator.class);

        int num = numberGenerator.next();
        log.info("Next number = {}", num);

        context.close();
    }
}
