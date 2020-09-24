package service;

import org.ujmp.core.Matrix;
import util.hanlpUtil;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class calculate {

    private hanlpUtil hanlpUtil=new hanlpUtil();


    //语句相似度计算
    public double calculation(String original,String copy) throws IOException {
        List<String> phrase = hanlpUtil.getPhrase(original, copy);
        HashMap<String,Integer> originalWords = hanlpUtil.division(original);
        HashMap<String,Integer> copyWords = hanlpUtil.division(copy);
        int line= phrase.size();
        //这里是创建零向量
        Matrix originalMatrix=Matrix.Factory.zeros(1,line);
        Matrix copyMatrix=Matrix.Factory.zeros(1,line);
        //计算向量模的矩阵
        Matrix squareOriginalMatrix=Matrix.Factory.zeros(1,line);
        Matrix squareCopyMatrix=Matrix.Factory.zeros(1,line);

        int i=0;
        for (String s : phrase) {
            if(originalWords.containsKey(s)){
                int value=originalWords.get(s);
                originalMatrix.setAsInt(value,0,i);
                squareOriginalMatrix.setAsInt(value*value,0,i);
            }
            if(copyWords.containsKey(s)){
                int value=copyWords.get(s);
                copyMatrix.setAsInt(value,0,i);
                squareCopyMatrix.setAsInt(value*value,0,i);
            }
            i++;
        }
        int vectorProduct=originalMatrix.mtimes(copyMatrix.transpose()).getAsInt(0,0);
        double sum1=squareOriginalMatrix.getValueSum();
        double sum2=squareCopyMatrix.getValueSum();
        return vectorProduct/(Math.sqrt(sum1)*Math.sqrt(sum2));
    }



}
