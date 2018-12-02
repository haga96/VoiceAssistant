package com.example.hania.voiceassistant.dao;

import java.util.List;

public interface IArgDao {
    public Arg fetchArgById(int argId);
    public List<Arg> fetchAllArgs();
    public boolean addArgs(Arg arg);
    public boolean deleteAllArgs();
}
