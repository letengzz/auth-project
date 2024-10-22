package com.hjc.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hjc
 */
@Data
public class SysUserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String keyword;
    private String createTimeBegin;
    private String createTimeEnd;
}
