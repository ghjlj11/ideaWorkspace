package lj.leetcode.code61;

public class Main {
    public static void main(String[] args){}
}
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode tail=head;
        int length=0;
        while (tail!=null){
            length++;
            tail=tail.next;
        }
        if(length<=1){
            return head;
        }
        k=k%length;
        return rotate(head,k,length,0);
    }
    private ListNode rotate(ListNode head,int k,int length,int times){
        if(times==k){
            return head;
        }
        ListNode tail=new ListNode(0,head),temp=tail.next;
        for(int i=0;i<length-2;i++){
            temp=temp.next;
        }
        ListNode res=temp.next;
        temp.next=null;
        res.next=tail.next;
        res=rotate(res,k,length,times+1);
        return res;
    }
    public ListNode rotates(ListNode head,int k){
        ListNode tail=head;
        ListNode res=new ListNode(0,head);
        ListNode tail2=res;
        int length=0;
        while (tail!=null){
            length++;
            tail=tail.next;
        }
        if(length<=1||k%length==0){
            return head;
        }
        k=k%length;
        int times=length-k;
        for(int i=0;i<times;i++){
            head=head.next;
            tail2=tail2.next;
        }
        tail2.next=null;
        ListNode j=new ListNode(0,head),x=j;
        while (x.next!=null){
            x=x.next;
        }
        x.next=res.next;
        return j.next;
    }
    public ListNode re(ListNode head,int k){
        ListNode tail=head,res=head;
        int length=0;
        while (tail!=null){
            length++;
            tail=tail.next;
        }
        if(length<=1||k%length==0){
            return head;
        }
        k=k%length;
        tail=head;
        int times=length-k;
        for(int i=0;i<times-1;i++){
            tail=tail.next;
        }
        res=tail.next;
        tail.next=null;
        ListNode tail2=res;
        for(int i=0;i<k-1;i++){
            tail2=tail2.next;
        }
        tail2.next=head;
        return res;
    }
}