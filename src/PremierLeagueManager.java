
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PremierLeagueManager implements LeagueManager {

    private static ArrayList<FootBallClub> leagueTeams = new ArrayList<FootBallClub>();
    private static ArrayList<Match> leagueMatches = new ArrayList<Match>();

    //add foot ball club to the leagueTeams arraylist
    @Override
    public void addClubToLeague(FootBallClub team) {
        leagueTeams.add(team);
        System.out.println(team.getClubName() + " team has successfully added to the premier league.");
    }

    //delete foot ball club from arraylist
    @Override
    public void deleteLeagueTeams(String type, String name){
        boolean isDone = false;
        if (leagueTeams.isEmpty()){
            System.out.println("\tThere aren't any foot ball clubs in premier league.");
            isDone = true;
        }else if (type.equals("c")){
            for (int i = 0; i < leagueTeams.size(); i++){
                //check the array list and get foot ball club objects
                if (!(leagueTeams.get(i) instanceof UniversityFootBallClub) && !(leagueTeams.get(i) instanceof SchoolFootBallClub)){
                    if (leagueTeams.get(i).getClubName().equals(name)){
                        leagueTeams.remove(i);
                        System.out.println(name + " team successfully removed from premier league.");
                        isDone = true;
                        break;
                    }
                }
            }
        }else if (type.equals("u")){
            for (int i = 0; i < leagueTeams.size(); i++){
                //check and get university clubs
                if (leagueTeams.get(i) instanceof UniversityFootBallClub){
                    if (leagueTeams.get(i).getClubName().equals(name)){
                        leagueTeams.remove(i);
                        System.out.println(name + " team successfully removed from premier league.");
                        isDone = true;
                        break;
                    }
                }
            }
        }else {
            for (int i = 0; i < leagueTeams.size(); i++){
                //check and get school club objects
                if (leagueTeams.get(i) instanceof SchoolFootBallClub){
                    if (leagueTeams.get(i).getClubName().equals(name)){
                        leagueTeams.remove(i);
                        System.out.println(name + " team successfully removed from premier league.");
                        isDone = true;
                        break;
                    }
                }
            }
        }
        //if the name cannot find application display this message
        if (!isDone){
            System.out.println("\tCannot find team - " + name + ". Please check club name.");
        }
    }


    @Override
    public void displayTeamInLeague(String type, String name){
        boolean isDone = false;
        if (leagueTeams.isEmpty()){
            System.out.println("\tThere aren't any foot ball clubs in premier league.");
            isDone = true;
        }else if (type.equals("c")){
            for (int i = 0; i < leagueTeams.size(); i++){
                if (!(leagueTeams.get(i) instanceof UniversityFootBallClub) && !(leagueTeams.get(i) instanceof SchoolFootBallClub)){
                    if (leagueTeams.get(i).getClubName().equals(name)){
                        System.out.println(leagueTeams.get(i));
                        isDone = true;
                        break;
                    }
                }
            }
        }else if (type.equals("u")){
            for (int i = 0; i < leagueTeams.size(); i++){
                if (leagueTeams.get(i) instanceof UniversityFootBallClub){
                    if (leagueTeams.get(i).getClubName().equals(name)){
                        System.out.println((UniversityFootBallClub) leagueTeams.get(i));
                        isDone = true;
                        break;
                    }
                }
            }
        }else {
            for (int i = 0; i < leagueTeams.size(); i++){
                if (leagueTeams.get(i) instanceof SchoolFootBallClub){
                    if (leagueTeams.get(i).getClubName().equals(name)){
                        System.out.println((SchoolFootBallClub) leagueTeams.get(i));
                        isDone = true;
                        break;
                    }
                }
            }
        }
        //if the name cannot find application display this message
        if (!isDone){
            System.out.println("Cannot find team - " + name + ". Please check club name.");
        }
    }

    @Override
    public void displayPointTable(String type) {
        ArrayList<FootBallClub> fcTeams = new ArrayList<FootBallClub>();
        if (leagueTeams.isEmpty()) {
            System.out.println("\tThere aren't any foot ball clubs in premier league.");
        } else if (type.equals("c")) {
            for (int i = 0; i < leagueTeams.size(); i++) {
                if (!(leagueTeams.get(i) instanceof UniversityFootBallClub) && !(leagueTeams.get(i) instanceof SchoolFootBallClub)) {
                    fcTeams.add(leagueTeams.get(i));
                }
            }
            Collections.sort(fcTeams);
            System.out.println("\n\t\t\t\t\t\tFoot Ball Club Point Table");
            displayPointTable(fcTeams);
        } else if (type.equals("u")) {
            for (int i = 0; i < leagueTeams.size(); i++) {
                if (leagueTeams.get(i) instanceof UniversityFootBallClub) {
                    fcTeams.add(leagueTeams.get(i));
                }
            }
            Collections.sort(fcTeams);
            System.out.println("\n\t\t\t\t\t\tUniversity Foot Ball Club Point Table");
            displayPointTable(fcTeams);
        } else {
            for (int i = 0; i < leagueTeams.size(); i++) {
                if (leagueTeams.get(i) instanceof SchoolFootBallClub) {
                    fcTeams.add(leagueTeams.get(i));
                }
            }
            Collections.sort(fcTeams);
            System.out.println("\n\t\t\t\t\t\tSchool Foot Ball Club Point Table");
            displayPointTable(fcTeams);
        }
        fcTeams.clear();
    }

    //common method to display point table
    public void displayPointTable(ArrayList<FootBallClub> team){
        if (team.isEmpty()){
            System.out.println("\n\tThere aren't any foot ball clubs in premier league.");
        }else {
            String format = " | %-3s | %-30s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-6s |%n";
            System.out.format(" =-----=--------------------------------=-------=-------=-------=-------=-------=-------=-------=-------=--------=%n");
            System.out.format(" |  #  |    Club Name                   |Matches|  Won  | Drawn | Lost  |  GS   |  GR   |  GD   |  Pts  | Win Pr.|%n");
            System.out.format(" |-----+--------------------------------+-------+-------+-------+-------+-------+-------+-------+-------+--------|%n");
            for (int i = 0; i < team.size(); i++) {
                String clubName;
                if (team.get(i).getClubName().length() > 20) {
                    clubName = team.get(i).getClubName().substring(0, 30);
                } else {
                    clubName = team.get(i).getClubName();
                }
                System.out.format(format, (i + 1), clubName, team.get(i).getNumOfMatchesPlayed(), team.get(i).getNumOfWins(), team.get(i).getNumOfDraws(), team.get(i).getNumOfDefeats(), team.get(i).getNumOfGoalsScored(), team.get(i).getNumOfGoalsReceived(), team.get(i).getGoalDifference(), team.get(i).getPoints(), team.get(i).getWinningPercentage());
                if (i == (team.size() - 1)) {
                    System.out.format(" -----------------------------------------------------------------------------------------------------------------%n");
                } else {
                    System.out.format(" |-----+--------------------------------+-------+-------+-------+-------+-------+-------+-------+-------+--------|%n");
                }
            }
        }
    }

    @Override
    public void addMatchDetails(Match match, String type){
        leagueMatches.add(match);
        System.out.println("Match details successfully added to league.");
        if (type.equals("c")) {
            for (int i = 0; i < leagueTeams.size(); i++) {
                if (!(leagueTeams.get(i) instanceof UniversityFootBallClub) && !(leagueTeams.get(i) instanceof SchoolFootBallClub)){
                    addMatchDetails(match, i);
                }
            }
        }else if (type.equals("u")) {
            for (int i = 0; i < leagueTeams.size(); i++) {
                if (leagueTeams.get(i) instanceof UniversityFootBallClub) {
                    addMatchDetails(match, i);
                }
            }
        }else {
            for (int i = 0; i < leagueTeams.size(); i++) {
                if (leagueTeams.get(i) instanceof SchoolFootBallClub) {
                    addMatchDetails(match, i);
                }
            }
        }

    }

    //common method for all club types overload the addMatchDetails() method
    public void addMatchDetails(Match match, int index) {
        //enter the match details to clubs
        if (leagueTeams.get(index).getClubName().equals(match.getTeamOne()) || leagueTeams.get(index).getClubName().equals(match.getTeamTwo())) {
            leagueTeams.get(index).setNumOfMatchesPlayed(1);
            if (match.getWinnerOrDraws().equals("draw")) {
                leagueTeams.get(index).setNumOfDraws(1);
                leagueTeams.get(index).setPoints(1);
            } else if (match.getWinnerOrDraws().equals(leagueTeams.get(index).getClubName())) {
                leagueTeams.get(index).setNumOfWins(1);
                leagueTeams.get(index).setPoints(3);
            } else {
                leagueTeams.get(index).setNumOfDefeats(1);
            }
            if (leagueTeams.get(index).getClubName().equals(match.getTeamOne())){
                leagueTeams.get(index).setNumOfGoalsScored(match.getTeamOneGoals());
                leagueTeams.get(index).setNumOfGoalsReceived(match.getTeamTwoGoals());
            }else if (leagueTeams.get(index).getClubName().equals(match.getTeamTwo())){
                leagueTeams.get(index).setNumOfGoalsScored(match.getTeamTwoGoals());
                leagueTeams.get(index).setNumOfGoalsReceived(match.getTeamOneGoals());
            }
            leagueTeams.get(index).setGoalDifference();
            leagueTeams.get(index).setWinningPercentage();
        }
    }

    //load data from txt file add method call in the run in program not in a menu option
    @Override
    public void loadDataFromTXT() throws IOException, ClassNotFoundException {
        //team details are in the team.txt file. read the file and add to the league teams arraylist
        try {
            File file = new File("teams.txt");
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fileInput);
            for (; ; ) {
                try {
                    FootBallClub fc = (FootBallClub) oi.readObject();
                    leagueTeams.add(fc);
                } catch (EOFException e) {
                    System.out.println("*- All team data had restored to Program. -*");
                    break;
                }
            }
            oi.close();
            fileInput.close();
        }catch (EOFException e){
            System.out.println("**");
        }

        //team details are in the team.txt file. read the file and add to the league teams arraylist
        try {
            File file = new File("matches.txt");
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fileInput);
            for (; ; ) {
                try {
                    Match match = (Match) oi.readObject();
                    leagueMatches.add(match);
                } catch (EOFException e) {
                    System.out.println("*- All match data had restored to Program. -*");
                    break;
                }
            }
            oi.close();
            fileInput.close();
        }catch (EOFException e){
            System.out.println("**");
        }

    }

    // end of the program this method call and save data to file
    @Override
    public void saveDataToTXT() throws IOException{
        FileOutputStream fileOut = new FileOutputStream("teams.txt");
        ObjectOutputStream oo = new ObjectOutputStream(fileOut);
        for (FootBallClub team : leagueTeams){
            oo.writeObject(team);
        }
        oo.close();
        fileOut.close();

        FileOutputStream fileOut1 = new FileOutputStream("matches.txt");
        ObjectOutputStream oo1 = new ObjectOutputStream(fileOut1);
        for (Match match : leagueMatches){
            oo1.writeObject(match);
        }
        oo1.close();
        fileOut1.close();
        System.out.println("Data is stored in txt file.");
    }

    //method for club name is used or not if it used it returns true
    public boolean isUsedName(String type, String teamName){
        if (type.equals("c")){
            for (int i = 0; i < leagueTeams.size(); i++){
                if (!(leagueTeams.get(i) instanceof UniversityFootBallClub) && !(leagueTeams.get(i) instanceof SchoolFootBallClub)){
                    if (leagueTeams.get(i).getClubName().equals(teamName)){
                        return true;
                    }
                }
            }
        }else if (type.equals("u")){
            for (int i = 0; i < leagueTeams.size(); i++){
                if (leagueTeams.get(i) instanceof UniversityFootBallClub){
                    if (leagueTeams.get(i).getClubName().equals(teamName)){
                        return true;
                    }
                }
            }
        }else {
            for (int i = 0; i < leagueTeams.size(); i++){
                if (leagueTeams.get(i) instanceof SchoolFootBallClub){
                    if (leagueTeams.get(i).getClubName().equals(teamName)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void playMatchGUI(int type, AnchorPane pane, AnchorPane paneForMatch,Label labelForMessage, Label labelForDate, Label labelForStadium, Label labelForTeamOne, Label labelForTeamOneGoal, Label labelForTeamTwo, Label labelForTeamTwoGoal, Label labelForSymbol){

        ArrayList<FootBallClub> fcTeams = new ArrayList<FootBallClub>();
        String clubType = "c";
        if (type == 0){
            for (int i =0; i < leagueTeams.size(); i++) {
                if (!(leagueTeams.get(i) instanceof UniversityFootBallClub) && !(leagueTeams.get(i) instanceof SchoolFootBallClub)) {
                    fcTeams.add(leagueTeams.get(i));
                    clubType = "c";
                }
            }
        }else if (type == 1){
            for (int i =0; i < leagueTeams.size(); i++) {
                if (leagueTeams.get(i) instanceof UniversityFootBallClub) {
                    fcTeams.add(leagueTeams.get(i));
                    clubType = "u";
                }
            }
        }else {
            for (int i =0; i < leagueTeams.size(); i++) {
                if (leagueTeams.get(i) instanceof SchoolFootBallClub) {
                    fcTeams.add(leagueTeams.get(i));
                    clubType = "s";
                }
            }
        }
        if (fcTeams.isEmpty() || fcTeams.size() == 1){
            labelForMessage.setText("You can't play match.\nThere is no teams or one team\nin the league.");
            labelForDate.setText("");
            labelForStadium.setText("");
            labelForTeamOne.setText("");
            labelForTeamOneGoal.setText("");
            labelForTeamTwo.setText("");
            labelForTeamTwoGoal.setText("");
            labelForSymbol.setText("");
        }else {
            int teamOne = (int) (Math.random() * fcTeams.size());
            int teamTwo =  (int) (Math.random() * fcTeams.size());
            while (teamOne == teamTwo){
                teamTwo = (int) (Math.random() * fcTeams.size());
            }
            String typeString;
            if (clubType.equals("c")){
                clubType = "Foot Ball Clubs";
            }else if (clubType.equals("u")){
                clubType = "University Foot Ball Clubs";
            }else {
                clubType = "School Foot Ball Clubs";
            }
            Match playMatch = new Match(LocalDate.now(), leagueTeams.get(teamOne).getClubName(), (int) (Math.random() * 10), leagueTeams.get(teamTwo).getClubName(), (int) (Math.random() * 10), leagueTeams.get(teamOne).getStadium(), clubType);
            addMatchDetails(playMatch, clubType);
            labelForDate.setText(playMatch.getDateOfMatch().toString());
            labelForStadium.setText(playMatch.getStadium());
            labelForTeamOne.setText(playMatch.getTeamOne());
            labelForTeamOneGoal.setText(Integer.toString(playMatch.getTeamOneGoals()));
            labelForTeamTwo.setText(playMatch.getTeamTwo());
            labelForTeamTwoGoal.setText( Integer.toString(playMatch.getTeamTwoGoals()));
            labelForSymbol.setText("-");
            labelForMessage.setText("");
        }
    }

    @Override
    public void pointTableGUI(int clubType, String tableType, TableView<FootBallClub> view){

        ArrayList<FootBallClub> fcTeams = new ArrayList<FootBallClub>();
        if (clubType ==0){
            for (int i =0; i < leagueTeams.size(); i++) {
                if (!(leagueTeams.get(i) instanceof UniversityFootBallClub) && !(leagueTeams.get(i) instanceof SchoolFootBallClub)) {
                    fcTeams.add(leagueTeams.get(i));
                }
            }
            sortedArrayListAcTypes(tableType, fcTeams);
        }else if (clubType ==1){
            for (int i =0; i < leagueTeams.size(); i++) {
                if (leagueTeams.get(i) instanceof UniversityFootBallClub) {
                    fcTeams.add(leagueTeams.get(i));
                }
            }
            sortedArrayListAcTypes(tableType, fcTeams);
        }else {
            for (int i =0; i < leagueTeams.size(); i++) {
                if (leagueTeams.get(i) instanceof SchoolFootBallClub) {
                    fcTeams.add(leagueTeams.get(i));
                }
            }
            sortedArrayListAcTypes(tableType, fcTeams);
        }
        ObservableList<FootBallClub> obsList = FXCollections.observableArrayList(fcTeams);
        view.setItems(obsList);
    }

    public ArrayList<FootBallClub> sortedArrayListAcTypes(String tableType, ArrayList<FootBallClub> fcTeams){
        if (tableType.equals("Point Table")){
            Collections.sort(fcTeams);
        }else if (tableType.equals("Highest Goal Table")){
            Comparator<FootBallClub> byGoals = new Comparator<FootBallClub>() {
                @Override
                public int compare(FootBallClub team1, FootBallClub team2) {
                    return team2.getNumOfGoalsScored() - team1.getNumOfGoalsScored();
                }
            };
            Collections.sort(fcTeams, byGoals);
        }else {
            Comparator<FootBallClub> byWins = new Comparator<FootBallClub>() {
                @Override
                public int compare(FootBallClub team1, FootBallClub team2) {
                    return team2.getNumOfWins() - team1.getNumOfWins();
                }
            };
            Collections.sort(fcTeams, byWins);
        }
        return fcTeams;
    }

    @Override
    public void matchDetailsGUI(AnchorPane pane){
        if (leagueMatches.isEmpty()){
            Label labelForNoMatch = new Label("There isn't any match in the League");
            labelForNoMatch.setLayoutX(10);
            labelForNoMatch.setLayoutY(20);
            labelForNoMatch.setStyle("-fx-font-size: 18px");
            pane.getChildren().add(labelForNoMatch);
        }else {
            Collections.sort(leagueMatches);
            for (int i = 0; i < leagueMatches.size(); i++) {
                matchInGUI(leagueMatches.get(i), pane, (250 * i));
            }
        }
    }

    @Override
    public void searchMatchDetailsGUI(LocalDate date, AnchorPane pane){
        int setYAxis = 0;
        boolean isDone = false;
        if (leagueMatches.isEmpty()){
            Label labelForNoMatch = new Label("There isn't any match in the League");
            labelForNoMatch.setLayoutX(10);
            labelForNoMatch.setLayoutY(20);
            labelForNoMatch.setStyle("-fx-font-size: 18px");
            pane.getChildren().add(labelForNoMatch);
        }else {
            for (Match match : leagueMatches) {
                if (match.getDateOfMatch().equals(date)) {
                    matchInGUI(match, pane, (setYAxis * 250));
                    setYAxis++;
                    isDone = true;
                }
            }
            if (!isDone){
                Label labelForNoMatchDate = new Label("There isn't any match in that date.");
                labelForNoMatchDate.setLayoutX(10);
                labelForNoMatchDate.setLayoutY(20);
                labelForNoMatchDate.setStyle("-fx-font-size: 18px");
                pane.getChildren().add(labelForNoMatchDate);
            }
        }
    }

    //common method for display match in search and all matches display method
    public void matchInGUI(Match match, AnchorPane pane, int yAxis){
        AnchorPane paneForMatch = new AnchorPane();
        Label labelForDate = new Label(match.getDateOfMatch().toString());
        labelForDate.setId("labelMT");
        labelForDate.setLayoutX(200);
        labelForDate.setLayoutY(10);
        Label labelForStadium = new Label(match.getStadium());
        labelForStadium.setId("labelMT");
        labelForStadium.setLayoutX(210);
        labelForStadium.setLayoutY(35);
        Label labelForTeamOne = new Label(match.getTeamOne());
        labelForTeamOne.setId("labelMT");
        labelForTeamOne.setLayoutX(10);
        labelForTeamOne.setLayoutY(80);
        Label labelForTeamOneGoal = new Label(Integer.toString(match.getTeamOneGoals()));
        labelForTeamOneGoal.setId("labelMT");
        labelForTeamOneGoal.setLayoutX(25);
        labelForTeamOneGoal.setLayoutY(100);
        Label labelForTeamTwo = new Label(match.getTeamTwo());
        labelForTeamTwo.setId("labelMT");
        labelForTeamTwo.setLayoutX(275);
        labelForTeamTwo.setLayoutY(80);
        Label labelForTeamTwoGoal = new Label(Integer.toString(match.getTeamTwoGoals()));
        labelForTeamTwoGoal.setId("labelMT");
        labelForTeamTwoGoal.setLayoutX(290);
        labelForTeamTwoGoal.setLayoutY(100);
        Label labelForSymbol = new Label("-");
        labelForSymbol.setId("labelMT");
        labelForSymbol.setLayoutX(245);
        labelForSymbol.setLayoutY(80);
        Label labelForType = new Label("Clubs Type - " +match.getClubType());
        labelForType.setId("labelMT");
        labelForType.setLayoutX(10);
        labelForType.setLayoutY(155);
        paneForMatch.setId("matchDetails");
        paneForMatch.setLayoutX(50);
        paneForMatch.setLayoutY(yAxis);
        paneForMatch.getChildren().addAll(labelForDate, labelForStadium, labelForTeamOne, labelForTeamTwo, labelForTeamOneGoal, labelForTeamTwoGoal, labelForSymbol, labelForType);
        pane.getStylesheets().add("styles.css");
        pane.getChildren().add(paneForMatch);

    }

}