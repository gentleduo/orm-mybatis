package org.duo.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.duo.pojo.Emp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchParameterTest {

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
    public void selectBySingleArg() {

        Emp emp = sqlSession.selectOne("org.duo.mapper.SearchParameterEmpMapper.findByEmpno", 7521);
        System.out.println(emp);

    }

    @Test
    public void selectByMapArg() {

        HashMap<Object, Object> args = new HashMap<>();
        args.put("deptno", 20);
        args.put("sal", 1500.0);
        List<Emp> emps = sqlSession.selectList("org.duo.mapper.SearchParameterEmpMapper.findEmpByDeptnoAndSal", args);
        emps.forEach(System.out::println);

    }

    @Test
    public void selectByObjectArg() {

        Emp emp = new Emp();
        emp.setDeptno(10);
        emp.setSal(2000.0);
        List<Emp> emps = sqlSession.selectList("org.duo.mapper.SearchParameterEmpMapper.findEmpByObject", emp);
        emps.forEach(System.out::println);

    }

    @After
    public void release() {
        sqlSession.close();
    }
}
