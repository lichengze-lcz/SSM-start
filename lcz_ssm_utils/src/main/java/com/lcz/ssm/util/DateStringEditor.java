package com.lcz.ssm.util;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.util.Date;

//用于 字符串 转换  成data对象
public class DateStringEditor extends PropertiesEditor {

    //重写类中的方法
    @Override
    public void setAsText(@Nullable String text) throws IllegalArgumentException {

        //把数据重新转换成Date对象
        try {
            Date date = DateUtils.string2Date(text, "yyyy-MM-dd HH:mm");

            super.setValue(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
