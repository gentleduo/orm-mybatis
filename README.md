# ORM

JDBC的缺点：需要手动的完成面向对象的Java语言和面向关系的数据库之间数据的转换，代码繁琐无技术含量，影响了开发效率。比如查询时需要手动的将数据集的列数据转换为Java对象的属性；而添加操作时需要手动将Java对象的属性转换为数据库表的列字段；关于面向对象的Java语言和面向关系的数据库之间数据的转换必须要做，问题在于这个转换是否可以不由开发者来做，ORM框架就是专门来做解决这个问题的，相当于在面向对象语言和关系数据库之间搭建一个桥梁。

ORM，Object-Relation Mapping，对象关系映射，它的作用是在关系型数据库和对象之间作一个映射，这样在具体操作数据库的时候，只要像平常操作对象一样操作它就可以了，ORM框架会根据映射完成对数据库的操作，就不需要和复杂的SQL语句打交道了。（MyBatis是一个半自动化的ORM框架）



# DDL

```sql
-- collate指定用来排序的规则，对于mysql中那些字符类型的列，如VARCHAR，CHAR，TEXT类型的列，都需要有一个COLLATE类型来告知mysql如何对该列进行排序和比较。简而言之，COLLATE会影响到ORDER BY语句的顺序，会影响到WHERE条件中大于小于号筛选出来的结果，会影响DISTINCT、GROUP BY、HAVING语句的查询结果。另外，mysql建索引的时候，如果索引列是字符类型，也会影响索引创建，只不过这种影响我们感知不到。总之，凡是涉及到字符类型比较或排序的地方，都会和COLLATE有关。
/* 
 *  utf8mb4指的是使用UTF-8编码方案支持完整的Unicode字符集，包括4字节的扩展字符（multibyte characters），这是MySQL对Unicode的支持最好的字符集。
 * 0900 是指Unicode校对规则版本为0900。
 * ai 指的是accent insensitive，意味着校对过程中不区分字符的发音符号或者音调。
 * ci 指的是case insensitive，意味着在校对过程中不区分字符的大小写。
 *
 */
create database if not exists mybatis_dev default character set utf8mb4 collate utf8mb4_0900_ai_ci;

CREATE TABLE `dept` (
  `deptno` int NOT NULL,
  `dname` char(32) DEFAULT NULL,
  `loc` char(32) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into dept(deptno, dname, loc) values(10,'ACCOUNTING','NEW YORK');
insert into dept(deptno, dname, loc) values(20,'RESEARCH','DALLAS');
insert into dept(deptno, dname, loc) values(30,'SALES','CHICAGO');
insert into dept(deptno, dname, loc) values(40,'OPERATIONS','BOSTON');

CREATE TABLE `emp` (
  `empno` int NOT NULL,
  `ename` char(32) DEFAULT NULL,
  `job` char(32) DEFAULT NULL,
  `mgr` int DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `sal` double DEFAULT NULL,
  `comm` double DEFAULT NULL,
  `deptno` int DEFAULT NULL,
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800.00 , NULL, 20);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.00 , 300.00, 30);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250.00 , 500.00, 30);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975.00 , NULL, 20);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250.00 , 1400.00, 30);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850.00 , NULL, 30);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450.00 , NULL, 10);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19', 3000.00 , NULL, 20);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000.00 , NULL, 10);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500.00 , 0.00, 30);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7876, 'ADAMS', 'CLERK', 7788, '1987-05-23', 1100.00 , NULL, 20);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950.00 , NULL, 30);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000.00 , NULL, 20);
insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)values(7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300.00 , NULL, 10);

-- 修改empno为自动递增
ALTER TABLE emp MODIFY empno INT AUTO_INCREMENT;
```



