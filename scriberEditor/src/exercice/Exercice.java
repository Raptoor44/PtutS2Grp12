package exercice;


import java.io.Serializable;

public abstract class Exercice implements Serializable {

    protected String titre;
    protected String consigne;
    protected String script;
    protected boolean sensibiliterCaseEstActiver;

    public String getTitre() {
        return titre;
    }

    public String getConsigne() {
        return consigne;
    }

    public String getScript() {
        return script;
    }

    public boolean isSensibiliterCaseEstActiver() {
        return sensibiliterCaseEstActiver;
    }

    public Exercice(String titre, String consigne, String script, boolean sensibiliterCaseEstActiver) {
        this.titre = titre;
        this.consigne = consigne;
        this.script = script;
        this.sensibiliterCaseEstActiver = sensibiliterCaseEstActiver;
    }

    @Override
    public String toString() {
        return "Exercice{" +
                "titre='" + titre + '\'' +
                ", consigne='" + consigne + '\'' +
                ", script='" + script + '\'' +
                ", sensibiliterCaseEstActiver=" + sensibiliterCaseEstActiver +
                '}';
    }
}
