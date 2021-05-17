package exercice;

public class Evaluation extends Exercice {

    // TODO temps en secondes -> int
    private float temps;

    public Evaluation(String titre, String consigne, String script, boolean caseSensitivity, float temps) {
        super(titre, consigne, script, caseSensitivity);
        this.temps = temps;

    }

    public float getTemps() {
        return temps;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "temps=" + temps +
                ", titre='" + titre + '\'' +
                ", consigne='" + consigne + '\'' +
                ", script='" + script + '\'' +
                ", caseSensitivity=" + caseSensitivity +
                '}';
    }
}
