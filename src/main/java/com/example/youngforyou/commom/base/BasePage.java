package com.example.youngforyou.commom.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BasePage<T> extends Page<T> {
    private static final long serialVersionUID = 5194933845448697148L;

    public BasePage() {
        super();
    }

    public BasePage(long current, long size) {
        super(current, size);
    }
}