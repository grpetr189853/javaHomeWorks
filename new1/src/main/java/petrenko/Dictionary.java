package petrenko;

import java.io.*;
import java.util.*;
//Словарик
/**
 * Created by grpetr189853 on 30.01.2017.
 */
public class Dictionary {

    public static HashMap<String,String> dict=new HashMap<>();
    public static void fillMap(){
        File fileDir = new File("dict1.txt");

        FileInputStream fin=null;

        try {
            //fin = new FileInputStream("dict1.txt");
            fin=new FileInputStream(fileDir);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(fin, "UTF-8"));
            int i;
            String line="";
            while ((i = br.read()) != -1) {
                char c = (char) i;
                line+=c;
                if(Character.toString(c).equals("\n")){
                    String engName=line.split(" ")[0];
                    String rusName=line.split(" ")[1];
                    dict.put(engName,rusName);
                    line="";
                }
            }
        }catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

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
    public static void getTranslation(Map mp,String engName){
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getKey().equals(engName) ) {
                System.out.println("Перевод слова :" + pair.getKey() + " = " +pair.getValue());//out.println("Found: " + pair.getKey() + " = " + pair.getValue());//pair.getValue()
            }

        }
    }
    public static void main(String[] args) {
        fillMap();
        getTranslation(dict,"white");
        getTranslation(dict,"black");
        getTranslation(dict,"red");
        getTranslation(dict,"Beige");
        getTranslation(dict,"Blue");
        getTranslation(dict,"Copper");
    }
}
