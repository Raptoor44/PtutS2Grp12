package exercice;

public class Entrainement extends Exercice {

    private boolean aideEstAutoriser;
    private boolean remplacementPartielEstAutoriser;

    public boolean isRemplacementPartielEstAutoriser() {
        return remplacementPartielEstAutoriser;
    }

    public boolean isAideEstAutoriser() {
        return aideEstAutoriser;
    }

    public Entrainement(String titre, String consigne, String script, boolean remplacementPartielEstAutoriser, Boolean sensibiliterCaseEstActiver, boolean aideEstAutoriser) {
        super(titre, consigne, script, sensibiliterCaseEstActiver);
        this.aideEstAutoriser = aideEstAutoriser;
        this.remplacementPartielEstAutoriser =  remplacementPartielEstAutoriser;
    }

    @Override
    public String toString() {
        return "Entrainement{" +
                "aideEstAutoriser=" + aideEstAutoriser +
                ", remplacementPartielEstAutoriser=" + remplacementPartielEstAutoriser +
                ", titre='" + titre + '\'' +
                ", consigne='" + consigne + '\'' +
                ", script='" + script + '\'' +
                ", sensibiliterCaseEstActiver=" + sensibiliterCaseEstActiver +
                '}';
    }
}
