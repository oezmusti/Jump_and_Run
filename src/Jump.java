public class Jump extends Thread{
    static boolean finished = true;
    static boolean highstPoint = false;
    public int jumpHigh = 85;
    static int basepointY = 235;
    static int jumpPosition = basepointY;
    public int jumpTime;

    //Nur für die Bekanthabe wichti
    public Jump(){

    }

    public void run(){
        finished = false;
        jumpTime = 2;
        while(finished == false){
            Jump();

            try {
                Thread.sleep(jumpTime);
            }catch (Exception e){

            }
        }

        highstPoint = false;

    }

    public void Jump(){
        if(highstPoint == false){
            jumpPosition--;
        }
        if(jumpPosition == (basepointY - jumpHigh)){
            highstPoint = true;
        }
        if(highstPoint == true && jumpPosition <= basepointY){
            jumpPosition++;
            if(jumpPosition == basepointY){
                finished = true;
            }
        }
    }
}
