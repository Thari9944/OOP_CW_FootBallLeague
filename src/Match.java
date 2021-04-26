import java.io.Serializable;
import java.time.LocalDate;

public class Match implements Serializable, Comparable<Match> {

    private LocalDate dateOfMatch;
    private String teamOne;
    private int teamOneGoals;
    private String teamTwo;
    private int teamTwoGoals;
    private String stadium;
    private String winnerOrDraws;
    private String clubType;

    public Match(LocalDate date, String teamOne, int teamOneGoals, String teamTwo, int teamTwoGoals, String stadium, String type){
        this.dateOfMatch = date;
        this.teamOne = teamOne;
        this.teamOneGoals = teamOneGoals;
        this.teamTwo = teamTwo;
        this.teamTwoGoals = teamTwoGoals;
        this.stadium = stadium;
        this.clubType = type;
        this.setWinnerOrDraws();
    }

    public void setDateOfMatch(LocalDate date){
        this.dateOfMatch = date;
    }

    public LocalDate getDateOfMatch(){
        return this.dateOfMatch;
    }

    public void setTeamOne(String teamOne){
        this.teamOne = teamOne;
    }

    public String getTeamOne(){
        return this.teamOne;
    }

    public void setTeamOneGoals(int goals){
        this.teamOneGoals = goals;
    }

    public int getTeamOneGoals(){
        return this.teamOneGoals;
    }

    public void setTeamTwo(String teamTwo){
        this.teamTwo = teamTwo;
    }

    public String getTeamTwo(){
        return this.teamTwo;
    }

    public void setTeamTwoGoals(int goals){
        this.teamTwoGoals = goals;
    }

    public int getTeamTwoGoals(){
        return this.teamTwoGoals;
    }

    public void setStadium(String stadium){
        this.stadium = stadium;
    }

    public String getStadium(){
        return this.stadium;
    }

    public void setWinnerOrDraws(){
        if(this.teamOneGoals > this.teamTwoGoals){
            this.winnerOrDraws = this.teamOne;
        } else if(this.teamOneGoals < this.teamTwoGoals){
            this.winnerOrDraws = this.teamTwo;
        }else{
            this.winnerOrDraws = "draw";
        }
    }

    public String getWinnerOrDraws(){
        return this.winnerOrDraws;
    }

    public void setClubType(String type){
        this.clubType = type;
    }

    public String getClubType(){
        return this.clubType;
    }

    public String toString(){
        return "Match Date: " + this.dateOfMatch +" At - " + this.stadium + "[Teams: " + this.teamOne + "(" + this.teamOneGoals + "), " + this.teamTwo + "(" + this.teamOneGoals  + ") Winner: " + this.winnerOrDraws +"]";
    }

    public int compareTo(Match match){
        return this.getDateOfMatch().compareTo(match.getDateOfMatch());
    }
}