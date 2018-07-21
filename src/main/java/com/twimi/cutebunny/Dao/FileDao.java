package com.twimi.cutebunny.Dao;

import com.twimi.cutebunny.Model.FileModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FileDao {

    @Select("Select * From tp_file Where id=#{id};")
    FileModel getFileById(@Param("id") int id);

    @Select("Select * From tp_file Where username=#{username} And filename=#{filename};")
    FileModel getFileByUsernameAndFilename(@Param("username") String username, @Param("filename") String filename);

    @Select("Select * From tp_file Where username=#{username};")
    List<FileModel> getFilesByUsername(@Param("username") String username);

    @Update("Update tp_file Set filehash=#{filehash},blockhash=#{blockhash} Where username=#{username} And filename=#{filename};")
    int update(FileModel model);

    @Insert("Insert Into tp_file(username,filename,filehash,blockhash) VALUES (#{username},#{filename},#{filehash},#{blockhash});")
    int insert(FileModel model);
}
