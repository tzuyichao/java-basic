package segment;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class MaximumMatchingSegmentationTest {

    @Test
    public void test_stopWords() {
        ForwardMaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        for(String stopWord : segmentation.getStopWords()) {
            System.out.println(stopWord);
        }
    }

    @Test
    public void test_dump_dictionary() {
        ForwardMaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        segmentation.getDictionary().forEach((term, detail) -> {
            System.out.println(detail);
        });
        System.out.println(segmentation.getDictionary().keySet().stream().max(Comparator.comparingInt(String::length)).get().length());
    }

    @Test
    public void test_process() {
        ForwardMaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("我们在野生动物园玩");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_process_long_sentence() {
        ForwardMaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("我们的VRF-989是個很棒的產品Akka是一种高度可扩展的软件，这不仅仅表现在性能方面，也表现在它所适用的应用的大小。");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_rmm_process() {
        ReverseDirectionMaximumMatchingSegmentation segmentation = new ReverseDirectionMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("我们在野生动物园玩");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_rmm_process_long_sentence() {
        ReverseDirectionMaximumMatchingSegmentation segmentation = new ReverseDirectionMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("我们的VRF-989是個很棒的產品。Akka是一种高度可扩展的软件，这不仅仅表现在性能方面，也表现在它所适用的应用的大小。");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_rmm_process2() {
        MaximumMatchingSegmentation segmentation = new ReverseDirectionMaximumMatchingSegmentation("/Users/tzuyichao/lab/data/segment/test_dic.txt", "/Users/tzuyichao/lab/data/segment/test_stop_words.txt");
        List<SegmentToken> tokens = segmentation.process("研究生命的起源");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_process2() {
        MaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation("/Users/tzuyichao/lab/data/segment/test_dic.txt", "/Users/tzuyichao/lab/data/segment/test_stop_words.txt");
        List<SegmentToken> tokens = segmentation.process("研究生命的起源");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

    @Test
    public void test_process2_2() {
        MaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("研究生命的起源");
        assertNotNull(tokens);
        System.out.println(tokens);
    }

//     客戶要檢測探針的品質，共有2種需求。 1) 量測探針頂點爪子是否有崩齒: 因為探針只有3mm長，爪子很小，需要用顯微鏡頭放大10倍來看，但因為放大倍率很大，所以景深只有10um，而探針本身長度因為會有公差，所以會無準穩定對焦，目前此案先不進行。 2) 量測探針共9處尺寸: 因為當初的測試報告結果還算ok，因此前往客戶端上機實測。
//    測試結果還算ok，客戶目前第一部設備正在製做，近期會先下單一套，後續待此開發機完成後，才會開始量產設備。


    @Test
    public void test_fmm_process3_1() {
        MaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("客戶要檢測探針的品質，共有2種需求。 1) 量測探針頂點爪子是否有崩齒: 因為探針只有3mm長，爪子很小，需要用顯微鏡頭放大10倍來看，但因為放大倍率很大，所以景深只有10um，而探針本身長度因為會有公差，所以會無準穩定對焦，目前此案先不進行。 2) 量測探針共9處尺寸: 因為當初的測試報告結果還算ok，因此前往客戶端上機實測。\n" +
                "    測試結果還算ok，客戶目前第一部設備正在製做，近期會先下單一套，後續待此開發機完成後，才會開始量產設備。");
        assertNotNull(tokens);
        System.out.println(tokens);
        System.out.println(tokens.size());
    }

    @Test
    public void test_bmm_process3_1() {
        MaximumMatchingSegmentation segmentation = new ReverseDirectionMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("客戶要檢測探針的品質，共有2種需求。 1) 量測探針頂點爪子是否有崩齒: 因為探針只有3mm長，爪子很小，需要用顯微鏡頭放大10倍來看，但因為放大倍率很大，所以景深只有10um，而探針本身長度因為會有公差，所以會無準穩定對焦，目前此案先不進行。 2) 量測探針共9處尺寸: 因為當初的測試報告結果還算ok，因此前往客戶端上機實測。\n" +
                "    測試結果還算ok，客戶目前第一部設備正在製做，近期會先下單一套，後續待此開發機完成後，才會開始量產設備。");
        assertNotNull(tokens);
        System.out.println(tokens);
        System.out.println(tokens.size());
    }

    @Test
    public void test_fmm_process3_2() {
        MaximumMatchingSegmentation segmentation = new ForwardMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("協助客戶設定絕對座標\n" +
                "1.客戶使用絕對型馬達、協助客戶設置絕對型座標 2. 以軟體示波器觀察負載變化情形 3. 現場讓機器進行多次的測試，確認沒有問題 4. 高慣量伺服馬達  觀測波形，在上升階段，因為剪刀臂機構關係，負載會上升，數值為額定電流的60%，屬正常範圍\n" +
                "機構：上控是台達PLC(DVP-SE)->兩台伺服馬達搭配減速機帶動剪刀臂動作(閘門開關)\n" +
                "之後客戶會移至戶外做測試，因機台需架設在外面環境，持續追蹤情形以確認沒有問題");
        assertNotNull(tokens);
        System.out.println(tokens);
        System.out.println(tokens.size());
    }

    @Test
    public void test_bmm_process3_2() {
        MaximumMatchingSegmentation segmentation = new ReverseDirectionMaximumMatchingSegmentation();
        List<SegmentToken> tokens = segmentation.process("協助客戶設定絕對座標\n" +
                "1.客戶使用絕對型馬達、協助客戶設置絕對型座標 2. 以軟體示波器觀察負載變化情形 3. 現場讓機器進行多次的測試，確認沒有問題 4. 高慣量伺服馬達  觀測波形，在上升階段，因為剪刀臂機構關係，負載會上升，數值為額定電流的60%，屬正常範圍\n" +
                "機構：上控是台達PLC(DVP-SE)->兩台伺服馬達搭配減速機帶動剪刀臂動作(閘門開關)\n" +
                "之後客戶會移至戶外做測試，因機台需架設在外面環境，持續追蹤情形以確認沒有問題");
        assertNotNull(tokens);
        System.out.println(tokens);
        System.out.println(tokens.size());
    }
}
