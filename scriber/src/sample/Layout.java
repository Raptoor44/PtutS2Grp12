package sample;

public enum Layout {

    OUVERTURE("Ouverture.fxml"),
    DESCRIPTION_EXERCICE("DescriptionOuverture.fxml"),
    REALISATION_EXERCICE("Exercise.fxml");

    private String pathToFile;

    Layout(String pathToFile){
        this.pathToFile = pathToFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }
}
