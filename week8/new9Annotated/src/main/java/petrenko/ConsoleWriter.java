package petrenko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by grpetr189853 on 14.03.2017.
 */
@Component
public class ConsoleWriter implements Writer{
    @Autowired
    @Qualifier("app")
    MainApp mainApp;
    @Autowired
    private Date date;
    @Autowired
    @Qualifier("dateFormat")
    private DateFormat df;
    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ConsoleWriter() {
    }

    public ConsoleWriter(@Qualifier("app")MainApp mainApp, @Qualifier("java.util.Date")Date date, @Qualifier("dateFormat")DateFormat dateFormat) {
        this.mainApp=mainApp;
        this.date=date;
        this.df=dateFormat;
    }
    public void write(List<String> list){
        Iterator<String> iterator=list.iterator();
        while(iterator.hasNext())
            System.out.println(df.format(date) + ": " +iterator.next());
    }

}
