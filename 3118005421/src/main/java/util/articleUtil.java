package util;

import java.io.*;

public class articleUtil {


    public String[] divisionArticle(File article) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new FileReader(article));
        String temp;
        StringBuilder stringBuilder=new StringBuilder();
        while((temp=bufferedReader.readLine())!=null){
            stringBuilder.append(temp);
        }
        String[] sentences=stringBuilder.toString()
                .replace(" ", "")
                .replace("。","，")
                .replace("：“","")
                .replace("”","")
                .replace("？","，")
                .split("，");
        return sentences;
    }
}
