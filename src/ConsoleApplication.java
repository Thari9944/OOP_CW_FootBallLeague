import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleApplication extends Application {
    private static PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
    final static Scanner sc = new Scanner(System.in);
    private static Stage mainStage = new Stage();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch();
    }

    public static void addClubToLeague() {
        System.out.println("-- Please enter following details to add team to Premier League --");
        String userCommand = "y";
        while (userCommand.equals("y")) {
            String clubType = clubTypeFromUser();
            String clubName = validateString("Club Name: ");
            //loop check the club name is unique. if it unique program goes to next steps. if it not unique ask for another name.
            if (premierLeagueManager.isUsedName(clubType, clubName)) {
                System.out.println("\tClub name is already taken.");
                continue;
            }
            String clubLocation = validateString("Club Location: ");
            String mangerName = validateString("Club Manger Name: ");
            String stadiumName = validateString("Club Stadium: ");
            int clubWins = validateInt("Number Of League Wins: ");
            int clubAwards = validateInt("Number of Awards: ");
            //create objects according to the football club type. then call the premier league add method.
            if (clubType.equals("c")) {
                FootBallClub fbc = new FootBallClub(clubName, clubLocation, mangerName, stadiumName, clubWins, clubAwards);
                premierLeagueManager.addClubToLeague(fbc);
            } else if (clubType.equals("u")) {
                String uniName = validateString("University Name: ");
                String uniLocation = validateString("University Location: ");
                UniversityFootBallClub ufbc = new UniversityFootBallClub(clubName, clubLocation, mangerName, stadiumName, clubWins, clubAwards, uniName, uniLocation);
                premierLeagueManager.addClubToLeague(ufbc);
            } else {
                String schoolName = validateString("School Name: ");
                String schoolLocation = validateString("School Location: ");
                SchoolFootBallClub sfbc = new SchoolFootBallClub(clubName, clubLocation, mangerName, stadiumName, clubWins, clubAwards, schoolName, schoolLocation);
                premierLeagueManager.addClubToLeague(sfbc);
            }
            System.out.println("\nAre you want to add another team ? (y\\n)");
            //sc.nextLine();
            userCommand = sc.nextLine();
        }
    }

    public static void deleteLeagueTeams() {
        System.out.println("-- Delete Team from Premier League --");
        //get data from user and pass them. type and club name which user want to delete.
        premierLeagueManager.deleteLeagueTeams(clubTypeFromUser(), validateString("Club Name: "));
    }

    public static void displayTeamInLeague() {
        System.out.println("-- Display Team in Premier League --");
        //get the club type and club name user want to see
        premierLeagueManager.displayTeamInLeague(clubTypeFromUser(), validateString("Club Name: "));
    }

    public static void displayPointTable() {
        System.out.println("-- Display Point Table in Premier League --");
        //get the type of football clubs and show the correct point table
        premierLeagueManager.displayPointTable(clubTypeFromUser());
    }

    public static void addMatchDetails() {
        System.out.println("-- Add Played Match Details --");
        String addMatch = "y";
        while (addMatch.equals("y")) {
            System.out.println("Match Date: (yyyy-mm-dd)");
            LocalDate matchDate;
            try {
                matchDate = LocalDate.parse(sc.nextLine());
            } catch (Exception e) {
                System.out.println("\tError - Invalid input.");
                continue;
            }
            String clubType = clubTypeFromUser();
            String teamOne = validateString("Team One: ");
            if (premierLeagueManager.isUsedName(clubType, teamOne)) {
                int teamOneGoals = validateInt("Team One Goals: ");
                String teamTwo = validateString("Team Two: ");
                if (premierLeagueManager.isUsedName(clubType, teamTwo)) {
                    int teamTwoGoals = validateInt("Team Two Goals: ");
                    String stadium = validateString("Stadium: ");
                    String type;
                    if (clubType.equals("c")){
                        type = "Foot Ball Clubs";
                    }else if (clubType.equals("u")){
                        type = "University Foot Ball Clubs";
                    }else {
                        type = "School Foot Ball Clubs";
                    }
                    Match match = new Match(matchDate, teamOne, teamOneGoals, teamTwo, teamTwoGoals, stadium, type);
                    premierLeagueManager.addMatchDetails(match, clubType);
                    System.out.println("\nAre you want to add another match ? (y\\n)");
                    addMatch = sc.nextLine().toLowerCase();

                } else {
                    System.out.println(teamTwo + " is not in league.");
                }
            } else {
                System.out.println(teamOne + " is not in league.");
            }
        }
    }

    //to validate string input and return string
    public static String validateString(String displayMessage) {
        while (true) {
            System.out.print(displayMessage);
            String userInput = sc.nextLine();
            if (userInput.matches("[A-Za-z\\s]*")) {
                String[] strArray = userInput.split("\\s"); //split the input and store in string array
                userInput = "";
                //this loop do the each word first letter makes uppercase
                for (int i = 0; i < strArray.length; i++) {
                    if (i == (strArray.length - 1)) {
                        userInput += strArray[i].substring(0, 1).toUpperCase() + strArray[i].substring(1).toLowerCase();
                    } else {
                        userInput += strArray[i].substring(0, 1).toUpperCase() + strArray[i].substring(1).toLowerCase() + " ";
                    }
                }
                return userInput;
            } else {
                System.out.println("\tError - Invalid input.");
            }
        }
    }

    //to validate integer input and return integer
    public static int validateInt(String displayMessage) {
        while (true) {
            try {
                System.out.print(displayMessage);
                int userInput = Integer.parseInt(sc.nextLine());
                return userInput;
            } catch (Exception e) {
                System.out.println("\tError - Integer required.");
            }
        }
    }

    //get the type from user and convert it to lower case
    public static String clubTypeFromUser() {
        System.out.println("\nPlease select the type of your Foot Ball Club" +
                "\n\tC for Club Foot Ball Club" +
                "\n\tU for University Foot Ball Club" +
                "\n\tS for School Foot Ball Club");
        while (true) {
            System.out.print("Response - ");
            String userResponse = sc.nextLine();
            switch (userResponse) {
                case "C":
                case "c":
                case "U":
                case "u":
                case "S":
                case "s":
                    return userResponse.toLowerCase();
                default:
                    System.out.println("\tError - Check your input.");
                    break;
            }
        }
    }

    //cli menu in program
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("\t\t\t\t\t ------------------------------------------------");
        System.out.println("\t\t\t\t\t === Welcome to the Foot Ball Premier League ===");
        System.out.println("\t\t\t\t\t ------------------------------------------------");
        premierLeagueManager.loadDataFromTXT();
        boolean wantExit = false;
        while (!wantExit) {
            System.out.printf("\n                             ***-------MAIN MENU-------***                 " +
                    "\n 1 for Add Team To Premier League          2 for Delete Team From Premier League" +
                    "\n 3 for Display Team In Premier League      4 for for Display Premier League Point Table" +
                    "\n 5 for Add Played Match Details            6 for GUI " +
                    "\n 7 for Exit" +
                    "\n Choice - ");
            String user = sc.nextLine();
            switch (user) {
                case "1":
                    addClubToLeague();
                    break;
                case "2":
                    deleteLeagueTeams();
                    break;
                case "3":
                    displayTeamInLeague();
                    break;
                case "4":
                    displayPointTable();
                    break;
                case "5":
                    addMatchDetails();
                    break;
                case "6":
                    show();
                    break;
                case "7":
                    wantExit = true;
                    break;
                default:
                    System.out.println("\tError - Invalid input.");
            }
        }
        premierLeagueManager.saveDataToTXT();
        System.out.println("-- Thank You ! --");
        System.exit(0);
    }

    //show the stage and in each method change the scene
    public static void show(){
        guiHome();
        mainStage.showAndWait();
    }

    public static void guiHome(){
        mainStage.setResizable(false);
        AnchorPane body = new AnchorPane();
        DropShadow shadow = new DropShadow();
        Stage homeStage = new Stage();
        homeStage.setTitle("Home");

        ImageView logo = new ImageView(new Image("images/logo.png",100,100, true, true));
        logo.setId("logo");
        Label labelFotTopic = new Label("Foot Ball Premier League");
        labelFotTopic.setId("topic");
        labelFotTopic.setLayoutX(150);
        labelFotTopic.setLayoutY(5);

        ImageView player = new ImageView(new Image("images/player.png",350,500, true, true));
        player.setLayoutY(180);

        Button playButton = new Button("\n\n\n\n\n\n\nPlay Match");
        playButton.setId("playMatchBtn");
        playButton.setLayoutX(350);
        playButton.setLayoutY(130);
        playButton.setOnAction(e -> {
            //homeStage.close();
            playMatchGUI();
                });
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> playButton.setEffect(shadow));
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> playButton.setEffect(null));

        Button showTableButton = new Button("\n\n\n\n\n\nShow League Table");
        showTableButton.setId("showTableBtn");
        showTableButton.setLayoutX(505);
        showTableButton.setLayoutY(130);
        showTableButton.setOnAction(e -> pointTableGUI());
        showTableButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> showTableButton.setEffect(shadow));
        showTableButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> showTableButton.setEffect(null));

        Button showMatchButton = new Button("\n\n\n\n\n\nShow All Matches");
        showMatchButton.setId("showMatchBtn");
        showMatchButton.setLayoutX(505);
        showMatchButton.setLayoutY(260);
        showMatchButton.setOnAction(e -> matchDetailsGUI());
        showMatchButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> showMatchButton.setEffect(shadow));
        showMatchButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> showMatchButton.setEffect(null));

        Button searchMatchButton = new Button("\n\nSearch Match by Date");
        searchMatchButton.setId("searchTableBtn");
        searchMatchButton.setLayoutX(350);
        searchMatchButton.setLayoutY(390);
        searchMatchButton.setOnAction(e -> searchMatchDetailsGUI());
        searchMatchButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> searchMatchButton.setEffect(shadow));
        searchMatchButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> searchMatchButton.setEffect(null));


        body.setId("main");
        body.getChildren().addAll(logo, player, labelFotTopic, playButton, showTableButton, showMatchButton, searchMatchButton);
        body.getStylesheets().add("HomeStyle.css");

        Scene scene = new Scene(body, 700, 500);
        mainStage.setScene(scene);
        //mainStage.showAndWait();

    }

    public static void playMatchGUI(){
        Stage stageForPlayMatch = new Stage();
        stageForPlayMatch.setTitle("Play A Match");
        AnchorPane pane = new AnchorPane();
        DropShadow shadow = new DropShadow();

        Label labelForTopic = new Label("Play Match");
        labelForTopic.setId("topic");
        labelForTopic.setLayoutX(175);
        labelForTopic.setLayoutY(5);

        Label labelForTypes = new Label("Select the Club Type : ");
        labelForTypes.setLayoutY(70);
        labelForTypes.setId("type");

        ChoiceBox<String> choiceBoxForTypes = new ChoiceBox<String>();
        choiceBoxForTypes.getItems().addAll("Foot Ball Clubs", "University Foot Ball Clubs" , "School Foot Ball Clubs");
        choiceBoxForTypes.setValue("Foot Ball Clubs");
        choiceBoxForTypes.setId("choiceType");
        choiceBoxForTypes.setLayoutX(20);
        choiceBoxForTypes.setLayoutY(110);

        Button buttonForPlay = new Button("Play");
        buttonForPlay.setId("playButton");
        buttonForPlay.setLayoutX(100);
        buttonForPlay.setLayoutY(155);

        AnchorPane paneForMatch = new AnchorPane();
        Label labelForMessage = new Label();
        labelForMessage.setId("labelMT");
        labelForMessage.setLayoutX(50);
        labelForMessage.setLayoutY(60);
        Label labelForDate = new Label();
        labelForDate.setId("labelMT");
        labelForDate.setLayoutX(200);
        labelForDate.setLayoutY(10);
        Label labelForStadium = new Label();
        labelForStadium.setId("labelMT");
        labelForStadium.setLayoutX(210);
        labelForStadium.setLayoutY(35);
        Label labelForTeamOne = new Label();
        labelForTeamOne.setId("labelMT");
        labelForTeamOne.setLayoutX(10);
        labelForTeamOne.setLayoutY(80);
        Label labelForTeamOneGoal = new Label();
        labelForTeamOneGoal.setId("labelMT");
        labelForTeamOneGoal.setLayoutX(25);
        labelForTeamOneGoal.setLayoutY(100);
        Label labelForTeamTwo = new Label();
        labelForTeamTwo.setId("labelMT");
        labelForTeamTwo.setLayoutX(275);
        labelForTeamTwo.setLayoutY(80);
        Label labelForTeamTwoGoal = new Label();
        labelForTeamTwoGoal.setId("labelMT");
        labelForTeamTwoGoal.setLayoutX(290);
        labelForTeamTwoGoal.setLayoutY(100);
        Label labelForSymbol = new Label();
        labelForSymbol.setId("labelMT");
        labelForSymbol.setLayoutX(245);
        labelForSymbol.setLayoutY(80);
        paneForMatch.setId("matchDetails");
        paneForMatch.setLayoutX(50);
        paneForMatch.setLayoutY(225);
        paneForMatch.getChildren().addAll(labelForMessage, labelForDate, labelForStadium, labelForTeamOne, labelForTeamTwo, labelForTeamOneGoal, labelForTeamTwoGoal, labelForSymbol);
        pane.getChildren().add(paneForMatch);
        paneForMatch.setVisible(false);

        buttonForPlay.setOnAction(e ->{
            premierLeagueManager.playMatchGUI(choiceBoxForTypes.getSelectionModel().getSelectedIndex(), pane, paneForMatch,labelForMessage, labelForDate, labelForStadium,labelForTeamOne,labelForTeamOneGoal,labelForTeamTwo,labelForTeamTwoGoal,labelForSymbol);
            paneForMatch.setVisible(true);
        });
        buttonForPlay.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> buttonForPlay.setEffect(shadow));
        buttonForPlay.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> buttonForPlay.setEffect(null));

//        mainStage.setOnCloseRequest( e -> guiHome());
        Button buttonForHome = new Button("Home");
        buttonForHome.setLayoutX(680);
        buttonForHome.setLayoutY(430);
        buttonForHome.setId("homeButton");
        buttonForHome.setOnAction(event -> guiHome());
        buttonForHome.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> buttonForHome.setEffect(shadow));
        buttonForHome.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> buttonForHome.setEffect(null));
        pane.getChildren().addAll(labelForTopic, labelForTypes, choiceBoxForTypes,buttonForPlay,buttonForHome);
        pane.setId("body");
        pane.getStylesheets().add("styles.css");
        Scene scene = new Scene(pane, 800,500);
        mainStage.setScene(scene);
        //mainStage.showAndWait();
    }

    public static void pointTableGUI(){
        DropShadow shadow = new DropShadow();
        Label labelForTopic = new Label("Premier League Point Table");
        labelForTopic.setId("topic");
        labelForTopic.setLayoutX(100);
        labelForTopic.setLayoutY(5);

        Label labelForTypes = new Label("Select the Club Type : ");
        labelForTypes.setLayoutY(65);
        labelForTypes.setId("type");

        ChoiceBox<String> choiceBoxForTypes = new ChoiceBox<String>();
        choiceBoxForTypes.getItems().addAll("Foot Ball Clubs", "University Foot Ball Clubs" , "School Foot Ball Clubs");
        choiceBoxForTypes.setValue("Foot Ball Clubs");
        choiceBoxForTypes.setId("choiceType");
        choiceBoxForTypes.setLayoutX(10);
        choiceBoxForTypes.setLayoutY(100);

        ToggleGroup toggleButtonForTables = new ToggleGroup();
        ToggleButton tButtonForPoints = new ToggleButton("Point Table");
        tButtonForPoints.setToggleGroup(toggleButtonForTables);
        tButtonForPoints.setSelected(true);
        tButtonForPoints.setLayoutX(5);
        tButtonForPoints.setLayoutY(150);
        tButtonForPoints.setId("toggleButtons");
        ToggleButton tButtonForGoals = new ToggleButton("Highest Goal Table");
        tButtonForGoals.setToggleGroup(toggleButtonForTables);
        tButtonForGoals.setLayoutX(205);
        tButtonForGoals.setLayoutY(150);
        tButtonForGoals.setId("toggleButtons");
        ToggleButton tButtonForWins = new ToggleButton("Largest Wins Table");
        tButtonForWins.setToggleGroup(toggleButtonForTables);
        tButtonForWins.setLayoutX(405);
        tButtonForWins.setLayoutY(150);
        tButtonForWins.setId("toggleButtons");


        TableView<FootBallClub> table = new TableView<FootBallClub>();
        table.setId("table");
        //TableColumn<FootBallClub, String> colForPosition = new TableColumn<>("Pos");
        TableColumn<FootBallClub, String> colForClub = new TableColumn<>("Club Name");
        colForClub.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        colForClub.setMaxWidth(180);
        colForClub.setMinWidth(180);
        TableColumn<FootBallClub, String> colForMatches = new TableColumn<>("Matches");
        colForMatches.setCellValueFactory(new PropertyValueFactory<>("numOfMatchesPlayed"));
        TableColumn<FootBallClub, String> colForWins = new TableColumn<>("Won");
        colForWins.setCellValueFactory(new PropertyValueFactory<>("numOfWins"));
        TableColumn<FootBallClub, String> colForDraws = new TableColumn<>("Drawn");
        colForDraws.setCellValueFactory(new PropertyValueFactory<>("numOfDraws"));
        TableColumn<FootBallClub, String> colForLost = new TableColumn<>("Lost");
        colForLost.setCellValueFactory(new PropertyValueFactory<>("numOfDefeats"));
        TableColumn<FootBallClub, String> colForScored = new TableColumn<>("GS");
        colForScored.setCellValueFactory(new PropertyValueFactory<>("numOfGoalsScored"));
        TableColumn<FootBallClub, String> colForReceived = new TableColumn<>("GR");
        colForReceived.setCellValueFactory(new PropertyValueFactory<>("numOfGoalsReceived"));
        TableColumn<FootBallClub, String> colForDifference = new TableColumn<>("GD");
        colForDifference.setCellValueFactory(new PropertyValueFactory<>("goalDifference"));
        TableColumn<FootBallClub, String> colForPoints = new TableColumn<>("Pts");
        colForPoints.setStyle("-fx-font-weight: bold");
        colForPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
        TableColumn<FootBallClub, String> colForPercentage = new TableColumn<>("Win Pr %");
        colForPercentage.setCellValueFactory(new PropertyValueFactory<>("winningPercentage"));
        table.getColumns().addAll(colForClub, colForMatches, colForWins, colForDraws, colForLost, colForScored, colForReceived, colForDifference, colForPoints, colForPercentage);
        table.setEditable(false);
        table.setLayoutX(50);
        table.setLayoutY(200);

        premierLeagueManager.pointTableGUI(0, "Point Table", table);

        choiceBoxForTypes.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String tableType;
            try {
                ToggleButton button = (ToggleButton) toggleButtonForTables.getSelectedToggle();
                tableType = button.getText();
            }catch (Exception e){
                tableType= "Point Table";
            }
            premierLeagueManager.pointTableGUI((int) newValue, tableType, table);
        });

        toggleButtonForTables.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            String tableType;
            try {
                ToggleButton button = (ToggleButton) newValue;
                tableType = button.getText();
            }catch (Exception e){
                tableType= "Point Table";
            }
            int clubType = (int) choiceBoxForTypes.getSelectionModel().getSelectedIndex();
            premierLeagueManager.pointTableGUI(clubType, tableType, table);
        });

        Button buttonForHome = new Button("Home");
        buttonForHome.setOnAction(event -> guiHome());
        buttonForHome.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> buttonForHome.setEffect(shadow));
        buttonForHome.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> buttonForHome.setEffect(null));
        buttonForHome.setLayoutX(680);
        buttonForHome.setLayoutY(430);
        buttonForHome.setId("homeButton");

        AnchorPane body = new AnchorPane();
        body.getStylesheets().add("styles.css");
        body.setId("body");
        body.getChildren().addAll(labelForTopic, labelForTypes, choiceBoxForTypes, tButtonForPoints, tButtonForGoals, tButtonForWins, table, buttonForHome);
        Scene scene = new Scene(body,800,500);
        mainStage.setScene(scene);
        //stageForTables.show();

    }

    public static void matchDetailsGUI(){
        AnchorPane mainBody = new AnchorPane();
        DropShadow shadow = new DropShadow();

        Label labelForTopic = new Label("All Matches played in league.");
        labelForTopic.setId("topic");
        labelForTopic.setLayoutX(100);
        labelForTopic.setLayoutY(5);

        ScrollPane scrollPaneForMatches = new ScrollPane();
        AnchorPane anchorPaneInScroll = new AnchorPane();
        premierLeagueManager.matchDetailsGUI(anchorPaneInScroll);
        scrollPaneForMatches.setId("scrollMatch");
        scrollPaneForMatches.setLayoutX(50);
        scrollPaneForMatches.setLayoutY(100);
        scrollPaneForMatches.setContent(anchorPaneInScroll);
        Button buttonForHome = new Button("Home");
        buttonForHome.setLayoutX(680);
        buttonForHome.setLayoutY(430);
        buttonForHome.setId("homeButton");
        buttonForHome.setOnAction(event -> guiHome());
        buttonForHome.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> buttonForHome.setEffect(shadow));
        buttonForHome.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> buttonForHome.setEffect(null));
        mainBody.setId("body");
        mainBody.getStylesheets().add("styles.css");
        mainBody.getChildren().addAll(labelForTopic, scrollPaneForMatches,buttonForHome);
        Scene scene = new Scene(mainBody, 800,500);
        mainStage.setScene(scene);
    }

    public static void searchMatchDetailsGUI(){
        AnchorPane mainBody = new AnchorPane();
        ScrollPane scrollPaneForMatches = new ScrollPane();

        DropShadow shadow = new DropShadow();

        Label labelForTopic = new Label("Search Match By Date");
        labelForTopic.setId("topic");
        labelForTopic.setLayoutX(200);
        labelForTopic.setLayoutY(5);

        TextField getDate = new TextField();
        getDate.setPromptText("yyyy-mm-dd");
        getDate.setLayoutX(50);
        getDate.setLayoutY(70);
        Button buttonForSearch = new Button("Search");
        buttonForSearch.setLayoutX(250);
        buttonForSearch.setLayoutY(70);
        buttonForSearch.setId("playButton");
        buttonForSearch.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> buttonForSearch.setEffect(shadow));
        buttonForSearch.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> buttonForSearch.setEffect(null));
        buttonForSearch.setOnAction(e ->{
            try {
                AnchorPane anchorPaneInScroll = new AnchorPane();
                LocalDate searchDate = LocalDate.parse(getDate.getText());
                premierLeagueManager.searchMatchDetailsGUI(searchDate, anchorPaneInScroll);
                scrollPaneForMatches.setContent(anchorPaneInScroll);

            }catch (Exception invalid){
                Stage stageForInvalidDate = new Stage();
                stageForInvalidDate.setTitle("Date Invalid");

                Label labelForTopic2 = new Label("Error");
                labelForTopic2.setStyle("-fx-text-fill: #ffffff");
                labelForTopic2.setLayoutX(130);
                labelForTopic2.setLayoutY(10);
                Label labelForMessage = new Label("Invalid Date type.\nType is yyyy-mm-dd. ");
                labelForMessage.setStyle("-fx-text-fill: #ffffff");
                labelForMessage.setLayoutX(5);
                labelForMessage.setLayoutY(50);
                Button buttonForClose = new Button("Close");
                buttonForClose.setLayoutX(200);
                buttonForClose.setLayoutY(100);
                buttonForClose.setOnAction(e2 -> stageForInvalidDate.close() );
                AnchorPane paneForInvalidDate = new AnchorPane();
                paneForInvalidDate.setId("invalidBody");
                paneForInvalidDate.getStylesheets().add("styles.css");
                paneForInvalidDate.getChildren().addAll(labelForTopic2, labelForMessage, buttonForClose);
                Scene sceneForInvalidDate = new Scene(paneForInvalidDate, 300, 150);
                stageForInvalidDate.setScene(sceneForInvalidDate);
                stageForInvalidDate.show();
            }
        } );

        Button buttonForHome = new Button("Home");
        buttonForHome.setLayoutX(680);
        buttonForHome.setLayoutY(430);
        buttonForHome.setId("homeButton");
        buttonForHome.setOnAction(event -> guiHome());
        buttonForHome.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> buttonForHome.setEffect(shadow));
        buttonForHome.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> buttonForHome.setEffect(null));
        mainBody.getStylesheets().add("styles.css");
        scrollPaneForMatches.setId("scrollMatch");
        scrollPaneForMatches.setLayoutX(50);
        scrollPaneForMatches.setLayoutY(110);
        mainBody.setId("body");
        mainBody.getChildren().addAll(getDate,buttonForSearch, buttonForHome, labelForTopic, scrollPaneForMatches);
        Scene scene = new Scene(mainBody, 800,500);
        mainStage.setScene(scene);

    }

}