package com.qf.service;

import com.qf.vo.ProductVO;

import java.util.List;

public interface SolrService {
    List<ProductVO> selectByKeyWord(String key_word, Integer page);
}
