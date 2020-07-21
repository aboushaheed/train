package com.train.company;

import com.train.company.billing.Biller;
import com.train.company.domaine.travel.Journey;
import com.train.company.domaine.travel.Summary;
import com.train.company.interfaces.console.in.mapping.processing.JsonFileReaderAndConverter;
import com.train.company.interfaces.console.out.mapping.processing.JsonFileWriter;

import java.io.File;
import java.io.IOException;

public class TrainCompanyApplication {

    public static void main( String[] args ) throws IOException {
        if (args == null) {
            System.out.println("You need to specify arguments");
        }else if (args[0] == null || args[0].trim().isEmpty()) {
            System.out.println("You need to specify a path for the input file!");
        }else if ( args.length < 2|| args[1] == null || args[1].trim().isEmpty()) {
            System.out.println("You need to specify a path for the output file!");
        } else {
            File inputFile = new File(args[0]);
            JsonFileReaderAndConverter jsonFileReaderAndConverter = new JsonFileReaderAndConverter();
            Journey journey = jsonFileReaderAndConverter.convert(inputFile);
            Biller biller = new Biller();
            Summary summary = biller.bill(journey);
            JsonFileWriter jsonFileWriter = new JsonFileWriter();
            jsonFileWriter.write(summary,args[1]);
            System.out.println("You can view results now in : " + args[1]);
        }
    }

}
