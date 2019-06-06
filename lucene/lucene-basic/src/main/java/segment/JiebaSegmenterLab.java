package segment;


import com.huaban.analysis.jieba.JiebaSegmenter;

public class JiebaSegmenterLab {
    public static void main(String[] args) {
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        String[] sentences =
                new String[] {"\"20170525 至橋椿討論EMS Dashboard進度： 參加人員: 台達 林界宏、呂卓翰、賴長風、張建平、劉慶星 (1)       待FMCS 上線後並提供對應資料給EMS後,  即可開始進行成形製成系統電力能耗Dashboard建置。 (趕2個月內上線) (2)       表處系統、一般設施等4大系統, 迴路釐清後, 約1個月可以完成Dashboard。 (3)       5/5 會先提供成形製程系統Dashboard Level 1~ Level 3 各一張版面上線。 (4)       目前能耗/ 產量的計算時間刻度, 雙方已經確認以 “日”為單位, 後續也會依此展開。 (5)       TagName名稱定義： 電力系統和成形已經定義完成, 待FMCS 更新。其他系統需橋椿提供英文代碼。 (6)       橋椿成形製程電力迴路已與橋椿釐清。 (7)       本週會後進度： Ø   提供SI 能源規劃資料及迴路計算資料。 Ø   4/25 提供Dashboard 需求版面給詰群找美工繪製。 (已提供)  \n" +
                        "Ø   安排5/4 內部討論 ( RD、SC、SI ) Ø   安排5/5 橋椿EMS 會議 及 SI說明發包SCOPE。\n" +
                        "\"\n", "上平車與下平車是個工廠設備。这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
                        "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
        for (String sentence : sentences) {
            System.out.println(jiebaSegmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
        }
    }
}
