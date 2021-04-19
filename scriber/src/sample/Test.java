package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public List<Word> words;

    public void initialize(String script){
        script = format(script);
        words = new ArrayList<>();
        for(String str : Arrays.asList(script.split("\\s+"))){
            words.add(new Word(str));
        }

        for(Word word : words){
            System.out.println(word.getWord());
        }
    }

    private void discoverWord(List<Word> words, int nbLettres, String str){
        if(str.length() < nbLettres){
            return;
        }
        for(Word word : words){
            for(int i = 0; i < nbLettres; i++){
                if(word.getWord().startsWith(str)){
                    word.setDiscovered(true);
                }
            }
        }
    }


    private String format(String str){
        str = str.replaceAll("\n", " ");
        str = str.replaceAll("\\.", " ");
        str = str.replaceAll(",", " ");
        str = str.replaceAll("\\?", " ");
        str = str.replaceAll("'", " ");
        str = str.replaceAll("!", " ");
        return str;
    }
    /*
    private void updateScore(){
        long count = words.stream().filter(Word::isKnown).map(w -> w.toString().toLowerCase().replaceAll("[^a-z0-9']", "")).distinct().count();
        score.setText(String.format("Score : %d / %d", count, exercise.getWordCount()));
    }
    */

}
