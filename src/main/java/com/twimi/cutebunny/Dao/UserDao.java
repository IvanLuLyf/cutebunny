package com.twimi.cutebunny.Dao;

import com.twimi.cutebunny.Model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {
    @Select("Select * From tp_user Where username=#{username} Or email=#{username};")
    UserModel getUser(@Param("username") String username);

    @Insert("Insert Into tp_user (username,email,password) Values (#{username},#{email},#{password});")
    int insert(UserModel model);
}
