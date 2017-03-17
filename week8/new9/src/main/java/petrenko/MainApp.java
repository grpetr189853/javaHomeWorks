package petrenko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by grpetr189853 on 14.03.2017.
 */
public class MainApp {
    public CyclicBarrier barrier;
    public static String message;

    public MainApp() {

        Runnable barrier1Action = new Runnable() {
            public void run() {
                System.out.println("\u001b[2J");
            }
        };
        barrier = new CyclicBarrier(4, barrier1Action);

    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
        MainApp app = (MainApp) ctx.getBean("app");

        for(int i=0;i<1000;i++) {
            try {
                //ConsoleWriter consoleWriter=(ConsoleWriter) ctx.getBean("consoleWriter");
                NewsService newsService=(NewsService) ctx.getBean("newsService");

                Thread thx=new Thread(newsService);
                thx.start();
                //consoleWriter.printNews();

                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
