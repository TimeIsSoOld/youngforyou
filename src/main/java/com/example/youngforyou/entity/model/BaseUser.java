package com.example.youngforyou.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号信息表
 * </p>
 *
 * @author Administrator
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("BASE_USER")
public class BaseUser extends Model<BaseUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("P_KEY")
    private String pKey;

    /**
     * 账号(电话)
     */
    @TableField("U_ACCOUNT")
    private String uAccount;

    /**
     * 密码
     */
    @TableField("U_PASSWORD")
    private String uPassword;

    /**
     * 昵称
     */
    @TableField("U_NAME")
    private String uName;

    /**
     * 电话
     */
    @TableField("U_PHONE")
    private String uPhone;

    /**
     * 头像SYS_UPLOAD_FILE.id
     */
    @TableField("LOGO")
    private String logo;

    /**
     * 状态：0-无效，1-有效
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 创建人base_user.p_key
     */
    @TableField("CREATE_BY")
    private String createBy;


    @Override
    protected Serializable pkVal() {
        return this.pKey;
    }

}
