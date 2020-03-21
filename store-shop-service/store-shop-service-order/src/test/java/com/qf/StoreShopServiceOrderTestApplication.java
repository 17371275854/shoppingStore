package com.qf;

import com.qf.entity.TProduct;
import com.qf.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreShopServiceOrderTestApplication {
    @Autowired
    ProductMapper productMapper;

    @Test
    public void content() {
        Map<Long, Long> map = new HashMap();
        map.put(1L, 1L);
        map.put(3L, 1L);
        map.put(5L, 1L);
        map.put(7L, 1L);
        Example example = new Example(TProduct.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("pid", map.keySet());
        List<TProduct> tProducts = productMapper.selectByExample(example);
        System.out.println();
    }
}
