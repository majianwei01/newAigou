import com.majianwei.util.commontools.StrUtils;
import com.majianwei.util.commontools.TypeConversion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringToLongTest {
    @Test
    public void test() throws Exception{
//        String str = "12";
//        System.out.println(str);//12
//        System.out.println(Long.parseLong(str));//12
//
//        //{"ids":"5,6"}
//        String str2 = "{ids:5,6}";
//        System.out.println(str2);
//        Map<String, Object> map = new HashMap<>();
//        map.put("ids", str2);
//        System.out.println(map.get("ids"));
        //JSONObject  jasonObject = JSONObject.fromObject(str);
        //Map map = (Map)jasonObject;
        String str = "color:red|font:yahei|width:800|height:300";
        String[] strs = str.split("\\|");
        System.out.println(strs);
//        for (String s : strs) {
//            System.out.println(s);
//        }
        Map<String, String> m = new HashMap<String, String>();
        for(String s:strs) {
            System.out.println(s);
            String[] ms = s.split(":");
            System.out.println("ms:"+ms);
            m.put(ms[0], ms[1]);
        }

    }
    @Test
    public void testsss() throws Exception{
        //{"ids":"5,6"}
        String str = "{\"ids\":\"5,6\"}";

//        long[] aLong = TypeConversion.getLong(str);
//        for (long l : aLong) {
//            System.out.println(l);
//        }
        //{"ids":"5,6"}
        //截取字符串最后3个字符
        int i = str.lastIndexOf("\"");
        System.out.println(i);

        //截取前8个字符，还有最后3个字符
        String substring = str.substring(8,i);
        System.out.println(substring);//5,6
    }
    @Test
    public void tes2t() throws Exception{
        String str = ".1.2.5.";
        int v = str.lastIndexOf("\\.");
        System.out.println("vvvvvvvvv"+v);
        //String substring = str.substring(1,str.length()-1);
        System.out.println(str.length());
        //System.out.println(str.lastIndexOf("5"));
        String substring2 = str.substring(1,str.length()-1);

        System.out.println(substring2);
      String[] split = substring2.split("\\.");
        System.out.println("aaaaaaaaaaaaa"+split.length);
        long[] longs = new long[split.length];
        for (int i = 0;i<split.length;i++){
            longs[i]=Long.parseLong(split[i]);
        }
        for (long aLong : longs) {
            System.out.println(aLong);
        }

    }
}
