package com.batch.batch.processor;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ItemProcessor implements org.springframework.batch.item.ItemProcessor<Long, Long> {

    @Override
    public Long process(Long item) throws Exception {
        if (Objects.nonNull(item))
            return Math.multiplyExact(item, 2);
        else
            return null;
    }
}
