package sample;

import java.util.*;

public class Exercise {
    private List<Word> words;
    private String score;
    private String script;
    private String occultedString;

    public Exercise(String script){
        words = new ArrayList<>();
        score = "";
        this.script = script;
        initialize();
        occultedString = script.replaceAll("[A-Za-z0-9]", "#");
    }

    public List<Word> getWords() {
        return words;
    }

    private void initialize() {
        String script2 = format(script);
        int index = 0;

        for (String str : script2.split("\\s+")) {
            index = script2.indexOf(str, index);
            words.add(new Word(str, index));
        }

        for (Word word : words) {
            System.out.println(word.getValue());
        }
    }

    private void discoverWord(int nbLettres, String str) {
        if (str.length() < nbLettres) {
            return;
        }

        for (Word word : words) {
            if (word.getValue().startsWith(str)) {
                word.setDiscovered();
            }
        }

        updateScore();
    }

    public void discoverWord(String str) {
        for(Word w : words){
            if(w.getValue().equals(str)){
                w.setDiscovered();
            }
        }
        updateScore();
    }


    private String format(String str) {
        str = str.replaceAll("\n", " ");
        str = str.replaceAll("\\.", " ");
        str = str.replaceAll(",", " ");
        str = str.replaceAll("\\?", " ");
        str = str.replaceAll("'", " ");
        str = str.replaceAll("!", " ");
        str = str.replaceAll("\\[", " ");
        str = str.replaceAll("]", " ");
        str = str.replaceAll(":", " ");
        str = str.replaceAll(" - ", " ");
        str = str.replaceAll("’", " ");

        return str;
    }


    private void updateScore() {
        int count = 0;
        for(Word w : words){
            if(w.isDiscovered()){
                count++;
            }
        }
        setScore(String.format("Score : %d / %d", count, words.size()));
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore(){
        return score;
    }

    public String getOccultedString() {
        return occultedString;
    }

    public String buildOccultedScript(){
        StringBuffer discoverWord = new StringBuffer(occultedString);

        for(Word w : words){
            if(w.isDiscovered()){
                for(int i = 0; i < w.getLength(); i++){
                    discoverWord.setCharAt(w.getIndex() + i, w.getValue().charAt(i));
                }
            }
        }

        return discoverWord.toString();
    }

    public String getScript() {
        return script;
    }

}
