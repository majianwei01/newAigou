import org.junit.Test;

public class CCC{
    public static void main(String[] args) {
        class CC{
            public int i = 3;
        }
        Object cc =new CC();
        CC ccc = (CC)cc;
        System.out.println(ccc.i);

    }
    @Test
    public void test() throws Exception{
        int a = 2;
        int b = 3;
        System.out.println(a<<b);

    }


}
