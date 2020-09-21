package service;

import org.ujmp.core.Matrix;
import util.IKUtil;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class calculate {

    private IKUtil ikUtil=new IKUtil();

    public  double calculation(String original,String copy) throws IOException {
        List<String> phrase = ikUtil.getPhrase(original, copy);
        HashMap<String,Integer> originalWords = ikUtil.division(original);
        HashMap<String,Integer> copyWords = ikUtil.division(copy);
        int line= phrase.size();
        Matrix originalMatrix=Matrix.Factory.zeros(1,line);
        Matrix copyMatrix=Matrix.Factory.zeros(1,line);
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
        System.out.println(originalMatrix);
        System.out.println(copyMatrix);
        System.out.println(copyMatrix.transpose());
        int vectorProduct=originalMatrix.mtimes(copyMatrix.transpose()).getAsInt(0,0);
        double sum1=squareOriginalMatrix.getValueSum();
        double sum2=squareCopyMatrix.getValueSum();
        System.out.println(vectorProduct);
        System.out.println(sum1);
        System.out.println(sum2);
        return vectorProduct/(Math.sqrt(sum1)*Math.sqrt(sum2));
    }




}
