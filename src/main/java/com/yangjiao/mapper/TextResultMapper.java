package com.yangjiao.mapper;

import com.yangjiao.domain.Question;
import com.yangjiao.domain.TextResult;
import com.yangjiao.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TextResultMapper {
    int insert(TextResult textResult);

    TextResult selectById(int id);

    List<TextResult> selectAllByQuestionId(@Param("questionId") int questionId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}
