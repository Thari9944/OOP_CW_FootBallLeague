import java.io.Serializable;

public class UniversityFootBallClub extends FootBallClub implements Serializable {

    //under 23 teams
    private String universityName;
    private String universityLocation;

    public UniversityFootBallClub(String name, String location, String manager, String stadium, int wins, int awards, String universityName, String universityLocation){
        super(name, location, manager, stadium, wins, awards);
        this.universityName = universityName;
        this.universityLocation = universityLocation;
    }

    public void setUniversityName(String name){
        this.universityName = name;
    }

    public String getUniversityName(){
        return this.universityName;
    }

    public void setUniversityLocation(String location){
        this.universityLocation = location;
    }

    public String getSchoolLocation(){
        return this.universityLocation;
    }

    public String toString(){
        String blue = "\u001B[34m";
        String defaultCol = "\u001B[0m";
        return blue + super.getClubName() + "(" + super.getClubLocation() + ")" +
                "\nUniversity Name: " + this.universityName + "(" + this.universityLocation +") " +
                "\nManager:" + super.getManagerName() +
                "\nStadium: " + super.getStadium() +
                "\n[Wins: " + super.getNumOfWins() +" Draws: " + super.getNumOfDraws() + " Defeats: " + super.getNumOfDefeats() + " Winning Precentage: " + super.getWinningPercentage() + "% Points: " + super.getPoints() + "]" + defaultCol;
    }
}