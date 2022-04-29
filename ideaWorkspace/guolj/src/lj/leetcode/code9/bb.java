package lj.leetcode.code9;

public class bb {
    public boolean isPalindrome(int x) {
        if (x >= 0) {
            String str = String.valueOf(x);
            /*int[] tmp = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                tmp[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
            }*/
            for (int m = 0, n = str.length() - 1; m < str.length() / 2; m++, n--) {
                if (str.charAt(m) != str.charAt(n))
                    return false;
            }
            return true;
        }
        return false;
    }
}
