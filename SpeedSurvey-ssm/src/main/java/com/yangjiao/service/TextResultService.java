package com.yangjiao.service;

import com.yangjiao.domain.TextResult;

import java.util.List;

public interface TextResultService {
    TextResult add(TextResult textResult);
    List<TextResult> queryAllByQuestionId(int questionId, int startIndex, int pageSize);
}
