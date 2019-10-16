import org.junit.Test;

public class SubStringTest {
    @Test
    public void test() throws Exception{
        String example = "123456879";
        System.out.println(example.substring(0, 3));
        System.out.println(example.length());
        String substring = example.substring(0, 1);
        //String substring = example.substring(1, 2);
        //System.out.println(substring);
//        for (int i = 0;i<example.length();i++){
//            String substring1 = example.substring(i, i + 1);
//            System.out.println(substring1);
//        }

    }

    public int[] maoPaoTest(int[] ints) throws Exception{
        for (int i = 1;i<ints.length;i++){
            for (int j = 0;j<ints.length - i;j++){
                System.out.println("lllll:"+j);
                int temp;
                if (ints[j]>ints[j+1]){
                    temp = ints[j];
                    ints[j]=ints[j+1];
                    ints[j+1] =temp;
                }
            }
        }
        return ints;
    }
    @Test
    public void test2() throws Exception{
        int[] ints = {1, 6, 9, 4};
        maoPaoTest(ints);
        for (int i = 0;i<ints.length;i++){
            System.out.println(ints[i]+"");
        }
    }
}
