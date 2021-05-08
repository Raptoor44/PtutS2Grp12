package exercice;

public class Evaluation extends Exercice {

    private float tempAlouer;

    public Evaluation(String titre, String consigne, Boolean remplacementPartielEstAutoriser, Boolean sensibiliterCaseEstActiver, float tempAlouer) {
        super(titre, consigne, remplacementPartielEstAutoriser, sensibiliterCaseEstActiver);
        this.tempAlouer = tempAlouer;
    }


}
