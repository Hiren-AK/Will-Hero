package code;

import java.io.Serializable;

public class Score implements Serializable {
    private  int score;
    public Score(int _score){
        score = _score;
    }

    public void setScore(int _score){
        score = _score;
    }

    public int getScore(){
        return score;
    }
}
