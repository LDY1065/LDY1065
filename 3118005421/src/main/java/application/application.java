package application;

import service.calculate;
import util.articleUtil;
import util.fileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class application {

    private static final Integer MAX = 5;
    private static articleUtil articleUtil=new articleUtil();
    private static calculate calculate=new calculate();


    static class myForkJoinTask extends RecursiveTask<Double> {
        private List<String> originalDivision;
        private List<String> copyDivision;

        public myForkJoinTask(List<String> originalDivision,List<String> copyDivision){
            this.copyDivision=copyDivision;
            this.originalDivision=originalDivision;
        }

        @Override
        protected Double compute() {
            if(copyDivision.size()>=MAX){
                myForkJoinTask subTask1=new myForkJoinTask(originalDivision,copyDivision.subList(0,copyDivision.size()/2));
                myForkJoinTask subTask2=new myForkJoinTask(originalDivision,copyDivision.subList(copyDivision.size()/2,copyDivision.size()));
                invokeAll(subTask1,subTask2);
                return (subTask1.join()+subTask2.join())/2;
            }else {
                double max=0;
                double sum=0;
                for (String s : copyDivision) {
                    for (String s1 : originalDivision) {
                        try {
                            double result = calculate.calculation(s1, s);
                            if(result>max)
                                max=result;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    sum+=max;
                    max=0;
                }
                return sum/copyDivision.size();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        if(args.length<3){
            System.out.println("请输入正确的命令行参数");
            System.exit(0);
        }
        File original=new File(args[0]);
        File copy=new File(args[1]);
        if(!original.isFile()||!copy.isFile()){
            System.out.println("请输入正确的路径");
            return;
        }
        if(original.length()==0||copy.length()==0){
            System.out.println("请不要输入空文件");
            return;
        }
        List<String> originalDivision = articleUtil.divisionArticle(original);
        List<String> copyDivision = articleUtil.divisionArticle(copy);
        ForkJoinPool pool=new ForkJoinPool();
        ForkJoinTask<Double> taskFuture=pool.submit(new myForkJoinTask(originalDivision,copyDivision));
        try {
            Double result = taskFuture.get();
            if(!fileUtil.writeAnswerToFile(String.format(result.toString(),"%.2f"),args[2])){
                System.out.println("输出文件路径有误");
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        /*double max=0;
        double sum=0;
        for (String s : copyDivision) {
            for (String s1 : originalDivision) {
                double calculation = calculate.calculation(s1, s);
                if(calculation>max)
                    max=calculation;
            }
            sum+=max;
            max=0;
        }
        System.out.println(sum/copyDivision.size());*/

    }


}
