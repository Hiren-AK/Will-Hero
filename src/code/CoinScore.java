package code;

import java.io.Serializable;

public class CoinScore implements Serializable {
    private  int score;
    public CoinScore(int _score){
        score = _score;
    }

    public void setCoinScore(int _score){
        score = _score;
    }

    public int getCoinScore(){
        return score;
    }
}