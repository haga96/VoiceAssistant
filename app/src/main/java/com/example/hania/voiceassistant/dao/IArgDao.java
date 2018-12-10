package com.example.hania.voiceassistant.dao;

import java.util.List;

public interface IArgDao {
     Arg fetchArgById(int argId);
     List<Arg> fetchAllArgs();
     boolean addArgs(Arg arg);
     boolean deleteAllArgs();
}
