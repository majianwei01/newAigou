package com.majianwei.es;

import com.majianwei.redis.CommonApplication_8848;
import com.majianwei.common.es_productDoc.ProductDoc;
import com.majianwei.redis.repository.ESRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication_8848.class)
public class ESTest {

    @Autowired
    private ESRepository esRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //es初始化
    @Test
    public void init() throws Exception{
        //创建索引
        elasticsearchTemplate.createIndex(ProductDoc.class);
        //做映射
        elasticsearchTemplate.putMapping(ProductDoc.class);
    }

}
