package com.twimi.cutebunny.Service.Impl;

import com.twimi.cutebunny.Dao.FileDao;
import com.twimi.cutebunny.Model.FileModel;
import com.twimi.cutebunny.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public List<FileModel> getFilesByUsername(String username) {
        return fileDao.getFilesByUsername(username);
    }

    @Override
    public FileModel getFileById(int id) {
        return fileDao.getFileById(id);
    }

    @Override
    public int saveFile(FileModel model) {
        FileModel existFile = fileDao.getFileByUsernameAndFilename(model.getUsername(),model.getFilename());
        if(existFile!=null){    //更新
            fileDao.update(model);
        }else{  //插入
            fileDao.insert(model);
        }
        return 0;
    }
}
