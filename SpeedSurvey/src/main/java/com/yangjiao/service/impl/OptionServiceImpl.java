package com.yangjiao.service.impl;

import com.yangjiao.domain.Option;
import com.yangjiao.mapper.OptionMapper;
import com.yangjiao.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionMapper optionMapper;

    @Override
    public Option add(Option option) {
        optionMapper.insert(option);
        return optionMapper.selectById(option.getId());
    }

    @Override
    public List<Option> queryALlByQuestionId(int questionId) {
        return optionMapper.selectAllByQuestionId(questionId);
    }
}
