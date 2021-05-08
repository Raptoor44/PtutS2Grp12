package exercice;


import java.io.Serializable;

public abstract class Exercice implements Serializable {

    private String titre;
    private String consigne;
    private Boolean remplacementPartielEstAutoriser;
    private Boolean sensibiliterCaseEstActiver;


    public Exercice(String titre, String consigne, Boolean remplacementPartielEstAutoriser, Boolean sensibiliterCaseEstActiver) {
        this.titre = titre;
        this.consigne = consigne;
        this.remplacementPartielEstAutoriser = remplacementPartielEstAutoriser;
        this.sensibiliterCaseEstActiver = sensibiliterCaseEstActiver;
    }

    @Override
    public String toString() {
        return "Exercice{" +
                "titre='" + titre + '\'' +
                ", consigne='" + consigne + '\'' +
                ", remplacementPartielEstAutoriser=" + remplacementPartielEstAutoriser +
                ", sensibiliterCaseEstActiver=" + sensibiliterCaseEstActiver +
                '}';
    }
}
