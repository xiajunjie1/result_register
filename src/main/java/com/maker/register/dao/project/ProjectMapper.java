package com.maker.register.dao.project;

import com.maker.register.model.Project;

import java.util.List;
import java.util.Map;

public interface ProjectMapper {
    public int insertProject(List<Project> projects)throws Exception;

    public List<Project> selectAllProjectsByDate(Map<String,Object> map)throws Exception;

    public int insertUpdateProject(List<Project> projects)throws Exception;

}
