package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.JudgeQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JudgeQuestionMapper {
    @Select("select * from judge_question where questionId in (select questionId from paper_manage where questionType = 3 and paperId = #{paperId})")
    List<JudgeQuestion> findByIdAndType(Integer paperId);

    @Select("select * from judge_question")
    IPage<JudgeQuestion> findAll(Page page);

    @Select("select questionId from judge_question order by questionId desc limit 1")
    JudgeQuestion findOnlyQuestionId();

    @Insert("insert into judge_question(subject,question,analysis,level,section)value " + "(#{subject},#{question},#{analysis},#{level},#{section})")
    int add(JudgeQuestion judgeQuestion);

    @Select("select questionId from judge_question where subject=#{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject, Integer pageNo);
}
