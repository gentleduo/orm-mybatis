package org.duo.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.duo.mapper.DeptMapper;
import org.duo.pojo.Dept;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class DeptMapperTest {

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
    public void addDept() {

        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = new Dept();
        dept.setDeptno(null);
        dept.setDname("IT");
        dept.setLoc("CHINA");
        System.out.println(dept);
        mapper.addDept(dept);
        sqlSession.commit();
        System.out.println(dept);
    }

    @After
    public void release() {
        sqlSession.close();
    }
}
