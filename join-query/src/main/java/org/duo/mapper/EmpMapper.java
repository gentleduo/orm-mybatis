package org.duo.mapper;

import org.duo.pojo.Emp;

import java.util.List;


/**
 * 按照以往的方式必须再新建EmpMapper的实现类EmpMapperImpl，然后在EmpMapperImpl中调用sqlSession的SelectOne、SelectList、SelectList等方法完成数据库操作;
 * 使用Mybatis代理模式则不需要额外创建实现类，而是通过创建EmpMapper.xml，在EmpMapper.xml中实现数据库访问逻辑
 */
public interface EmpMapper {

    public List<Emp> findAll();

    /**
     * 一对一关联查询
     * 根据员工编号查询员工的所有信息并携带所在的部门信息
     *
     * @param empno 要查询的员工编号
     * @return Emp对象, 组合了Dept对象作为属性, 对部门信息进行存储
     */
    Emp findEmpJoinDeptByEmpno(int empno);

}
