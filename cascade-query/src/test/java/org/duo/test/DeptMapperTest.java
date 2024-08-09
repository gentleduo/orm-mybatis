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
    public void findByDetpno() {
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = deptMapper.findDeptByDeptno(20);
        System.out.println(dept.getDname());
//        System.out.println(dept.getDeptno());
//        System.out.println(dept.getLoc());
//        List<Emp> empList = dept.getEmpList();
//        empList.forEach(System.out::println);

    }

    @After
    public void release() {
        sqlSession.close();
    }
}
