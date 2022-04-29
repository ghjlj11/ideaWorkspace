package lj.random;

public class Continue {
    public static void main(String[] args){
        int sum=0,i;
        for(int j=0;j<3;j++){
            if(j==1||j==0){continue;}
        for(i=0;i<=10;i++){
            if(i%2==0){
                continue;
            }
            sum+=i;
        }}
        System.out.println(sum);
        int j,k;
        for(j=2;j<=100;j++){
            for(k=2;k<=j/2;k++){
                if(j%k==0)
                    break;
            }
            if(k>j/2){
                System.out.println(j+" "+k+" "+j+":是素数");
            }
        }
        System.out.println(Math.pow(2,14));
        String f="asdfafag";
        System.out.println(f.lastIndexOf("fa"));
        int c=9;
        if(c++<=9){System.out.println("yes");}
        System.out.println(++c);
        System.out.println(c);
        int b=1234;
        System.out.println(b>>>1);
    }
}
