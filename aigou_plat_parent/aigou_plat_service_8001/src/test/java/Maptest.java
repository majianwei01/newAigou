import org.junit.Test;

import java.util.*;

public class Maptest {
    public static void main2(String[] args) {
        Student stu1 = new Student();
        stu1.setId(1L);
        stu1.setName("xiaowang");
        Map<String,Student> map = new HashMap<>();
        Student a = map.put("a", stu1);

        System.out.println(map.get("a"));
    }
    @Test
    public void test() throws Exception{
        Map<String, String> map = new HashMap<>();
        //Set<Map.Entry<String, Object>> entries = map.entrySet();
        map.put("aa", "bb");
        map.put("cc", "dd");
        map.put("ee", "ff");
        for (String s : map.keySet()) {
            System.out.println("s:"+s);
        }
        for (String value : map.values()) {
            System.out.println("value:"+value);
        }
        System.out.println(map.get("aa"));
        System.out.println(map);//{aa=bb, cc=dd, ee=ff}
        System.out.println(map.keySet());//[aa, cc, ee]
        System.out.println(map.entrySet());//[aa=bb, cc=dd, ee=ff]
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("entry:"+entry);
            System.out.println("key:"+entry.getKey());

        }

    }
    @Test
    public void te2st() throws Exception{
    String str = ".1.2.6.";
        String substring = str.substring(1, str.length() - 1);
        String[] split = substring.split("\\.");
        long[] longs = new long[split.length];
        for (int i = 0;i<split.length;i++){
            longs[i] = Long.parseLong(split[i]);
            System.out.println(longs[i]);
        }
        System.out.println(longs);

        /*   1.| 2.. 3.* 4.+ 这些特殊符号需要转义
        * String str1="sfs|abcd";
        String str2="sfs.abcd";
        String str3="sfs&abcd";
        String str4="sfs*abcd";
        String str5="sfs+abcd";

        String[] strs1=str1.split("\\|");
        String[] strs2=str2.split("\\.");
        String[] strs3=str3.split("&");
        String[] strs4=str4.split("\\*");
        String[] strs5=str5.split("\\+");
        * */
    }
    @Test
    public void teest() throws Exception{

        int a = 1;
        int b = 2;
        int c = 3;
        List<Integer> objects = new ArrayList<>();
        objects.add(a);
        objects.add(b);
        objects.add(c);
        System.out.println(objects);

    }
    @Test
    public void tesqt() throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("", "a");
        //map.put("", "b");
        System.out.println(map.get(""));
        Hashtable<String,String> kk = new Hashtable<>();
        kk.put(null, "ss");
        System.out.println(kk);
        new Object();

    }

    public static void a() {
        //b();静态不能调用非静态
        //其他类调用只能通过类来访问调用类的静态方法
        //类的static修饰的方法其子类是不能覆写的
    }
    public void b(){
        a();//普通方法可以调用静态方法
    }
    public void s(){
    }

}
