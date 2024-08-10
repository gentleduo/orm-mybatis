package org.duo.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.duo.pojo.Dept;

/**
 * 按照以往的方式必须再新建EmpMapper的实现类EmpMapperImpl，然后在EmpMapperImpl中调用sqlSession的SelectOne、SelectList、SelectList等方法完成数据库操作;
 * 使用Mybatis代理模式则不需要额外创建实现类，而是通过创建EmpMapper.xml，在EmpMapper.xml中实现数据库访问逻辑
 */
public interface DeptMapper {

    Dept findDeptByDeptno(int deptno);

    @Select("select * from dept where deptno =#{deptno}")
    Dept findByDeptno(int deptno);

    @Update("update dept set dname =#{dname}, loc =#{loc} where deptno =#{deptno}")
    int updateDept(Dept dept);

    @Insert("insert into dept values(${deptno},#{dname},#{loc})")
    int addDept(Dept dept);

    @Delete("delete from dept where deptno =#{deptno}")
    int removeDept(int deptno);

}
