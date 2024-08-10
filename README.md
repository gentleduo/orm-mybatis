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

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus`  (
  `ENAME` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `SAL` double(7, 2) NULL DEFAULT NULL,
  `COMM` double(7, 2) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bonus
-- ----------------------------

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `DEPTNO` int(2) NOT NULL,
  `DNAME` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LOC` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`DEPTNO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO `dept` VALUES (20, 'RESEARCH', 'DALLAS');
INSERT INTO `dept` VALUES (30, 'SALES', 'CHICAGO');
INSERT INTO `dept` VALUES (40, 'OPERATIONS', 'BOSTON');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `EMPNO` int(4) NOT NULL,
  `ENAME` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `MGR` int(4) NULL DEFAULT NULL,
  `HIREDATE` date NULL DEFAULT NULL,
  `SAL` double(7, 2) NULL DEFAULT NULL,
  `COMM` double(7, 2) NULL DEFAULT NULL,
  `DEPTNO` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`EMPNO`) USING BTREE,
  INDEX `FK_DEPTNO`(`DEPTNO`) USING BTREE,
  CONSTRAINT `FK_DEPTNO` FOREIGN KEY (`DEPTNO`) REFERENCES `dept` (`DEPTNO`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800.00, NULL, 20);
INSERT INTO `emp` VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.00, 300.00, 30);
INSERT INTO `emp` VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250.00, 500.00, 30);
INSERT INTO `emp` VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975.00, NULL, 20);
INSERT INTO `emp` VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250.00, 1400.00, 30);
INSERT INTO `emp` VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850.00, NULL, 30);
INSERT INTO `emp` VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450.00, NULL, 10);
INSERT INTO `emp` VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000.00, NULL, 10);
INSERT INTO `emp` VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500.00, 0.00, 30);
INSERT INTO `emp` VALUES (7876, 'ADAMS', 'CLERK', 7788, '1987-05-23', 1100.00, NULL, 20);
INSERT INTO `emp` VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950.00, NULL, 30);
INSERT INTO `emp` VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300.00, NULL, 10);

-- ----------------------------
-- Table structure for salgrade
-- ----------------------------
DROP TABLE IF EXISTS `salgrade`;
CREATE TABLE `salgrade`  (
  `GRADE` int(11) NOT NULL,
  `LOSAL` double(7, 2) NULL DEFAULT NULL,
  `HISAL` double(7, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`GRADE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salgrade
-- ----------------------------
INSERT INTO `salgrade` VALUES (1, 700.00, 1200.00);
INSERT INTO `salgrade` VALUES (2, 1201.00, 1400.00);
INSERT INTO `salgrade` VALUES (3, 1401.00, 2000.00);
INSERT INTO `salgrade` VALUES (4, 2001.00, 3000.00);
INSERT INTO `salgrade` VALUES (5, 3001.00, 9999.00);

DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects`  (
  `pid` int(2) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `money` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


INSERT INTO `projects` VALUES (1, ' ***大学OA', 500000);
INSERT INTO `projects` VALUES (2, '学生选课系统', 100000);
INSERT INTO `projects` VALUES (3, '讲师测评系统', 20000);
INSERT INTO `projects` VALUES (4, '线上问答系统 ', 20000);

DROP TABLE IF EXISTS `projectrecord`;
CREATE TABLE `projectrecord`  (
  `empno` int(4) NOT NULL,
  `pid` int(2) NOT NULL,
  PRIMARY KEY (`empno`, `pid`) USING BTREE,
  INDEX `fk_project_pro`(`pid`) USING BTREE,
  CONSTRAINT `fk_emp_pro` FOREIGN KEY (`empno`) REFERENCES `emp` (`EMPNO`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_project_pro` FOREIGN KEY (`pid`) REFERENCES `projects` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

INSERT INTO `projectrecord` VALUES (7369, 1);
INSERT INTO `projectrecord` VALUES (7521, 1);
INSERT INTO `projectrecord` VALUES (7369, 2);
INSERT INTO `projectrecord` VALUES (7499, 2);
INSERT INTO `projectrecord` VALUES (7521, 2);
INSERT INTO `projectrecord` VALUES (7369, 3);
INSERT INTO `projectrecord` VALUES (7499, 3);
INSERT INTO `projectrecord` VALUES (7521, 3);
INSERT INTO `projectrecord` VALUES (7369, 4);
INSERT INTO `projectrecord` VALUES (7499, 4);

SET FOREIGN_KEY_CHECKS = 1;

-- 修改empno为自动递增
ALTER TABLE emp MODIFY empno INT AUTO_INCREMENT;

-- 修改deptno为自动递增
ALTER TABLE dept MODIFY deptno INT AUTO_INCREMENT;
```

# Mybatis代理模式

通过SqlSession调用自身方法发送SQL命令并得到结果的方式存在如下缺点：

1. SqlSession的SelectList()、selectOne()、selectMap()等方法都只能提供一个查询参数，如果要多个参数，需要封装到JavaBean或者Map中；
2. 返回值类型比较固定
3. 只提供了映射文件，没有提供数据库操作的接口，不利于后期的维护扩展。

在MyBatis中提供了另外一种成为Mapper代理（或称为接口绑定）的操作方式，优点

有接口、模块之间有规范了；

参数的处理多样化了，接口中的方式参数列表由开发者自己决定；

# 动态SQL 

经常遇到很多按照很多查询条件进行查询的情况，如果采用JDBC进行处理，需要根据条件是否取值进行SQL语句的拼接，一般情况下是使用StringBuilder类及其append方法实现，还是比较繁琐，例如：拼接时要确保不能忘记添加必要的空格，还要注意去掉列表最后一列名的逗号。利用动态SQL这一特性可以彻底拜托这种痛苦：

MyBatis在简化操作方法提出了动态SQL功能，将使用Java代码拼接SQL语句，改变为在XML映射文件中截止标签拼接SQL语句。相比而言，大大减少了代码量，更灵活、高度可配置、利于后期维护。

MyBatis中动态SQL是编写在mapper.xml中的，其语法和JSTL类似，但是却是基于强大的OGNL表达式实现的。

MyBatis也可以在注解中配置SQL，但是由于注解功能受限，尤其对于复杂的SQL语句，可读性很差，所以较少使用。

set是trim标签的特例

# MyBatis实现多表查询

# 级联查询

# MyBatis注解开发

MyBatis编写SQL除了使用Mapper.xml还可以使用注解完成。当可以使用Auto Mapping时使用注解非常简单，不需要频繁的在接口和mapper.xml两个文件之间进行切换。但是必须配置resultMap时使用注解将会变得很麻烦，这种情况下推荐使用mapper.xml进行配置。

MyBatis支持纯注解方式，支持纯mapper.xml方式，也支持注解和mapper.xml混合形式。如果某个功能同时使用两种方式进行配置，XML方式将覆盖注解方式。当只有接口没有mapper.xml时在sqlMapConfig.xml中可以通过<mapper class=""></mapper>加载接口类。如果是混合使用时，使用<package name=""/>。此方式一直是官方推荐方式。

1. 使用注解没有实现Java代码和SQL语句的解耦
2. 无法实现SQL语句的动态拼接
3. 进行多表的查询时定制ResultMap比较麻烦

