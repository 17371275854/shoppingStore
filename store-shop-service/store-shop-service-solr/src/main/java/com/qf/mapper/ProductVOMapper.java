package com.qf.mapper;

import com.qf.vo.ProductVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ProductVOMapper{
    List<ProductVO> selectAll();
}
