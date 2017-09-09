package com.base.dao;

import com.base.entity.Record;
import com.base.entity.condition.RecordCondition;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List selectByCondition(RecordCondition record);
}