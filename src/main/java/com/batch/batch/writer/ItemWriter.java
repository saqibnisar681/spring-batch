package com.batch.batch.writer;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemWriter implements org.springframework.batch.item.ItemWriter<Long> {
    @Override
    public void write(List<? extends Long> list) throws Exception {
        System.out.println(list);
    }
}
