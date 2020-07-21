package com.train.company.interfaces.console.in.mapping.processing;

import com.train.company.domaine.city.Station;
import com.train.company.domaine.day.Tap;
import com.train.company.domaine.travel.Journey;
import com.train.company.interfaces.console.in.exception.ReadingFileException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonFileReaderAndConverterTest {

    @Test
    void should_not_accept_null_input_file() {
        File file = null;
        JsonFileReaderAndConverter jsonFileReaderAndConverter = new JsonFileReaderAndConverter();
        Throwable exception = assertThrows(ReadingFileException.class, () -> jsonFileReaderAndConverter.convert(file));
        assertEquals("the file must be not null.", exception.getMessage());
    }
    @Test
    void should_not_accept_empty_file() throws IOException {
        File temp = Files.createTempFile("name", "").toFile();
        JsonFileReaderAndConverter jsonFileReaderAndConverter = new JsonFileReaderAndConverter();
        Throwable exception = assertThrows(ReadingFileException.class, () -> jsonFileReaderAndConverter.convert(temp));
        assertEquals("the file must be not empty", exception.getMessage());
    }

    @Test
    void should_convert_file_to_object() throws IOException, ReadingFileException {

        JsonFileReaderAndConverter jsonFileReaderAndConverter = new JsonFileReaderAndConverter();

        Journey expected = new Journey();
        Tap tap1 = new Tap();
        tap1.setCustomerId(1);
        tap1.setUnixTimestamp(1572242400L);
        tap1.setStation(Station.A);

        Tap tap2 = new Tap();
        tap2.setCustomerId(1);
        tap2.setUnixTimestamp(1572244200L);
        tap2.setStation(Station.D);
        List<Tap> taps = new ArrayList<>() ;
        taps.add(tap1);
        taps.add(tap2);
        expected.setTaps(taps);


        File temp = Files.createTempFile("name", "").toFile();
        String journeyData = "{" +
                "\"taps\":" +
                "[" +
                "{" +
                "\"unixTimestamp\": 1572242400," +
                "\"customerId\": 1," +
                "\"station\": \"A\"" +
                "}," +
                "{" +
                "\"unixTimestamp\": 1572244200," +
                "\"customerId\": 1," +
                "\"station\": \"D\"" +
                "}" +
                "]" +
                "}";

        FileWriter fw = new FileWriter(temp);
        fw.write(journeyData);
        fw.close();
        Journey journey = jsonFileReaderAndConverter.convert(temp);
        assertEquals(expected,journey);

    }
    @Test
    void should_convert_loaded_file_to_object() throws ReadingFileException {

        JsonFileReaderAndConverter jsonFileReaderAndConverter = new JsonFileReaderAndConverter();
        ClassLoader classLoader = getClass().getClassLoader();
        File input = new File(requireNonNull(classLoader.getResource("A_D_D_A_input.txt")).getFile());
        Journey expected = getJourney();
        Journey journey = jsonFileReaderAndConverter.convert(input);
        assertEquals(expected,journey);

    }

    private Journey getJourney() {
        return Journey.newJourney()
                .taps(List.of(
                        Tap.newTap()
                                .unixTimestamp(1572242400L)
                                .station(Station.A)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(1572244200L)
                                .station(Station.D)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(1572282000L)
                                .station(Station.D)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(1572283800L)
                                .station(Station.A)
                                .customerId(1)
                                .build()
                ))
                .build();
    }
}