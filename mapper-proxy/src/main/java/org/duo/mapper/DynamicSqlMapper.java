package org.duo.mapper;

import org.apache.ibatis.annotations.Param;
import org.duo.pojo.Emp;

import java.util.List;
import java.util.Map;

/**
 * 按照以往的方式必须再新建EmpMapper的实现类EmpMapperImpl，然后在EmpMapperImpl中调用sqlSession的SelectOne、SelectList、SelectList等方法完成数据库操作;
 * 使用Mybatis代理模式则不需要额外创建实现类，而是通过创建EmpMapper.xml，在EmpMapper.xml中实现数据库访问逻辑
 */
public interface DynamicSqlMapper {

    public List<Emp> findByIf(Emp emp);

    public List<Emp> findByWhere(Emp emp);

    public List<Emp> findByWhereChoose(Emp emp);

    public int updateBySet(Emp emp);

    public int updateByTrim(Emp emp);

    public List<Emp> findByArray(int[] empnos);

    public List<Emp> findByList(List<Integer> empnos);
}
