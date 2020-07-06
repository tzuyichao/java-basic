package basic;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodLab {
    static interface SolrConstants {
        String SEARCH_HIGHLIGHT_LEFT_EM = "<em>";
        String SEARCH_HIGHLIGHT_RIGHT_EM = "</em>";
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class nodeClz = Node.class;
        Method getIdMeth = nodeClz.getMethod("setId", int.class);
        System.out.println(getIdMeth.toString());

        String test= "{\"machine\":\"插件機\",\"process\":\"gogogo <em>test</em>\",\"module\":\"供料系統\",\"<em>kpov</em>\":\"卡料\",\"kpovSpec\":\"零件0卡料\",\"kpovSmartFunction\":\"In-time\\nwarning (1)\",\"kpovSmartFunctionDesc\":\"卡料及時自動報警\",\"kpiv\":\"軌道光滑度\",\"kpivSpec\":\"表面粗糙度等級3.2-6.3\",\"kpivSmartFunction\":\"\",\"kpivSmartFunctionDesc\":\"\"} ";
        System.out.println(removeKeyWithHighLighting(test));
    }

    private static String removeKeyWithHighLighting(String jsonContent) {
        String KEY_REGEX = "<em>(.+?)</em>.:";
        String value = jsonContent;
        Pattern pattern = Pattern.compile(KEY_REGEX);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            String org = matcher.group();
            String match = org.replace(SolrConstants.SEARCH_HIGHLIGHT_LEFT_EM, "").replace(SolrConstants.SEARCH_HIGHLIGHT_RIGHT_EM, "");
            int idx = value.indexOf(org);
            value = value.substring(0, idx) + match + value.substring(idx + org.length());
            matcher = pattern.matcher(value);
        }
        return value;
    }
}
