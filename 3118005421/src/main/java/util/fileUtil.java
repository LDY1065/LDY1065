package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileUtil {

    public static void main(String[] args) throws IOException {

    }

    public static boolean writeAnswerToFile(String answer,String fileName) {
        File file=new File(fileName);
        if(fileName.indexOf(".txt")!=-1){
            String[] split = fileName.split("/");
            fileName=fileName.replace("/"+split[split.length-1],"");
            System.out.println(fileName);
        }
        File answerDirectory=new File(fileName);
        if(!answerDirectory.exists()){
            answerDirectory.mkdirs();
        }
        try {
            file.createNewFile();
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(answer);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}