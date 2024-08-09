package org.duo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {

    // java是通过反射将pojo和数据库的字段名进行映射的，所以pojo的成员变量名称必须和数据库保持一致
    private Integer deptno;
    private String dname;
    private String loc;
}
