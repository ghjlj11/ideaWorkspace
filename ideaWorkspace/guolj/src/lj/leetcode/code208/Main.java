package lj.leetcode.code208;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) {
        Trie2 t = new Trie2();
        t.insert("apple");
        System.out.println(t.search("apple"));
        System.out.println(t.startsWith("app"));
        System.out.println(t.search("app"));
        t.insert("app");
        System.out.println(t.search("app"));
    }
}
