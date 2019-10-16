package com.majianwei.redis.ESservice.ES_impl;

import com.majianwei.redis.ESservice.IEsService;
import com.majianwei.common.es_productDoc.ProductDoc;
import com.majianwei.redis.repository.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsServiceImpl implements IEsService {

    @Autowired
    private ESRepository esRepository;
    @Override
    public void addOne(ProductDoc productDoc) {
        esRepository.save(productDoc);

    }

    @Override
    public void addBatch(List<ProductDoc> list) {
        esRepository.saveAll(list);

    }

    @Override
    public void delOne(Long id) {
            esRepository.deleteById(id);

    }

    @Override
    public void delBatch() {
        esRepository.deleteAll();

    }
}
