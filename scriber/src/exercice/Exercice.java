package exercice;

import java.io.Serializable;

public abstract class Exercice implements Serializable {

    private String titre;
    private String consigne;
    private String script;
    //TODO mettre remplacementPartiel dans Entrainement
    private Boolean remplacementPartielEstAutoriser;
    private Boolean sensibiliterCaseEstActiver;


    public Exercice(String titre, String consigne, String script, Boolean remplacementPartielEstAutoriser, Boolean sensibiliterCaseEstActiver) {
        this.titre = titre;
        this.consigne = consigne;
        this.script = script;
        this.remplacementPartielEstAutoriser = remplacementPartielEstAutoriser;
        this.sensibiliterCaseEstActiver = sensibiliterCaseEstActiver;
    }

    @Override
    public String toString() {
        return "Exercice{" +
                "titre='" + titre + '\'' +
                ", consigne='" + consigne + '\'' +
                ", script='" + script + '\'' +
                ", remplacementPartielEstAutoriser=" + remplacementPartielEstAutoriser +
                ", sensibiliterCaseEstActiver=" + sensibiliterCaseEstActiver +
                '}';
    }
}
