package com.example.youngforyou.entity.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Administrator
 * @since 2020-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MbTable extends Model<MbTable> {

    private static final long serialVersionUID = 1L;


    private String qj;

    private String khqc;

    private String ph;

    private String pm;

    private String gg;

    private String ccdw;

    private String bz;

    private Integer jl;

    private BigDecimal xhje;

    private BigDecimal jse;

    private BigDecimal xhze;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
