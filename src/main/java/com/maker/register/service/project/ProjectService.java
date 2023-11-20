package com.maker.register.service.project;

import com.maker.register.model.Project;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    public int insertProjects(List<Project> projects) throws Exception;
    public List<Project> getProjectsByDate(Map<String,Object> map)throws Exception;

    public int replaceProjects(List<Project> projects) throws Exception;
}
