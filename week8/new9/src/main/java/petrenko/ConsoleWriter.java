package petrenko;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by grpetr189853 on 14.03.2017.
 */
public class ConsoleWriter implements Writer{

    MainApp mainApp;
    private Date date;
    private DateFormat df;
    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }



    public ConsoleWriter(MainApp mainApp,Date date,DateFormat dateFormat) {
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
