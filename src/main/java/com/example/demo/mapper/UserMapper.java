package com.example.demo.mapper;


import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,accoinId,token,gmtCreate,gmtModified) values (#{name},#{accoinId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

//    @Select("SELECT * FROM CITY WHERE state = #{state}")
//    City findByState(@Param("state") String state);
}
