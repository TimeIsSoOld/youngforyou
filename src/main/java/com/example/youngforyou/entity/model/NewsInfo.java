package com.example.youngforyou.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author Administrator
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("NEWS_INFO")
public class NewsInfo extends Model<NewsInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String pKey;

    /**
     * 用户id
     */
    private String uKey;

    /**
     * 标题
     */
    private String newTitle;

    /**
     * 摘要
     */
    private String summaryInfo;

    /**
     * 内容
     */
    private Blob newsCont;

    /**
     * 封面 SYS_UPLOAD_FILE.p_key
     */
    private String coverImg;

    /**
     * 发布时间
     */
    private String publishTiime;

    /**
     * 所属区划
     */
    private String qhdm;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人base_user.p_key
     */
    private String createBy;

    /**
     * 删除状态:0-已删除，1-未删除
     */
    private Integer delStatus;


    @Override
    protected Serializable pkVal() {
        return this.pKey;
    }

}
