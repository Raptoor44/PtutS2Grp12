package sample;

public class Score {

    private int points;
    private long startTime;
    private long totalTimePased;
    private String answer;


    public void startExercice(){
        startTime = System.currentTimeMillis();
    }

    public long getTimePassed(){
        return System.currentTimeMillis() - startTime;
    }

    public int getPoints() {
        return points;
    }

    public void stopTime(){
        totalTimePased = getTimePassed();
    }

    public long getTotalTimePased() {
        return totalTimePased;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
