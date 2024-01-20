public class Jump extends Thread{
    static boolean finished = true;
    static boolean highstPoint = false;
    public int jumpHigh = 180;
    static int basepointY = 349;
    static int jumpPosition = basepointY;
    public int jumpTime;

    public void run(){
        finished = false;
        jumpTime = 1;
        while(finished == false){
            doJump();
            try {
                Thread.sleep(jumpTime);
            }catch (Exception e){

            }
        }
        highstPoint = false;
    }

    public void doJump(){
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
