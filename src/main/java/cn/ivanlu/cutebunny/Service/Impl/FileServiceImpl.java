package cn.ivanlu.cutebunny.Service.Impl;

import cn.ivanlu.cutebunny.Dao.FileDao;
import cn.ivanlu.cutebunny.Model.FileModel;
import cn.ivanlu.cutebunny.Service.FileService;
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
        FileModel existFile = fileDao.getFileByUsernameAndFilename(model.getUsername(), model.getFilename());
        if (existFile != null) {    //更新
            fileDao.update(model);
        } else {  //插入
            fileDao.insert(model);
        }
        return 0;
    }
}
