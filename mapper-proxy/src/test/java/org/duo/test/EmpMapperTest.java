package org.duo.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.duo.mapper.EmpMapper;
import org.duo.pojo.Emp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EmpMapperTest {

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
    public void selectByEmpno() {

        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.findByEmpno(7499);
        System.out.println(emp);
    }

    @Test
    public void selectByDeptnoAndSal() {

        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.findByDeptnoAndSal(20, 3000.0);
        emps.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {

        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("deptno", 20);
        paramMap.put("sal", 3000.0);
        List<Emp> emps = mapper.findByMap(paramMap);
        emps.forEach(System.out::println);
    }

    @Test
    public void selectByObject() {

        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = new Emp();
        emp.setDeptno(20);
        emp.setSal(3000.0);
        List<Emp> emps = mapper.findByObject(emp);
        emps.forEach(System.out::println);
    }


    @Test
    public void selectByLike() {

        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.findByLike("a");
        emps.forEach(System.out::println);
    }

    @Test
    public void addEmp() {
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.addEmp(new Emp(null, "TOM", "SALESMAN", 7521, new Date(), 2314.0, 100.0, 10));
        sqlSession.commit();
    }

    @Test
    public void updateEnameByEmpno() {
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.updateEnameByEmpno(7937, "JERRY");
        sqlSession.commit();
    }

    @Test
    public void deleteByEmpno() {
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.deleteByEmpno(7937);
        sqlSession.commit();
    }

    @After
    public void release() {
        sqlSession.close();
    }
}
