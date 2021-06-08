package exercice;

public class Entrainement extends Exercice {

    private boolean allowHelp;
    private boolean allowReplacement;
    private int nbLetterMinimum;
    private boolean allowDisplayingSolution;
    private boolean allowDisplayNbWordDiscover;
    private String aideText;

    public Entrainement(String titre, String consigne, String script, String aideText, char occultationCharacter, boolean caseSensitivity, boolean allowHelp, boolean allowReplacement, int nbLetterMinimum, boolean allowDisplayingSolution, boolean allowDisplayNbWordDiscover) {
        super(titre, consigne, script, occultationCharacter, caseSensitivity);
        this.allowHelp = allowHelp;
        this.allowReplacement =  allowReplacement;
        this.aideText = aideText;
        this.nbLetterMinimum = nbLetterMinimum;
        this.allowDisplayingSolution = allowDisplayingSolution;
        this.allowDisplayNbWordDiscover = allowDisplayNbWordDiscover;
    }

    public boolean isReplacementAllowed() {
        return allowReplacement;
    }

    public boolean isHelpAllowed() {
        return allowHelp;
    }

    public String getAideText() {
        return aideText;
    }

    public int getNbLetterMinimum() {
        return nbLetterMinimum;
    }

    public boolean isAllowDisplayingSolution() {
        return allowDisplayingSolution;
    }

    public boolean isAllowReplacement() {
        return allowReplacement;
    }

    @Override
    public String toString() {
        return "Entrainement{" + super.toString() +
                "allowHelp=" + allowHelp +
                ", allowReplacement=" + allowReplacement +
                ", aideText='" + aideText + '\'' +
                ", nbLetterMinimum" + nbLetterMinimum +
                ", allowDisplayingSolution " + allowDisplayingSolution +
                ", allowDisplayNbWordDiscover " + allowDisplayNbWordDiscover +

                '}';
    }
}
