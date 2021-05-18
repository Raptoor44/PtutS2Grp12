package exercice;

public class Entrainement extends Exercice {

    private boolean allowHelp;
    private boolean allowReplacement;

    public Entrainement(String titre, String consigne, String script, boolean caseSensitivity, boolean allowHelp, boolean allowReplacement) {
        super(titre, consigne, script, caseSensitivity);
        this.allowHelp = allowHelp;
        this.allowReplacement =  allowReplacement;
    }

    public boolean isReplacementAllowed() {
        return allowReplacement;
    }

    public boolean isHelpAllowed() {
        return allowHelp;
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
                '}';
    }

}
