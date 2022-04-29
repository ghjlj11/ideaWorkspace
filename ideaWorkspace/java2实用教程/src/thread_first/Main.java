package thread_first;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SpeakElephant speakElephant = new SpeakElephant();
        SpeakCar speakCar = new SpeakCar();
        speakElephant.start();
        speakCar.start();
        for(int i = 0; i < 20 ; i ++){
            System.out.print("我" + i + "  ");
        }
    }
}
