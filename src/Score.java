import java.util.List;

public class Score {

    private String name;
    private int level;
    private int score;
    private Integer game;

    public Score() {
    }

    public Score(String name, int level, int score, Integer game) {
        this.name = name;
        this.level = level;
        this.score = score;
        this.game = game;
    }

    public Score(String name, String level, String score, Integer game) {
        setName( name );
        setLevel( Integer.parseInt(level) );
        setScore( Integer.parseInt(score) );
        setGame( game );
    }

    public Score(List<String> scoreAsList) {

        setName( scoreAsList.get(0) );
        setLevel( Integer.parseInt(scoreAsList.get(1)) );
        setScore( Integer.parseInt(scoreAsList.get(2)) );
        setGame( Integer.parseInt(scoreAsList.get(3)) );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String toString() {
        return ""+name+","+level+","+score;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }
}
