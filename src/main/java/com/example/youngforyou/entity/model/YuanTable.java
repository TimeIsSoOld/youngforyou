package com.example.youngforyou.entity.model;

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
public class YuanTable extends Model<YuanTable> {

    private static final long serialVersionUID = 1L;

    private String dddh;

    private String jzrq;

    private String lydh;

    private String ywbm;

    private String wyry;

    private String khqc;

    private String sphm;

    private String ph;

    private String pm;

    private String gg;

    private String sl;

    private String dj;

    private String wsje;

    private String se;

    private String jshj;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
