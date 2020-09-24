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
        //将标点符号替代，切割语句
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
