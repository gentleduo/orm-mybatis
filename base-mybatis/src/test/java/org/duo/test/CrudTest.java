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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CrudTest {

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
    public void insert() {

        Emp emp = new Emp(null, "Angelababy", "SALESMAN", 7839, new Date(), 3100.0, 200.0, 10);
        int insert = sqlSession.insert("org.duo.mapper.CrudEmpMapper.addEmp", emp);
        System.out.println("插入数据行数：" + insert);
        sqlSession.commit();// mybatis默认事务执行完后是rollback，所以在增删改之后需要手动提交；或者在factory.openSession(true)表示自动提交
    }

    @Test
    public void update() {

        Emp emp = new Emp();
        emp.setEmpno(7936);
        emp.setEname("黄晓明");
        int insert = sqlSession.update("org.duo.mapper.CrudEmpMapper.updateEmp", emp);
        System.out.println("修改数据行数：" + insert);
        sqlSession.commit();// mybatis默认事务执行完后是rollback，所以在增删改之后需要手动提交；或者在factory.openSession(true)表示自动提交
    }

    @Test
    public void delete() {

        int insert = sqlSession.delete("org.duo.mapper.CrudEmpMapper.deletyByEmpno", 7936);
        System.out.println("修改数据行数：" + insert);
        sqlSession.commit();// mybatis默认事务执行完后是rollback，所以在增删改之后需要手动提交；或者在factory.openSession(true)表示自动提交
    }

    @After
    public void release() {
        sqlSession.close();
    }
}
