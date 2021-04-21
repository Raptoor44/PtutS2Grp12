package exercice;

public class Entrainement extends Exercice {

    private boolean aideEstAutoriser;

    public Entrainement(String titre, String mediaFile, String consigne, Boolean remplacementPartielEstAutoriser, Boolean sensibiliterCaseEstActiver, boolean aideEstAutoriser) {
        super(titre, mediaFile, consigne, remplacementPartielEstAutoriser, sensibiliterCaseEstActiver);
        this.aideEstAutoriser = aideEstAutoriser;
    }
}
