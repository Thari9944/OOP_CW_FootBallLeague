import java.io.Serializable;

public class FootBallClub extends SportsClub implements Comparable<FootBallClub> , Serializable {

    private int numOfMatchesPlayed;
    private int numOfWins;
    private int numOfDraws;
    private int numOfDefeats;
    private int numOfGoalsScored;
    private int numOfGoalsReceived;
    private int goalDifference;
    private int points;
    private double winningPercentage;

    public FootBallClub(String name, String location, String manager, String stadium, int wins, int awards){
        super(name, location, manager, stadium, wins, awards);
    }

    public void setNumOfMatchesPlayed(int matches){
        this.numOfMatchesPlayed += matches;
    }

    public int getNumOfMatchesPlayed(){
        return this.numOfMatchesPlayed;
    }

    public void setNumOfWins(int wins){
        this.numOfWins += wins;
    }

    public int getNumOfWins(){
        return this.numOfWins;
    }

    public void setNumOfDraws(int draws){
        this.numOfDraws += draws;
    }

    public int getNumOfDraws(){
        return this.numOfDraws;
    }

    public void setNumOfDefeats(int defeats){
        this.numOfDefeats += defeats;
    }

    public int getNumOfDefeats(){
        return this.numOfDefeats;
    }

    public void setWinningPercentage(){
        if (this.numOfMatchesPlayed == 0){
            this.winningPercentage = 0;
        }else{
            this.winningPercentage = (double) Math.round((((double) this.numOfWins/this.numOfMatchesPlayed) *100)*100)/100;
        }
    }

    public double getWinningPercentage(){
        return this.winningPercentage;
    }

    public void setNumOfGoalsScored(int goals){
        this.numOfGoalsScored += goals;
    }

    public int getNumOfGoalsScored(){
        return this.numOfGoalsScored;
    }

    public void setNumOfGoalsReceived(int goals){
        this.numOfGoalsReceived += goals;
    }

    public int getNumOfGoalsReceived(){
        return this.numOfGoalsReceived;
    }

    public void setGoalDifference(){
        this.goalDifference = this.numOfGoalsScored - this.numOfGoalsReceived;
    }

    public int getGoalDifference(){
        return this.goalDifference;
    }

    public void setPoints(int points){
        this.points += points;
    }

    public int getPoints(){
        return this.points;
    }

    public String toString(){
        String blue = "\u001B[34m";
        String defaultCol = "\u001B[0m";
        return blue + super.getClubName() + "(" + super.getClubLocation() + ") " +
                "\nManager:" + super.getManagerName() +
                "\nStadium: " + super.getStadium() +
                "\n[Wins: " + this.numOfWins +" Draws: " + this.numOfDraws + " Defeats: " + this.numOfDefeats + " Winning Percentage: " + this.winningPercentage + "% Points: " + this.points + "]" + defaultCol;
    }

    public int compareTo(FootBallClub club){
        if (this.points < club.getPoints()){
            return 1;
        }else if (this.points > club.getPoints()){
            return -1;
        }else if (this.points == club.getPoints()){
            if (this.numOfGoalsScored < club.getNumOfGoalsScored()){
                return 1;
            }else {
                return -1;
            }
        }
        return 0;
    }
}