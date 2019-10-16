import org.junit.Test;

public class StringTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        System.out.println(a.equals(b));//false
        String str1 = "hello";
        String str2 = "he"+new String("llo");
        String str3 = "nihao";
        String str4 = "hello";
        String str5 = new String("hello");
        //System.out.println(str1 == str2);//false对象引用的地址不同
        System.out.println(str1.equals(str2));//true 因为比较字符串的值 值是一样的
        //System.out.println(str1+str3);
        System.out.println(str1 == str4);//
        System.out.println("==================================");
        //System.out.println(str1.equals(str4));
        System.out.println(str1 == str5);//false
        System.out.println(str1.equals(str5));//true String字符串的equals比较的是字符串的值是否相等
    }
    @Test
    public void test() throws Exception{
        Person xw = new Person("xw", 1);
        Person xw1 = new Person("xw", 1);
        Integer a = 1;
        Integer b = 1;
        System.out.println(a.equals(b));//true
        System.out.println(a == b);
//        if (xw == xw1){
//            System.out.println("duasdasdasd");
//        }
//        System.out.println(xw == xw1);//false 对象地址不同
//        System.out.println(xw.equals(xw1));// true


    }
}
