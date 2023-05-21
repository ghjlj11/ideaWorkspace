package lj.offer.offer58;


/**
 * @author 86187
 */
public class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}
