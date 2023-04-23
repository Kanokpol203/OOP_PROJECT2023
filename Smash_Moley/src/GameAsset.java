public class GameAsset {
    private int score = 0;
    private String difficulty;
    
    public void changeScore(int score){
        this.score += score;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public String getDifficulty() {
        return difficulty;
    }
    
}
