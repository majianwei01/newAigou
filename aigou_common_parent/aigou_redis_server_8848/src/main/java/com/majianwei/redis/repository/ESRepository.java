package com.majianwei.redis.repository;

import com.majianwei.common.es_productDoc.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESRepository extends ElasticsearchRepository<ProductDoc,Long> {
}
