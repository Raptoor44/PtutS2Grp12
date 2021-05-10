package sample;

public class Word {

    private boolean discovered;
    private String value;
    private int index;
    private int length;

    public Word(String word, int index){
        this.value = word;
        this.index = index;
        length = word.length();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String word) {
        this.value = word;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered() {
        this.discovered = true;
    }

    public int getLength() {
        return length;
    }

    public int getIndex() {
        return index;
    }
}
