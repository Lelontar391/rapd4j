package com.rapd4j.mapper;

import com.rapd4j.entity.TException;
import com.rapd4j.entity.TExceptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TExceptionMapper {
    int countByExample(TExceptionExample example);

    int deleteByExample(TExceptionExample example);

    int deleteByPrimaryKey(String eid);

    int insert(TException record);

    int insertSelective(TException record);

    List<TException> selectByExampleWithBLOBs(TExceptionExample example);

    List<TException> selectByExample(TExceptionExample example);

    TException selectByPrimaryKey(String eid);

    int updateByExampleSelective(@Param("record") TException record, @Param("example") TExceptionExample example);

    int updateByExampleWithBLOBs(@Param("record") TException record, @Param("example") TExceptionExample example);

    int updateByExample(@Param("record") TException record, @Param("example") TExceptionExample example);

    int updateByPrimaryKeySelective(TException record);

    int updateByPrimaryKeyWithBLOBs(TException record);

    int updateByPrimaryKey(TException record);
}