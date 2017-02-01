package petrenko;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;
//выводит в консоль отсортированные по алфавиту песни
/**
 * Created by grpetr189853 on 30.01.2017.
 */
public class Songs {
    public static TreeSet<String> songs=new TreeSet<>();
    public static void fillSongs(TreeSet<String> songs) {
        FileInputStream fin=null;
        try {
            fin = new FileInputStream("songs.txt");
            int i;
            String line="";
            while ((i = fin.read()) != -1) {
                char c = (char) i;
                line+=c;
                if(Character.toString(c).equals("\n")){
                    songs.add(line);
                    line="";
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {

            try {

                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }
    public static void printSongs(TreeSet<String> songs){
        Iterator<String> it=songs.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    public static void main(String[] args) {
        fillSongs(songs);
        printSongs(songs);
    }
}
