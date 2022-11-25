package org.example;

import org.example.Parser.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ParserWorker<ArrayList<String>> parser = new ParserWorker<>(new MainWebParser());

        //настройки
        parser.setParserSettings(new Link());
        parser.onCompletedList.add(new Completed());
        parser.onNewDataList.add(new NewData());
        //Стартуем
        parser.Start();
        Thread.sleep(10000);
        int a = 23431;
        parser.Abort();


    }
    static class Completed implements OnCompleted {

        @Override
        public void OnCompleted(Object sender) {
            System.out.println("Complited");
        }
    }
    static class NewData implements OnNewDataHandler<ArrayList<String>> {

        @Override
        public void OnNewData(Object sender, ArrayList<String> args) {
            for (String s : args) {
                System.out.println(s);
            }
        }
    }


}