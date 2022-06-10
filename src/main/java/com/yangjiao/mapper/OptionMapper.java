package com.yangjiao.mapper;

import com.yangjiao.domain.Option;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionMapper {
    int insert(Option option);
    Option selectById(int id);
    List<Option> selectAllByQuestionId(int quesitonId);
    int update(Option option);
    Integer getMaxOrderByQuestionId(int questionId);
    int deleteNotInIds(@Param("ids") Integer[] ids);

}
