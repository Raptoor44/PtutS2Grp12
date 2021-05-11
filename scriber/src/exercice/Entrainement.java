package exercice;

public class Entrainement extends Exercice {

    private boolean aideEstAutoriser;

    public Entrainement(String titre, String consigne, String script, Boolean remplacementPartielEstAutoriser, Boolean sensibiliterCaseEstActiver, boolean aideEstAutoriser) {
        super(titre, consigne, script, remplacementPartielEstAutoriser, sensibiliterCaseEstActiver);
        this.aideEstAutoriser = aideEstAutoriser;
    }
}
