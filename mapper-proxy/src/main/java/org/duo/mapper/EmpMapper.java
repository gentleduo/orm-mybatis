package org.duo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.duo.pojo.Emp;

/**
 * 按照以往的方式必须再新建EmpMapper的实现类EmpMapperImpl，然后在EmpMapperImpl中调用sqlSession的SelectOne、SelectList、SelectList等方法完成数据库操作;
 * 使用Mybatis代理模式则不需要额外创建实现类，而是通过创建EmpMapper.xml，在EmpMapper.xml中实现数据库访问逻辑
 */
public interface EmpMapper {

    public List<Emp> findAll();

    /**
     * 根据员工编号查询单个员工信息的方法
     *
     * @param empno 员工编号
     * @return Emp对象
     */
    public Emp findByEmpno(int empno);

    /**
     * 根据部门号和薪资下限查询员工信息
     *
     * @param deptno 部门编号
     * @param sal    薪资下限
     * @return 多个Emp对象的List集合
     */
    /*public List<Emp> findByDeptnoAndSal(int deptno, double sal);*/
    public List<Emp> findByDeptnoAndSal(@Param("deptno") int deptno, @Param("sal") double sal);

    /**
     * 根据参数集合查询员工信息
     *
     * @param map 参数集合
     * @return 多个Emp对象的List集合
     */
    public List<Emp> findByMap(Map<String, Object> map);

    /**
     * 根据引用类型参数查询员工信息
     *
     * @param emp Emp对象
     * @return 多个Emp对象的List集合
     */
    public List<Emp> findByObject(Emp emp);


    /**
     * 根据员工名字模糊匹配多个员工信息
     *
     * @param ename 员工姓名片段
     * @return 多个Emp对象的List集合
     */
    public List<Emp> findByLike(String ename);

    /**
     * 增加员工信息
     *
     * @param emp 新增员工信息的Emp对象
     * @return 对数据库数据产生影响的行数
     */
    int addEmp(Emp emp);

    /**
     * 根据员工编号修改员工姓名的方法
     *
     * @param empno 要修改的员工编号
     * @param ename 修改之后的新的员工名字
     * @return 对数据库数据产生影响的行数
     */
    int updateEnameByEmpno(@Param("empno") int empno, @Param("ename") String ename);

    /**
     * 根据员工编号删除员工信息
     *
     * @param empno 要删除的员工编号
     * @return 对数据库数据产生影响的行数
     */
    int deleteByEmpno(int empno);
}
