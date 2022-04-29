package lj.LanQ.YangHui;

class e{
    public static void main(String[] args){
        shuzu A=new shuzu();
        System.out.println(A.sd(20210301));
    }
}
class shuzu {
    public long sd(int N) {
        if(N==1){
            return 1;
        }
        long ans=3;
        long a[][]=new long[10000][10000];
        a[1][1]=1;
        a[1][0]=1;
        for(int i=2;i<5000;i++) {
            for (int j = 0; j <=i; j++) {
                ans++;
                if(j==0||j==i){
                    a[i][j]=1;
                }
                else {
                    a[i][j] = a[i - 1][j] + a[i - 1][j - 1];
                    if (a[i][j] == N){
                        return ans;
                    }
                }
            }
        }
        return ans;
    }
}
