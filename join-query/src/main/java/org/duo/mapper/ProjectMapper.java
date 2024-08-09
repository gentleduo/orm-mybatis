package org.duo.mapper;

import org.duo.pojo.Project;

/**
 * 按照以往的方式必须再新建EmpMapper的实现类EmpMapperImpl，然后在EmpMapperImpl中调用sqlSession的SelectOne、SelectList、SelectList等方法完成数据库操作;
 * 使用Mybatis代理模式则不需要额外创建实现类，而是通过创建EmpMapper.xml，在EmpMapper.xml中实现数据库访问逻辑
 */
public interface ProjectMapper {

    /**
     * 根据项目编号查询一个项目信息及参与该项目的所有员工信息
     *
     * @param pid 项目编号
     * @return 所有信息封装的Project对象
     */
    Project findProjectJoinEmpsByPid(int pid);

}
