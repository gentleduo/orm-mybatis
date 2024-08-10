package org.duo.mapper;

import org.duo.pojo.Emp;

/**
 * 一级存储
 * 1、是SqlSession上的缓存，默认开启，是一种内存型缓存,不要求实体类对象实现Serializable接口；
 * 2、缓存中的数据使用键值对形式存储数据：namespace+sqlid+args+offset >>> hash值作为键，查询出的结果作为值；
 * 3、中间发生了增删改或者是调用了SqlSession调用了commit,会自动清空缓存，因此也可以理解为同一事务内的缓存；
 * 二级缓存
 * 1、二级缓存是以namespace为标记的缓存，可以是由一个SqlSessionFactory创建的SqlSession之间共享缓存数据；
 * 2、默认并不开启、实体类必须实现序列化接口；
 * 3、二级缓存未必完全使用内存,有可能占用硬盘存储,所以缓存中存储的JavaBean对象必须实现序列化接口
 * 4、必须有一个SqlSession在执行commit或者close的时候才会将数据放入到二级缓存，其他SqlSession执行相同SQL语句时就直接从二级缓存中获取了，因此也可以理解为不同事务间的缓存；
 */
public interface EmpMapper {

    public Emp findByEmpno(int empno);
}
