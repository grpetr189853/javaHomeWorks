package petrenko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Application {
    @Autowired
    @Qualifier("writer4")
    private WriterToFile writer;
    @Autowired
    @Qualifier("messenger4")
    private Messenger messenger;
    @Autowired
    public Application(@Qualifier("writer4") WriterToFile writer,@Qualifier("messenger4") Messenger messenger) {
        this.writer = writer;
        this.messenger = messenger;
    }

    public Application() {
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring4.xml");

        Application app = (Application) ctx.getBean("app");
        for(int i=0;i < 5;i++){
            String message = app.messenger.getMessage();
            app.writer.write(message);
        }
        ctx.close();
    }

}