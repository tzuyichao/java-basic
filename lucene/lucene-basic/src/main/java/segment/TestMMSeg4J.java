package segment;

import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Word;

import java.io.IOException;
import java.io.StringReader;

public class TestMMSeg4J {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = Dictionary.getInstance();
        MMSeg mmSeg = new MMSeg(new StringReader("上一堂課之後跑18km與2500rpm的挑戰"), new ComplexSeg(dictionary));
        Word word = null;
        boolean first = true;
        while((word=mmSeg.next())!=null) {
            System.out.println(word.getString());
        }
    }
}
