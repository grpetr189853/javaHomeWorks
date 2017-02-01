package petrenko;

import java.io.*;
//программа которая читает текст из консоли и записывает в файл
/**
 * Created by grpetr189853 on 01.02.2017.
 */
public class FileWriter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя файла:");
        String fileName=reader.readLine();
        BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileName));
        System.out.println("Введите текст, по окончании нажмите exit:");
        while (true){
            String nextLine=reader.readLine();
            if(nextLine.equals("exit")) break;
            bw.write(nextLine);
            bw.write("\n");
        }
        reader.close();
        bw.close();
    }
}
