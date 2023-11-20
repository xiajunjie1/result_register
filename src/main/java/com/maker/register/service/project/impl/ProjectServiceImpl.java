package com.maker.register.service.project.impl;

import com.maker.register.dao.project.ProjectMapper;
import com.maker.register.model.Project;
import com.maker.register.service.project.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {
    private static final Logger LOGGER= LoggerFactory.getLogger(ProjectServiceImpl.class);
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public int insertProjects(List<Project> projects) throws Exception {
        if(projects==null||projects.isEmpty()){
            throw new RuntimeException("插入的项目数据为空！");
        }

        return projectMapper.insertProject(projects);
    }

    @Override
    public List<Project> getProjectsByDate(Map<String, Object> map) throws Exception {
        if(map==null || map.isEmpty()){
            throw new RuntimeException("查询项目参数为空！");
        }
        return projectMapper.selectAllProjectsByDate(map);
    }

    @Override
    public int replaceProjects(List<Project> projects) throws Exception {
        if(projects==null || projects.isEmpty()){
            throw new RuntimeException("更新的项目数据为空！");
        }
        return projectMapper.insertUpdateProject(projects);
    }
}
