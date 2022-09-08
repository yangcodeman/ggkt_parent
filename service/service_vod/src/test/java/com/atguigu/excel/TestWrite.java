package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestWrite {

    public static void main(String[] args) {

        String fileName = "/Users/yang/Desktop/ggkt/atguigu.xls";

        EasyExcel.write(fileName,User.class)
                .sheet("写操作测试")
                .doWrite(data());
    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<User> data() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setId(i);
            data.setName("Lcuy"+i);
            list.add(data);
        }
        return list;
    }
}
