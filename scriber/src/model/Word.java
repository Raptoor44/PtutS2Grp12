package model;

import java.util.Objects;

public class Word {

    private boolean discovered;
    private boolean partiallyDiscovered;
    private String value;
    private int index;
    private int length;
    private int partialLength;

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

    public boolean isPartiallyDiscovered() {
        return partiallyDiscovered;
    }

    public void setPartiallyDiscovered() {
        this.partiallyDiscovered = true;
    }

    public int getPartialLength() {
        return partialLength;
    }

    public void setPartialLength(int partialLength){
        this.partialLength = partialLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return value.equals(word.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
