package thread_first;

/**
 * @author 86187
 */
public class SpeakElephant extends Thread{
    @Override
    public void run(){
        for(int i = 0; i < 20; i ++){
            if(i == 0){
                try {
                    SpeakElephant.sleep(500);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.print("大象" + i + "  ");
        }
    }
}
