package singoton;

/**
 * @author 86187
 */
public class Test {
    private volatile Test test;

    public Test getTest(){
        if(this.test == null){
            synchronized (Test.class){
                if(this.test == null){
                    this.test = new Test();
                }
            }
        }
        return this.test;
    }
    private Test(){}
}
