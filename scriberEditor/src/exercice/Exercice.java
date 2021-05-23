package exercice;


import java.io.Serializable;

public abstract class Exercice implements Serializable {

    protected String titre;
    protected String consigne;
    protected String script;
    protected boolean caseSensitivity;

    public Exercice(String titre, String consigne, String script, boolean caseSensitivity) {
        this.titre = titre;
        this.consigne = consigne;
        this.script = script;
        this.caseSensitivity = caseSensitivity;
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

    @Override
    public String toString() {
        return "Exercice{" +
                "titre='" + titre + '\'' +
                ", consigne='" + consigne + '\'' +
                ", script='" + script + '\'' +
                ", caseSensitivity=" + caseSensitivity +
                '}';
    }
}
