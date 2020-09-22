package util;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class articleUtil {


    public List<String> divisionArticle(File article) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new FileReader(article));
        String temp;
        StringBuilder stringBuilder=new StringBuilder();
        while((temp=bufferedReader.readLine())!=null){
            stringBuilder.append(temp);
        }
        String[] sentences=stringBuilder.toString()
                .replace(" ", "")
                .replace("”","")
                .replace("：“","")
                .replace("？","。")
                .split("。");
        List<String> list = Arrays.asList(sentences);
        return list;
    }

}
