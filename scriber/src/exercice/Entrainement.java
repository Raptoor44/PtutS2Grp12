package exercice;

public class Entrainement extends Exercice {

    private boolean allowHelp;
    private boolean allowReplacement;
    private String aideText;

    public Entrainement(String titre, String consigne, String script, String aideText, char occultationCharacter, boolean caseSensitivity, boolean allowHelp, boolean allowReplacement) {
        super(titre, consigne, script, occultationCharacter, caseSensitivity);
        this.allowHelp = allowHelp;
        this.allowReplacement =  allowReplacement;
        this.aideText = aideText;
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

    @Override
    public String toString() {
        return "Entrainement{" +
                "allowHelp=" + allowHelp +
                ", allowReplacement=" + allowReplacement +
                ", titre='" + titre + '\'' +
                ", consigne='" + consigne + '\'' +
                ", script='" + script + '\'' +
                ", caseSensitivity=" + caseSensitivity +
                ", help text =" + aideText +
                ", occultation character =" + occultationCharacter +

                '}';
    }

}
