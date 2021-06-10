package sample;

import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        String script = "Where is the nearest tube / underground (USA subway) station? Go straight a ahead and take the second right. Take the dual carriageway towards the airport and then turn right just before the fire station";
        TextAfficheur textAfficheur = new TextAfficheur(script, '#');
        List<Word> wordList = textAfficheur.getWords();
        System.out.println(script.length());

        for(Word word : wordList){
            System.out.println(word.getValue() + " " + word.getIndex());
        }

        Scanner scanner = new Scanner(System.in);
        String str = "";

        while (true){
            System.out.println(textAfficheur.buildOccultedScript());
            str = scanner.next();

            textAfficheur.discoverWord(str, 2);
            textAfficheur.getScore();
        }

    }

}
