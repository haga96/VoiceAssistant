package com.example.hania.voiceassistant.dao;

import java.util.List;

public interface IRequestDao {
    public Request fetchRequestById(int requestId);
    public List<Request> fetchAllRequests();
    public boolean addRequest(Request request);
    public boolean deleteAllRequests();
}
