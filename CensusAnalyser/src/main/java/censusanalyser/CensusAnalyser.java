package censusanalyser;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    Map<String, CensusDAO> csvFileMap;

    public CensusAnalyser() {
        this.csvFileMap = new HashMap<String, CensusDAO>();
    }


    public int loadIndiaCensusData(String... csvFilePath) throws CensusAnalyserException {
        csvFileMap = new CensusLoader().loadCensusData(IndiaCensusCSV.class);
        return csvFileMap.size();

    }

    public int loasUSCensusData(String... usCensusCsvFilePath) throws CensusAnalyserException {
        csvFileMap = new CensusLoader().loadCensusData(USCensusCSV.class);
        return csvFileMap.size();
    }



    public String getStateWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        if (csvFileMap == null || csvFileMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Map<String, Comparator<IndiaCensusDAO>> comparatorMap = new HashMap<String, Comparator<IndiaCensusDAO>>();
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        comparatorMap.put("state", Comparator.comparing(census -> census.state));
        List<CensusDAO> censusDAOS = csvFileMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }


    private void sort(List<CensusDAO> censusDAOS, Comparator<CensusDAO> censusComparator) {
        for (int i = 0; i < censusDAOS.size(); i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                CensusDAO census1 = censusDAOS.get(j);
                CensusDAO census2 = censusDAOS.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusDAOS.set(j, census2);
                    censusDAOS.set(j + 1, census1);
                }

            }

        }
    }
}
