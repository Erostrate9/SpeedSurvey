package com.yangjiao.mapper;

import com.yangjiao.domain.Option;

import java.util.List;

public interface OptionMapper {
    int insert(Option option);
    Option selectById(int id);
    List<Option> selectAllByQuestionId(int quesitonId);

}
