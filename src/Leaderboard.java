import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private List<Score> tableEasy;
    private List<Score> tableMedium;
    private List<Score> tableDifficult;
    private List<Score> tableInsane;

    final int EASY      = 1;
    final int MEDIUM    = 2;
    final int DIFFICULT = 3;
    final int INSANE    = 4;

    public int game;

    public Leaderboard(int game) {

        this.game = game;
        tableEasy = new ArrayList<>();
        tableMedium = new ArrayList<>();
        tableDifficult = new ArrayList<>();
        tableInsane = new ArrayList<>();
    }

    public void add(Score score) {

        switch(score.getLevel()) {

            case EASY:
                tableEasy.add(score);
                tableEasy.sort(Comparator.comparing(Score::getScore));
                break;
            case MEDIUM:
                tableMedium.add(score);
                tableMedium.sort(Comparator.comparing(Score::getScore));
                break;
            case DIFFICULT:
                tableDifficult.add(score);
                tableDifficult.sort( Comparator.comparing(Score::getScore) );
                break;
            case INSANE:
                tableInsane.add(score);
                tableInsane.sort( Comparator.comparing(Score::getScore) );
                break;
        }
    }

    public void remove(Score score) {

    }

    public void load() {

        List<List<String>> tables = CSV.loadAsStrings("src/scores.csv");
        List<Score> scores = new ArrayList<>();


        clear();    // clear leaderboard

        // read in each list and convert to score then add score
        // This will add to the appropriate table Easy/Medium or Difficult

        for(List<String> scoreAsList : tables) {
            Score score = new Score(scoreAsList);
            if (score.getGame() == game)
                add(score);
        }

    }

    private void clear() {
        tableEasy = new ArrayList<>();
        tableMedium = new ArrayList<>();
        tableDifficult = new ArrayList<>();
    }

    public void save() {
        List<String> tables = new ArrayList<>();

        tables = this.stringify();

        CSV.save(tables, "src/scores.csv");

    }

    public List<String> stringify() {
        List<String> tables = new ArrayList<>();

        for(Score score : tableEasy) {
            tables.add(score.toString());
        }
        for(Score score : tableMedium) {
            tables.add(score.toString());
        }
        for(Score score : tableDifficult) {
            tables.add(score.toString());
        }
        return tables;

    }

    public List<Score> topFive(int game, int level) {

        List<Score> topFiveScores = new ArrayList<>();

        switch(level) {

            case EASY:
                for(int i = 0 ; i < 5 ; i++)   if (i < tableEasy.size())      topFiveScores.add(tableEasy.get(i));
                break;
            case MEDIUM:
                for(int i = 0 ; i < 5 ; i++)   if (i < tableMedium.size())    topFiveScores.add(tableMedium.get(i));
                break;
            case DIFFICULT:
                for(int i = 0 ; i < 5 ; i++)   if (i < tableDifficult.size()) topFiveScores.add(tableDifficult.get(i));
                break;
            case INSANE:
                for(int i = 0 ; i < 5 ; i++)   if (i < tableInsane.size())    topFiveScores.add(tableInsane.get(i));
                break;
        }

        return topFiveScores;
    }

}
