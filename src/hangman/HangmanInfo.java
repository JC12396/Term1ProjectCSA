package hangman;

public class HangmanInfo {



    private int wins = 0;
    private int losses = 0;
    private int trys = 0;

    public void setWins(int winsNum)
    {
        this.wins = winsNum;
    }
    public int getWins()
    {
        return wins;
    }


    public void setLosses(int lossesNum)
    {
        this.losses = lossesNum;
    }
    public int getLosses()
    {
        return losses;
    }


    public void setTrys(int trysNum)
    {
        this.trys = trysNum;
    }
    public int getTrys()
    {
        return trys;
    }



}
