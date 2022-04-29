package lj.random.IInterface;

public class Interface {
    public static void main(String[] args){
        China zhang;
        Japan hehe;
        zhang=new China();
        hehe=new Japan();
        zhang.number=32+ Computable.Max;
        hehe.number=14+Computable.Max;
        System.out.println("zhang:"+zhang.number+"sum:"+zhang.f(zhang.number)+"   "+79*39);
        System.out.println("hehe:"+hehe.number+"sum:"+hehe.f(hehe.number)+"     "+"60+46");
    }
}
