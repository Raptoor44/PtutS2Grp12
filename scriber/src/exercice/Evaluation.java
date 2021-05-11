package exercice;

public class Evaluation extends Exercice {

    private float tempAlouer;

    public Evaluation(String titre, String consigne, String script, Boolean remplacementPartielEstAutoriser, Boolean sensibiliterCaseEstActiver, float tempAlouer) {
        super(titre, consigne, script, remplacementPartielEstAutoriser, sensibiliterCaseEstActiver);
        this.tempAlouer = tempAlouer;
    }

    @Override
    public String toString() {
        return super.toString() +
                "tempAlouer=" + tempAlouer +
                '}';
    }
}
