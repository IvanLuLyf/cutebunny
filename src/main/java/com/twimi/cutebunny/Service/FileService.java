package com.twimi.cutebunny.Service;

import com.twimi.cutebunny.Model.FileModel;

import java.util.List;

public interface FileService {
    List<FileModel> getFilesByUsername(String username);

    FileModel getFileById(int id);

    int saveFile(FileModel model);
}
