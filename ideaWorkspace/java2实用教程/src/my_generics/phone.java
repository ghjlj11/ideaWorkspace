package my_generics;

public class phone <T> implements change<T>{
    private T t;
    public phone(T t){
        this.t = t;
    }
    public T getT(){
        return t;
    }
    @Override
    public void show() {
        System.out.println(t.getClass());
    }
}
