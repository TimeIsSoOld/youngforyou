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
 * 文件信息表
 * </p>
 *
 * @author Administrator
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SYS_UPLOAD_FILE")
public class SysUploadFile extends Model<SysUploadFile> {

    private static final long serialVersionUID = 1L;

    /**
     * 文件编号
     */
    @TableId("ID")
    private String id;

    /**
     * 文件编号
     */
    @TableField("OPERATE_TIME")
    private String operateTime;

    /**
     * 上传ip
     */
    @TableField("IP_ADDRESS")
    private String ipAddress;

    /**
     * 源文件名称
     */
    @TableField("FILE_NAME")
    private String fileName;

    /**
     * 文件大小
     */
    @TableField("FILE_SIZE")
    private String fileSize;

    /**
     * 是否上传成功
     */
    @TableField("ISUPLOAD")
    private String isupload;

    /**
     * 现文件名
     */
    @TableField("CURFILENAME")
    private String curfilename;

    /**
     * FTP存储路径
     */
    @TableField("LOCALPATH")
    private String localpath;

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
        return this.id;
    }

}
