package com.train.company.interfaces.console.in.mapping.processing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.train.company.domaine.travel.Journey;
import com.train.company.interfaces.console.in.exception.ReadingFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class JsonFileReaderAndConverter {

    private static final int EMPTY = 0;
    private static Logger log = LoggerFactory.getLogger(JsonFileReaderAndConverter.class);

    public Journey convert(File input){

        if (input == null) throw new ReadingFileException("the file must be not null.");
        if (input.length() == EMPTY) throw new ReadingFileException("the file must be not empty");
        Journey journey;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        try {
            journey = objectMapper.readValue(input, Journey.class);
        } catch (IOException e) {
            log.error("error on reading file data",e);
            throw new ReadingFileException("error on reading file data");
        }
        return journey;
    }
}
