package com.example.basic.mapper;

import com.example.basic.entity.Country;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CountryMapper {
    @Select("SELECT * FROM countries")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
    })
    List<Country> getAll();
}
