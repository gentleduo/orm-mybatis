package org.duo.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.duo.mapper.DynamicSqlMapper;
import org.duo.mapper.EmpMapper;
import org.duo.pojo.Emp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DynamicSqlMapperTest {

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
    public void selectListByIf() {

        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp = new Emp();
        //emp.setEmpno(7521);
        emp.setEname("A");
        List<Emp> emps = mapper.findByIf(emp);
        emps.forEach(System.out::println);
    }

    @Test
    public void selectListByWhere() {

        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp = new Emp();
        emp.setEmpno(7521);
        //emp.setEname("A");
        List<Emp> emps = mapper.findByWhere(emp);
        emps.forEach(System.out::println);
    }

    @Test
    public void selectListByWhereChoose() {

        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp = new Emp();
        emp.setEmpno(7521);
        emp.setEname("A");
        List<Emp> emps = mapper.findByWhereChoose(emp);
        emps.forEach(System.out::println);
    }

    @Test
    public void updateBySet() throws ParseException {

        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp = new Emp();
        emp.setEmpno(7521);
        emp.setEname("TOM");
        emp.setSal(2350.0);
        emp.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse("1984-04-02"));
        mapper.updateBySet(emp);
        sqlSession.commit();
    }

    @Test
    public void updateByTrim() throws ParseException {

        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        Emp emp = new Emp();
        emp.setEmpno(7521);
//        emp.setEname("TOM");
//        emp.setSal(2350.0);
        emp.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse("1983-03-19"));
        mapper.updateByTrim(emp);
        sqlSession.commit();
    }

    @Test
    public void selectListByArray() {

        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        int[] empnos = {7521, 7839, 7499};
        List<Emp> emps = mapper.findByArray(empnos);
        emps.forEach(System.out::println);
    }

    @Test
    public void selectListByList() {

        DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        List<Integer> empnos = new ArrayList<Integer>();
        Collections.addAll(empnos, 7521, 7839, 7499);
        List<Emp> emps = mapper.findByList(empnos);
        emps.forEach(System.out::println);
    }

    @After
    public void release() {
        sqlSession.close();
    }
}
