package exercice;

public class Evaluation extends Exercice {

    private float tempAlouer;

    public Evaluation(String titre, String mediaFile, String consigne, Boolean remplacementPartielEstAutoriser, Boolean sensibiliterCaseEstActiver, float tempAlouer) {
        super(titre, mediaFile, consigne, remplacementPartielEstAutoriser, sensibiliterCaseEstActiver);
        this.tempAlouer = tempAlouer;
    }


}
