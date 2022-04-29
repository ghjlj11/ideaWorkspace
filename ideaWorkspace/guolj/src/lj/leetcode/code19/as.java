package lj.leetcode.code19;
import java.util.Scanner;
public class as {
    public static void main(String[] args){
        ListNode l=new ListNode(0);
        ListNode k=l;
        for(int i=0;i<5;i++){
            Scanner sc=new Scanner(System.in);
            k.next=new ListNode(sc.nextInt());
            k=k.next;
        }
        bs b=new bs();
        ListNode r=b.removeNthFromEnd(l,2);
        while(r!=null){
        System.out.println(r.val);
        r=r.next;
        }
    }
}
