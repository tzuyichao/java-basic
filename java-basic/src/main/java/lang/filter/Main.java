package lang.filter;

public class Main {
    public static void main(String[] args) {
        BenchmarkMain benchmarkMain = new BenchmarkMain();
        System.out.println(benchmarkMain.replaceFilter.filter(benchmarkMain.str, benchmarkMain.mask));
        System.out.println(benchmarkMain.trieKeywordFilter.filter(benchmarkMain.str, benchmarkMain.mask));
    }
}
