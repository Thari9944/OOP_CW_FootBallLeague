import java.io.Serializable;

public class SchoolFootBallClub extends FootBallClub implements Serializable {

    //under 18 teams
    private String schoolName;
    private String schoolLocation;

    public SchoolFootBallClub(String name, String location, String manager, String stadium, int wins, int awards, String schoolName, String schoolLocation){
        super(name, location, manager, stadium, wins, awards);
        this.schoolName = schoolName;
        this.schoolLocation = schoolLocation;
    }

    public void setSchoolName(String name){
        this.schoolName = name;
    }

    public String getSchoolName(){
        return this.schoolName;
    }

    public void setSchoolLocation(String location){
        this.schoolLocation = location;
    }

    public String getSchoolLocation(){
        return this.schoolLocation;
    }

    public String toString(){
        String blue = "\u001B[34m";
        String defaultCol = "\u001B[0m";
        return blue + super.getClubName() + "(" + super.getClubLocation() + ")" +
                "\nSchool Name: " + this.schoolName + "(" + this.schoolLocation +") " +
                "\nManager:" + super.getManagerName() +
                "\nStadium: " + super.getStadium() +
                "\n[Wins: " + super.getNumOfWins() +" Draws: " + super.getNumOfDraws() + " Defeats: " + super.getNumOfDefeats() + " Winning Precentage: " + super.getWinningPercentage() + "% Points: " + super.getPoints() + "]" + defaultCol;
    }
}