package com.batch.batch.reader;


import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemReader implements org.springframework.batch.item.ItemReader<Long> {

    List<Long> data = List.of(1L,2L,3L, 4L, 5L);
    int i;

    @Override
    public Long read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (i < data.size())
            return data.get(i++);
        else {
            i = 0;
            return null;
        }

    }
}
