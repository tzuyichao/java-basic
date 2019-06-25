package trie;

public class Main {
    public static void main(String[] args) {
        String[] data = new String[] {"三元組儲存", "三餐", "一日三餐", "圖查詢"};

        Trie trie = new Trie();
        for(String str: data) {
            trie.add(str);
        }

        System.out.println("Trie size: " + trie.size());
        System.out.println("Trie contains 'abc': " + trie.contains("abc"));
        System.out.println("True contains '三餐': " + trie.contains("三餐"));
        System.out.println("remove '三元組儲存'");
        trie.remove("三元組儲存");
        System.out.println("True contains '三元組儲存': " + trie.contains("三元組儲存"));
        System.out.println("Trie size: " + trie.size());
    }
}
