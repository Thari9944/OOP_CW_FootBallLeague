import java.io.Serializable;

public class SportsClub implements Serializable {

    private String clubName;
    private String clubLocation;
    private String managerName;
    private String stadium;
    private int numOfClubWins;
    private int numOfAwards;

    public SportsClub(String name, String location, String manager, String stadium, int wins, int awards){
        this.clubName = name;
        this.clubLocation = location;
        this.managerName = manager;
        this.stadium = stadium;
        this.numOfClubWins = wins;
        this.numOfAwards = awards;
    }

    public void setClubName(String name){
        this.clubName = name;
    }

    public String getClubName(){
        return this.clubName;
    }

    public void setClubLocation(String location){
        this.clubLocation = location;
    }

    public String getClubLocation(){
        return this.clubLocation;
    }

    public void setManagerName(String name){
        this.managerName = name;
    }

    public String getManagerName(){
        return this.managerName;
    }

    public void setStadium(String stadium){
        this.stadium = stadium;
    }

    public String getStadium(){
        return this.stadium;
    }

    public void setNumOfClubWins(int wins){
        this.numOfClubWins = wins;
    }

    public int getNumOfClubWins(){
        return this.numOfClubWins;
    }

    public void setNumOfAwards(int awards){
        this.numOfAwards = awards;
    }

    public int getNumOfAwards(){
        return this.numOfAwards;
    }
}