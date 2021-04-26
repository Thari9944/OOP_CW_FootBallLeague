import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;

public interface LeagueManager {
    public void addClubToLeague(FootBallClub team);
    public void deleteLeagueTeams(String type, String name);
    public void displayTeamInLeague(String type, String name);
    public void displayPointTable(String type);
    public void addMatchDetails(Match match, String type);
    public void loadDataFromTXT() throws IOException, ClassNotFoundException;
    public void saveDataToTXT() throws IOException;
    public void playMatchGUI(int type, AnchorPane pane, AnchorPane paneForMatch, Label labelForMessage, Label labelForDate, Label labelForStadium, Label labelForTeamOne, Label labelForTeamOneGoal, Label labelForTeamTwo, Label labelForTeamTwoGoal, Label labelForSymbol);
    public void pointTableGUI(int clubType, String tableType, TableView<FootBallClub> view);
    public void matchDetailsGUI(AnchorPane pane);
    public void searchMatchDetailsGUI(LocalDate date, AnchorPane pane);

}
