import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//手机号码合法性测试
public class TelNumIleagleTest {

    public boolean telNumTest(String telNum) {
        String str = "^[1][3579]\\d{9}$";
        Pattern compile = Pattern.compile(str);
        Matcher matcher = compile.matcher(telNum);
        return matcher.matches();
    }

    @Test
    public void test() throws Exception{
        System.out.println(telNumTest("13983798840"));
        int i = 2;
        switch (i){
            case 1:
                System.out.println("hahah");
                break;
            case 2:
                System.out.println("666");
                break;
            default:
                System.out.println("默认");
                String str = "^[1][6789]\\d{9}$";


        }
    }
}
