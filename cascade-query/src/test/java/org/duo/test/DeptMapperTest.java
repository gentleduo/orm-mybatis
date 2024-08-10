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
    public void findByDeptno() {
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = deptMapper.findDeptByDeptno(20);
        System.out.println(dept.getDname());
//        System.out.println(dept.getDeptno());
//        System.out.println(dept.getLoc());
//        List<Emp> empList = dept.getEmpList();
//        empList.forEach(System.out::println);

    }

    @Test
    public void findDeptByAnnotations() {
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = deptMapper.findByDeptno(20);
        System.out.println(dept);
//        System.out.println(dept.getDeptno());
//        System.out.println(dept.getLoc());
//        List<Emp> empList = dept.getEmpList();
//        empList.forEach(System.out::println);

    }

    @Test
    public void insertDept() {
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = new Dept(41, "研发部", "上海");
        deptMapper.addDept(dept);
        sqlSession.commit();

    }

    @Test
    public void updateDept() {
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = new Dept(41, "销售部", "北京");
        deptMapper.updateDept(dept);
        sqlSession.commit();

    }

    @Test
    public void deleteDept() {
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        deptMapper.removeDept(41);
        sqlSession.commit();

    }


    @After
    public void release() {
        sqlSession.close();
    }
}
