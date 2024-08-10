package org.duo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {

    // java是通过反射将pojo和数据库的字段名进行映射的，所以pojo的成员变量名称必须和数据库保持一致
    private Integer deptno;
    private String dname;
    private String loc;
    // 组合一个Emp的List集合作为属性
    private List<Emp> empList;

    public Dept(Integer deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }
}
