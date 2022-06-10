package com.yangjiao.service;

import com.yangjiao.domain.Option;
import com.yangjiao.domain.Question;

import java.util.List;

public interface OptionService {
    Option add(Option option);
    List<Option> queryALlByQuestionId(int questionId);
    Option update(Option option);
    Integer getMaxOrder(int questionId);
    int deleteNotInIds(Integer[] ids);
    Option queryById(int id);
}
