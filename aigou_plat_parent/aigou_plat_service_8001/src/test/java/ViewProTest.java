import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.majianwei.plat.EurekaServerApplication_8001;
import com.majianwei.plat.domain.ProductExt;
import com.majianwei.plat.service.IProductExtService;
import com.majianwei.plat.service.ISpecificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EurekaServerApplication_8001.class)
public class ViewProTest {
    @Autowired
    private IProductExtService productExtService;
    @Test
    public void test() throws Exception{
        Wrapper<ProductExt> wrapper = new EntityWrapper<>();
        wrapper.eq("productId", 5L);
        System.out.println("AAAAAAAAAAAAA"+productExtService.selectOne(wrapper).getViewProperties());
        //System.out.println(productExtService.selectOne(wrapper));

    }
}
