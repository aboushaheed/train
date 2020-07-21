package com.train.company.interfaces.console.out.mapping.processing;

import com.train.company.domaine.city.Station;
import com.train.company.domaine.day.CustomerSummary;
import com.train.company.domaine.day.Trip;
import com.train.company.domaine.travel.Summary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

class JsonFileWriterTest {
    @TempDir
    File temp;

    @Test
    void should_write_a_file_in_specified_path() throws IOException {
        JsonFileWriter jsonFileWriter = new JsonFileWriter();
        File json = new File(temp, "should_write_a_file_in_specified_path.txt");
        Summary summary = getSummary();
        jsonFileWriter.write(summary,json.toPath().toString());
        assertTrue(json.exists());
    }
    @Test
    void should_write_non_empty_file_in_specified_path() throws IOException {
        JsonFileWriter jsonFileWriter = new JsonFileWriter();
        File json = new File(temp, "should_write_non_empty_file_in_specified_path.txt");
        Summary summary = getSummary();
        jsonFileWriter.write(summary,json.toPath().toString());
        assertTrue(json.length() != 0);
    }

    @Test
    void should_write_summary_as_json_content() {
        JsonFileWriter jsonFileWriter = new JsonFileWriter();
        File json = new File(temp, "should_write_summary_as_json_content.txt");
        Summary summary = getSummary();

        ClassLoader classLoader = getClass().getClassLoader();
        File expected = new File(requireNonNull(classLoader.getResource("A_D_D_A_output.txt")).getFile());
        assertDoesNotThrow(()-> jsonFileWriter.write(summary,json.toPath().toString()));
        assertEquals(expected.length(), json.length());

    }

    private Summary getSummary() {
        return Summary.newSummary()
                    .customerSummaries(List.of(
                            CustomerSummary.newCustomerSummary()
                                    .customerId(1)
                                    .totalCostInCents(480)
                                    .trips(List.of(
                                            Trip.newTrip()
                                                    .stationStart(Station.A)
                                                    .stationEnd(Station.D)
                                                    .startedJourneyAt(1572242400L)
                                                    .costInCents(240)
                                                    .zoneFrom(1)
                                                    .zoneTo(2)
                                                    .build(),
                                            Trip.newTrip()
                                                    .stationStart(Station.D)
                                                    .stationEnd(Station.A)
                                                    .startedJourneyAt(1572282000L)
                                                    .costInCents(240)
                                                    .zoneFrom(2)
                                                    .zoneTo(1)
                                                    .build()
                                    ))
                                    .build()
                    ))
                    .build();
    }

}