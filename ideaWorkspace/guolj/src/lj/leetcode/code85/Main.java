package lj.leetcode.code85;

public class Main {
	public static void main(String[] args) {
		char[][] k = {{0,0}};
		char[][] c = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}; 
		Solution a = new Solution();
		System.out.println(a.maximalRectangle(c));
	}
}
