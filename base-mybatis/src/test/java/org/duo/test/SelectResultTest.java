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
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SelectResultTest {

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
    public void selectOne() {

        Emp emp = sqlSession.selectOne("org.duo.mapper.SelectResultEmpMapper.findOne");
        System.out.println(emp);

    }

    @Test
    public void selectList() {

        List<Emp> list = sqlSession.selectList("org.duo.mapper.SelectResultEmpMapper.findAll");
        list.forEach(System.out::println);
    }

    @Test
    public void selectMap() {

        Map<Integer, Emp> empMaps = sqlSession.selectMap("org.duo.mapper.SelectResultEmpMapper.findMap", "empno");
        Set<Integer> empnos = empMaps.keySet();
        for (Integer empno : empnos) {
            System.out.println(empno + " : " + empMaps.get(empno));
        }
    }

    @After
    public void release() {
        sqlSession.close();
    }
}
