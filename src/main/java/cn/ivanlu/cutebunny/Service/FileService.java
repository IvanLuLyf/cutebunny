package cn.ivanlu.cutebunny.Service;

import cn.ivanlu.cutebunny.Model.FileModel;

import java.util.List;

public interface FileService {
    List<FileModel> getFilesByUsername(String username);

    FileModel getFileById(int id);

    int saveFile(FileModel model);
}
