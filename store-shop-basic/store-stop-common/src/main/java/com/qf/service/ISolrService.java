package com.qf.service;

import com.qf.dto.ResultBean;

public interface ISolrService {
    ResultBean seek(String keyword, Long page);
}
