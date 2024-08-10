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

public class Level1CacheTest {

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
    public void findByEmpno() {
        EmpMapper mapper1 = sqlSession.getMapper(EmpMapper.class);
        Emp emp1 = mapper1.findByEmpno(7499);
        System.out.println(emp1);
        EmpMapper mapper2 = sqlSession.getMapper(EmpMapper.class);
        Emp emp2 = mapper2.findByEmpno(7369);
        System.out.println(emp2);
        System.out.println(mapper1==mapper2);
        System.out.println(emp1==emp2);
    }

    @After
    public void release() {
        sqlSession.close();
    }
}
