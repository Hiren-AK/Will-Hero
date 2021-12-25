package code;

import java.io.Serializable;

public class HighScore implements Serializable {
    private int highScore;

    public HighScore(){
        this.highScore = 0;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
