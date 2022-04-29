package lj.leetcode.code2;
public class Solution {
   public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       ListNode h=null,t=null;
       int a=0,sum=0,b,c;
       while(l1!=null||l2!=null){
           b=l1!=null?l1.val:0;
           c=l2!=null?l2.val:0;
           sum=b+c+a;
           if(h==null)
               h=t=new ListNode(sum%10);
           else{
               t.next=new ListNode(sum%10);
               t=t.next;
           }
           a=sum/10;
           if(l1!=null){
               l1=l1.next;
           }
           if(l2!=null){
               l2=l2.next;
           }
       }
       if(a>0){
           t.next=new ListNode(a);
       }
       return h;
    }

    public ListNode sums(ListNode l1, ListNode l2) {
       ListNode res=new ListNode(),tail=res;
       int temp=0,num1=0,num2=0,sum=0;
       while(l1!=null||l2!=null){
           num1=l1==null?0:l1.val;
           num2=l2==null?0:l2.val;
           sum=temp+num1+num2;
           tail.next=new ListNode(sum%10);
           temp=sum/10;
           if(l1!=null){
               l1=l1.next;
           }
           if(l2!=null){
               l2=l2.next;
           }
           tail=tail.next;
       }
       if(temp!=0){
           tail.next=new ListNode(temp);
       }
       return tail.next;
    }
}
