package petrenko;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by java-1-08 on 13.03.2017.
 */
@Component
public class CacheWriter extends  WriterToFileImpl {
    private int cacheSize;
    private List<String> cache;

    public CacheWriter() {
    }

    public CacheWriter(@Value("3")int cacheSize , @Qualifier("java.util.Date") Date date, @Qualifier("dateFormat") DateFormat df, @Value("storage4.txt") String fileName) {
        super(date, df, fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }
    @PreDestroy
    public void destroy() {
        System.out.println("DESTROY method");
        if ( ! cache.isEmpty()) {
            writeMsgCache();
        }
    }

    public void write(String str) {
        cache.add(str);
        if (cache.size() == cacheSize) {
            writeMsgCache();
            cache.clear();
        }
    }

    private void writeMsgCache() {
        for (int i = 0; i < cache.size(); i++) {
            super.write(cache.get(i));
        }
    }

}
