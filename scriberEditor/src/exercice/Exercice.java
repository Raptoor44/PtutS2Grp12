package exercice;


import java.io.Serializable;

public abstract class Exercice implements Serializable {

    protected String titre;
    protected String consigne;
    protected String script;
    protected boolean caseSensitivity;
    protected char occultationCharacter;

    public Exercice(String titre, String consigne, String script, char occultationCharacter, boolean caseSensitivity) {
        this.titre = titre;
        this.consigne = consigne;
        this.script = script;
        this.caseSensitivity = caseSensitivity;
        this.occultationCharacter = occultationCharacter;
    }

    public String getTitre() {
        return titre;
    }

    public String getConsigne() {
        return consigne;
    }

    public String getScript() {
        return script;
    }

    public boolean isCaseSensitive() {
        return caseSensitivity;
    }

    public char getOccultationCharacter() {
        return occultationCharacter;
    }

    @Override
    public String toString() {
        return " { titre='" + titre + '\'' +
                ", consigne='" + consigne + '\'' +
                ", script='" + script + '\'' +
                ", caseSensitivity=" + caseSensitivity +
                ", ouclation character=" + occultationCharacter +
                '}';
    }
}
