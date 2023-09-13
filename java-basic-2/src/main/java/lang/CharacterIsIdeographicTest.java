package lang;

public class CharacterIsIdeographicTest {
  public static boolean isCJK(String str) {
    return str.codePoints().allMatch(Character::isIdeographic);
  }
  
  public static void main(String[] args) {
    String str1 = "測試1";
    String str2 = "測試二";
    System.out.println(isCJK(str1));
    System.out.println(isCJK(str2));
  }
}
