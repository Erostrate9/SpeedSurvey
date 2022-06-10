package com.yangjiao.mapper;

import com.yangjiao.domain.OptionResult;
import com.yangjiao.domain.TextResult;

public interface OptionResultMapper {
    int insert(OptionResult optionResult);
    OptionResult selectById(int id);
}
