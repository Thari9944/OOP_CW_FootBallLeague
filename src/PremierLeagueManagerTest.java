import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PremierLeagueManagerTest {

    @org.junit.jupiter.api.Test
    void addClubToLeague() {
        FootBallClub club = new FootBallClub("LiverPool", "Liverpool", "Morgan", "Anfeild", 4, 3);
        assertEquals("LiverPool", club.getClubName());
        assertEquals("Liverpool", club.getClubLocation());
        assertEquals("Morgan", club.getManagerName());
        assertEquals("Anfeild", club.getStadium());
        assertEquals(4, club.getNumOfClubWins());
        assertEquals(3, club.getNumOfAwards());
    }

    @Test
    void addMatchDetails() {
        FootBallClub club = new FootBallClub("LiverPool", "Liverpool", "Morgan", "Anfeild", 4, 3);
        club.setNumOfMatchesPlayed(1);
        club.setNumOfWins(2);
        club.setNumOfDefeats(3);
        club.setNumOfDraws(4);
        club.setPoints(5);
        club.setNumOfGoalsScored(5);
        club.setNumOfGoalsReceived(2);
        club.setGoalDifference();
        assertEquals(1, club.getNumOfMatchesPlayed());
        assertEquals(2, club.getNumOfWins());
        assertEquals(3, club.getNumOfDefeats());
        assertEquals(4, club.getNumOfDraws());
        assertEquals(5, club.getPoints());
        assertEquals(5, club.getNumOfGoalsScored());
        assertEquals(2, club.getNumOfGoalsReceived());
        assertEquals(3, club.getGoalDifference());
    }

    @Test
    void testAddMatchDetails() {
        Match match = new Match(LocalDate.parse("2018-11-11"),"Liverpool", 4 , "chelsea", 2, "hotspur", "FootBallClub");
        assertEquals(LocalDate.parse("2018-11-11"), match.getDateOfMatch());
        assertEquals("Liverpool", match.getTeamOne());
        assertEquals(4, match.getTeamOneGoals());
        assertEquals("chelsea", match.getTeamTwo());
        assertEquals(2, match.getTeamTwoGoals());
        assertEquals("hotspur", match.getStadium());
        assertEquals("FootBallClub", match.getClubType());
    }
}