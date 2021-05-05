package sample;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private List<Word> words;
    private String score;

    public void initialize(String script) {
        script = format(script);
        words = new ArrayList<>();

        for (String str : script.split("\\s+")) {
            words.add(new Word(str));
        }

        for (Word word : words) {
            System.out.println(word.getWord());
        }
    }

    private void discoverWord(List<Word> words, int nbLettres, String str) {
        if (str.length() < nbLettres) {
            return;
        }

        for (Word word : words) {
            if (word.getWord().startsWith(str)) {
                word.setDiscovered(true);
            }
        }
    }

    private void discoverWord(String str, List<Word> words) {
        words.stream().filter(word -> !word.isDiscovered())
                .filter(word -> word.getWord().toLowerCase().startsWith(str.toLowerCase()))
                .forEach(word -> word.setDiscovered(true));
    }


    private String format(String str) {
        str = str.replaceAll("\n", " ");
        str = str.replaceAll("\\.", " ");
        str = str.replaceAll(",", " ");
        str = str.replaceAll("\\?", " ");
        str = str.replaceAll("'", " ");
        str = str.replaceAll("!", " ");
        return str;
    }

    public int getWordDiscovered() {
        int counting = 0;
        for (Word word : words) {
            if (word.isDiscovered()) {
                counting++;
            }
        }
        return counting;
    }

    private void updateScore() {
        long count = words.stream()
                .filter(Word::isDiscovered).map(w -> w.toString().toLowerCase().replaceAll("[^a-z0-9']", ""))
                .distinct().count();
        setScore(String.format("Score : %d / %d", count, getWordDiscovered()));
    }

    public void setScore(String score) {
        this.score = score;
    }
}
