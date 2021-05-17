package exercice;

public class Evaluation extends Exercice {

    private float tempAlouer;

    public float getTempAlouer() {
        return tempAlouer;
    }

    public Evaluation(String titre, String consigne, String script, Boolean sensibiliterCaseEstActiver, float tempAlouer) {
        super(titre, consigne, script, sensibiliterCaseEstActiver);
        this.tempAlouer = tempAlouer;

    }


    @Override
    public String toString() {
        return super.toString() +
                "tempAlouer=" + tempAlouer +
                '}';
    }

}
