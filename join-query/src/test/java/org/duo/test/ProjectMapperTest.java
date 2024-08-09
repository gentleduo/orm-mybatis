package org.duo.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.duo.mapper.ProjectMapper;
import org.duo.pojo.Emp;
import org.duo.pojo.Project;
import org.duo.pojo.ProjectRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProjectMapperTest {

    private SqlSession sqlSession;

    @Before
    public void init() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream resourceAsFile = null;
        try {
            resourceAsFile = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsFile);
        sqlSession = factory.openSession();
    }

    @Test
    public void selectManyToMany() {
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        Project project = mapper.findProjectJoinEmpsByPid(2);
        System.out.println(project.getPid());
        System.out.println(project.getPname());
        System.out.println(project.getMoney());

        List<ProjectRecord> projectRecords = project.getProjectRecords();
        for (ProjectRecord projectRecord : projectRecords) {
            Emp emp = projectRecord.getEmp();
            System.out.println(emp);
        }
    }

    @After
    public void release() {
        sqlSession.close();
    }
}
