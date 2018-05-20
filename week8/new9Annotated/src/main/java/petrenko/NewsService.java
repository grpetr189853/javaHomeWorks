package petrenko;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

/**
 * Created by grpetr189853 on 14.03.2017.
 */
public class NewsService implements Runnable{
    public String news;
    @Autowired
    @Qualifier("consoleWriter")
    public ConsoleWriter consoleWriter;
    @Autowired
    @Qualifier("fileWriter")
    public FileWriter fileWriter;
    public final String hostName="24tv.ua";
    public final String protocol="http://";
    @Autowired
    @Qualifier("app")
    MainApp mainApp;
    public NewsService(String news) {
        this.news = news;
    }

    public String getNews() {
        return news;
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ConsoleWriter getConsoleWriter() {
        return consoleWriter;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void setConsoleWriter(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
    }
    @PostConstruct
    public void init() throws IOException {

        InetAddress in = InetAddress.getByName(hostName);
        if(!in.isReachable(1000)){
            System.err.println("Host is unreachable");
        }
    }
    public List<String> getRandomElements(final int amount, final List<String> list) {
        ArrayList<String> returnList = new ArrayList<String>(list);

        Collections.shuffle(returnList);
        if (returnList.size() > amount)
            returnList.subList(amount, returnList.size()).clear();

            return returnList;
    }
    public NewsService(){


    }

    @Override
    public void run() {
        List<String> newsAll= null;
        try {
            try {
                Document doc = Jsoup.connect(protocol+hostName+"/ru/").get();
                Elements title = doc.select(".news-title");
                newsAll = new ArrayList<>();
                for (Element tit : title) {
                    newsAll.add(tit.select(".news-title").text());
                }
                List<String> new5=getRandomElements(5,newsAll);
                mainApp.barrier.await();
                consoleWriter.write(new5);
                mainApp.barrier.await();
                fileWriter.write(new5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
