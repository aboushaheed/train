package com.train.company.interfaces.console.out.mapping.processing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.train.company.domaine.travel.Summary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileWriter {

    public void write(Summary summary, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(summary);
        Files.write(Paths.get(path), json.getBytes());
    }
}
