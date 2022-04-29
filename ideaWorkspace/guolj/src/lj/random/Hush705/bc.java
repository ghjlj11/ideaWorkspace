package lj.random.Hush705;


import java.util.Iterator;
import java.util.LinkedList;

public class bc {
    private static final int base=857;
    private LinkedList[] date;
    public void MyHushSet(){
        date=new LinkedList[base];
        for(int i=0;i<base;i++){
            date[i]=new LinkedList<Integer>();
        }
    }
    public void add(int key){
        int h=hush(key);
        Iterator<Integer> it=date[h].iterator();
        while (it.hasNext()){
            Integer ele=it.next();
            if(ele==key){
                return;
            }
        }
        date[h].offerLast(key);
    }
    public void remove(int key){
        int h=hush(key);
        Iterator<Integer> it=date[h].iterator();
        while(it.hasNext()){
            Integer ele=it.next();
            if(ele==key){
                date[h].remove(ele);
                return;
            }
        }
    }
    public boolean contains(int key){
        int h=hush(key);
        Iterator<Integer> it=date[h].iterator();
        while(it.hasNext()){
            Integer ele=it.next();
            if(ele==key)
                return true;
        }
        return false;
    }
    public void put(){
        Iterator<Integer> tr=date[0].iterator();
        int i=0;
        while (i<base){
            while(tr.hasNext()){
                System.out.println(tr.next());
            }
            tr=date[i++].iterator();
        }
    }
    private int hush(int key){
        return key%base;
    }
}
