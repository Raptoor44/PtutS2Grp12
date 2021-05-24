package exercice;

public class Evaluation extends Exercice {

    private int temps;

    public Evaluation(String titre, String consigne, String script,char occultationCharacter, boolean caseSensitivity, int temps) {
        super(titre, consigne, script, occultationCharacter, caseSensitivity);
        this.temps = temps;

    }

    public int getTemps() {
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
                ", occultation character =" + occultationCharacter +

                '}';
    }

}
