package atqh.nx2022.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User {
    /**
     * 
     */
    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 姓名
     */
    private String username;

    /**
     *专业年级
     */
    private String majors;

    /**
     * 性别
     */
    private String sex;

    /**
     * 获奖经历
     */
    private String awards;

    /**
     * 个人简历
     */
    private String text;

    /**
     * 平均绩点
     */
    private Double gpa;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 为什么想加入我们
     */
    private String why;

    /**
     * 意向部门
     */
    private String department;

    /**
     *邮箱
     */
    private String email;

    /**
     * 照片
     */
    @TableField(exist = false)
    private String pic;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}