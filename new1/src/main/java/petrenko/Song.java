package petrenko;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
//сортирует и выводит песни из файла по названию и исполнителю
/**
 * Created by grpetr189853 on 01.02.2017.
 */
public class Song implements Comparable<Song> {
    private String songName="";
    private String songAuthor="";
    private int songRating=0;
    public Song(String songName,String songAuthor){
        this.songName=songName;
        this.songAuthor=songAuthor;
    }
    public Song(String songName,String songAuthor,int songRating){
        this.songName=songName;
        this.songAuthor=songAuthor;
        this.songRating=songRating;
    }
    public void setSongName(String name){
        this.songName=name;
    }
    public String getSongName(){
        return songName;
    }
    public void setSongAuthor(String author){
        this.songAuthor=author;
    }
    public String getSongAuthor(){
        return songAuthor;
    }
    public void setSongRating(int songRating){
        this.songRating=songRating;
    }
    public int getSongRating(){
        return songRating;
    }
    public static void  fillTreeSet(TreeSet<Song> songs){
        FileInputStream fin=null;
        try {
            fin = new FileInputStream("song.txt");
            int i;
            String line="";
            while ((i = fin.read()) != -1) {
                char c = (char) i;
                line+=c;
                if(Character.toString(c).equals("\n")){
                    String songName=line.split("/")[0];
                    String songAuthor=line.split("/")[1];
//                    int songRating=Integer.parseInt(line.split("/")[2]);
                    Song song=new Song(songName,songAuthor);
                    songs.add(song);
//                    songs.add(line);
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
    public int compareTo(Song other){
        return this.getSongAuthor().compareTo(other.getSongAuthor());
    }
    public static void main(String[] args) {
        TreeSet<Song> songs=new TreeSet<Song>();
        fillTreeSet(songs);
        System.out.println("***********sorted by Song Name**************");
        SortedSet<Song> sortedByName=new TreeSet<Song>(new Comparator<Song>() {
            public int compare(Song song, Song t1) {
                return song.getSongName().compareTo(t1.getSongName());
            }
        });
        sortedByName.addAll(songs);
        Iterator<Song> it=sortedByName.iterator();
        while(it.hasNext()){
            Song el=it.next();
            System.out.println(el.getSongName()+"/"+el.getSongAuthor());
        }

        System.out.println("***********sorted by Song Author****************");
        SortedSet<Song> sortedByAuthor=new TreeSet<Song>(new Comparator<Song>() {
            public int compare(Song song, Song t1) {
                return song.getSongAuthor().compareTo(t1.getSongAuthor());
            }
        });
        sortedByAuthor.addAll(songs);
        Iterator<Song> it1=sortedByAuthor.iterator();
        while(it1.hasNext()){
            Song el=it1.next();
            System.out.println(el.getSongName()+"/"+el.getSongAuthor());
        }

    }

}
