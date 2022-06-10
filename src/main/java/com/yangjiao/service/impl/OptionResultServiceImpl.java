package com.yangjiao.service.impl;

import com.yangjiao.domain.OptionResult;
import com.yangjiao.mapper.OptionResultMapper;
import com.yangjiao.service.OptionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionResultServiceImpl implements OptionResultService {
    @Autowired
    private OptionResultMapper optionResultMapper;
    @Override
    public OptionResult add(OptionResult optionResult) {
        optionResultMapper.insert(optionResult);
        return optionResultMapper.selectById(optionResult.getId());
    }
}
