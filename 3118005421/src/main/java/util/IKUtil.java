package util;



import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class IKUtil {

    //分词
    public HashMap<String,Integer> division(String sentence) throws IOException {
        StringReader stringReader=new StringReader(sentence);
        IKSegmenter ikSegmenter=new IKSegmenter(stringReader,true);
        Lexeme lex;
        HashMap<String,Integer> word=new HashMap<>();
        while ((lex=ikSegmenter.next())!=null){
            int times=0;
            if(word.containsKey(lex.getLexemeText())){
                times=word.get(lex.getLexemeText());
                word.put(lex.getLexemeText(),times+1);
            }else{
                word.put(lex.getLexemeText(),1);
            }
        }
        return word;
    }


    //接受两个句子，整理出一个词库
    public List<String> getPhrase(String str1,String str2){
        HashSet<String> phraseSet=new HashSet<String>();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append(str2);
        List<String> phrase=null;
        try {
            HashMap<String,Integer> mapStr=division(stringBuilder.toString());
            mapStr.forEach((key,value)->{phraseSet.add(key);});
            phrase= Arrays.asList(phraseSet.toArray(new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrase;
    }

}
