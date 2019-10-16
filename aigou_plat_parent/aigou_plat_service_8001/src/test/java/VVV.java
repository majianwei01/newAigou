import org.junit.Test;

public class VVV extends MMM {
    @Override
    public void get() {
        super.get();
        new Object();
        new String();
    }
    @Test
    public void stringtest(){
        String s = "";
        int length = s.length();
        System.out.println(length);
    }
    @Test
    public void whileTest() throws Exception{
        boolean param = true;
        while (param){
            System.out.println("为真就一直循环下去");
            param = false;
        }
    }
    //求两个整数之间的最大公约数
    //大的能整除已小的那么小的就是最大公约数
    //
    public int gys(int m,int n) throws Exception{
        int k,y;
        if (m<n){
            k = m;
            m = n;
            n = k;
        }
        while (m % n != 0){//3和2
            y = m % n;
            System.out.println("余数y:"+y);
            m = n;
            System.out.println("m:"+m);
            n = y;
            System.out.println("n:"+n);
        }
        return n;
    }
    public int gbs(int m,int n) throws Exception{
        int gys = gys(m, n);
        System.out.println("最大公约树"+gys);
        System.out.println("想成"+m*n);
        return  (m*n)/gys;
    }
    @Test
    public void zdgys() throws Exception{
        //System.out.println("最大公约数"+gys(4453, 5679));
        System.out.println("最大公倍数"+gbs(3, 5));
    }
    @Test
    public void test() throws Exception{
        System.out.println(64 % 24);
    }
}
