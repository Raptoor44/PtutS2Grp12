package view;

public enum Layout {

    OUVERTURE("Ouverture.fxml"),
    DESCRIPTION_EXERCICE("DescriptionOuverture.fxml"),
    REALISATION_EXERCICE("Exercise.fxml"),
    FIN_EXERCICE("End.fxml");

    private String pathToFile;

    Layout(String pathToFile){
        this.pathToFile = pathToFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }
}
