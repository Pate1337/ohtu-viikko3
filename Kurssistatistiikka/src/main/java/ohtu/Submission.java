package ohtu;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }
    
    public int getHours() {
        return hours;
    }
    
    public int[] getExercises() {
        return exercises;
    }

//    @Override
    public String tehtävät() {
        String tehtävät = "";
        for (int tehtävä: exercises) {
            tehtävät = tehtävät + tehtävä + " ";
        }
        return tehtävät;
    }
    
}