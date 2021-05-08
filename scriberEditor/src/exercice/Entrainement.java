package exercice;

public class Entrainement extends Exercice {

    private boolean aideEstAutoriser;

    public Entrainement(String titre, String consigne, Boolean remplacementPartielEstAutoriser, Boolean sensibiliterCaseEstActiver, boolean aideEstAutoriser) {
        super(titre, consigne, remplacementPartielEstAutoriser, sensibiliterCaseEstActiver);
        this.aideEstAutoriser = aideEstAutoriser;
    }
}
