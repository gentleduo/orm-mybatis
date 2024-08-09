package org.duo.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.duo.mapper.DeptMapper;
import org.duo.mapper.EmpMapper;
import org.duo.pojo.Dept;
import org.duo.pojo.Emp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
    public void selectList() {

        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.findAll();
        emps.forEach(System.out::println);
    }

    @Test
    public void testOneToMany() {
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.findDeptJoinEmpsByDeptno(20);
        System.out.println(dept);
        System.out.println("---------");
        List<Emp> empList = dept.getEmpList();
        empList.forEach(System.out::println);
    }


    @After
    public void release() {
        sqlSession.close();
    }
}
