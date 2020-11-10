package com.dukoia.microservice.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
@author Dukoia
@createTime 2020/11/3 11:08
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "payment")
public class Payment {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "serial")
    private String serial;

    public static final String COL_ID = "id";

    public static final String COL_SERIAL = "serial";
}