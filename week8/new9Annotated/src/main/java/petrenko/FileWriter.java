package petrenko;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by grpetr189853 on 16.03.2017.
 */
@Component
public class FileWriter implements Writer {
    private File file;
    private String fileName = "news.txt";
    @Autowired
    private Date date;
    @Autowired
    @Qualifier("dateFormat")
    private DateFormat df;
    @PostConstruct
    public void init() throws IOException {

        this.file = new File(fileName);
        if(!file.canWrite()){
            System.err.println("Cannot write to file");
        }
    }

    public FileWriter() {
    }

    public FileWriter(@Qualifier("java.util.Date") Date date , @Qualifier("dateFormat")  DateFormat df, @Value("news.txt") String fileName) {
        this.fileName = fileName;
        this.date = date;
        this.df = df;
    }

    public void write(List<String> list){
        try {
            System.out.println("write to file");
            Iterator<String> iterator=list.iterator();
            while(iterator.hasNext())
            FileUtils.write(new File(fileName), df.format(date) + ": " + iterator.next() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
