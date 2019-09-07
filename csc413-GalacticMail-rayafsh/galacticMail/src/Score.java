public class Score {


private  int SCORES = 1000;
private boolean scoreIncreased = true;

    public void setSCORES(boolean isUp){
       this.scoreIncreased = isUp;
    }

    public int getSCORES() {
        return this.SCORES;
    }

    public void scoreDown(){
        this.SCORES -= 1;
        if(this.SCORES < 0){
            this.SCORES = 0;
        }
    }

    public void scoreUp(){
      if(scoreIncreased){
         this.SCORES += 1;
      }
      this.scoreIncreased = false;
    }


    public String getSCORESstr() {
        return String.valueOf(this.SCORES);
    }


}
