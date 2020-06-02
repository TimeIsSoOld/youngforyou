package com.example.youngforyou.entity.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 2020年1至4月应收单明细表
 * </p>
 *
 * @author Administrator
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("INCOMEDETAIL_TABLE")
public class IncomedetailTable extends Model<IncomedetailTable> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("P_KEY")
    private String pKey;

    /**
     * 订单单号
     */
    @TableField("DDDH")
    private String dddh;

    /**
     * 记账日期
     */
    @TableField("JZRQ")
    private String jzrq;

    /**
     * 来源单号
     */
    @TableField("LYDH")
    private String lydh;

    /**
     * 业务部门
     */
    @TableField("YWBM")
    private String ywbm;

    /**
     * 业务人员
     */
    @TableField("YWRY")
    private String ywry;

    /**
     * 客户全称
     */
    @TableField("KHQC")
    private String khqc;

    /**
     * 税票号码
     */
    @TableField("SPHM")
    private String sphm;

    /**
     * 品号
     */
    @TableField("PH")
    private String ph;

    /**
     * 品名
     */
    @TableField("PM")
    private String pm;

    /**
     * 规格
     */
    @TableField("GG")
    private String gg;

    /**
     * 计价数量
     */
    @TableField("JJSL")
    private Integer jjsl;

    /**
     * 折扣后无税单价
     */
    @TableField("DJ")
    private BigDecimal dj;

    /**
     * 无税金额(本币)
     */
    @TableField("WSJE")
    private BigDecimal wsje;

    /**
     * 税额(本币)
     */
    @TableField("SE")
    private BigDecimal se;

    /**
     * 价税合计(本币)
     */
    @TableField("HJ")
    private BigDecimal hj;


    @Override
    protected Serializable pkVal() {
        return this.pKey;
    }

}
