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

/**
 * Created by java-1-08 on 12.03.2017.
 */
@Component
public class WriterToFileImpl implements WriterToFile {
    private File file;
    private String fileName = "storage4.txt";

    //without arguments
    //public or private
    //may throw exception
    @PostConstruct
    public void init() throws IOException {
        System.out.println("In init method");
        this.file = new File(fileName);
        if(!file.canWrite()){
            System.err.println("Cannot write to file");
        }
    }
    @Autowired

    private Date date;
    @Autowired
    @Qualifier("dateFormat")
    private DateFormat df;

    public WriterToFileImpl() {
    }

    public WriterToFileImpl(@Qualifier("java.util.Date") Date date, @Qualifier("dateFormat") DateFormat df, @Value("storage4.txt") String fileName ) {
        this.date = date;
        this.df = df;
        this.fileName = fileName;
    }

    public void write(String str) {
        try {
            System.out.println("write to file");
            FileUtils.write(new File(fileName), df.format(date) + ": " + str + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
