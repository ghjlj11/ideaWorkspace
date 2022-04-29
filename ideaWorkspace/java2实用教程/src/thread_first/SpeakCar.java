package thread_first;

/**
 * @author 8
*/
public class SpeakCar extends Thread{
    @Override
    public void run(){
        for(int i = 0; i < 20 ; i ++){
            if( i == 0){
                try {
                    SpeakCar.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("汽车" + i + "  ");
        }
    }
}
