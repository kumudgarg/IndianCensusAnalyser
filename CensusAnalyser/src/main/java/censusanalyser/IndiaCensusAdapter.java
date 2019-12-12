package censusanalyser;

import csvbulider_jar.CSVBuilderFactory;
import csvbulider_jar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {
    Map<String, CensusDAO> censusDAOMap = new HashMap<String, CensusDAO>();

    @Override
    public Map<String, CensusDAO> loadCensusData(CensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException, CensusAnalyserException {
        Map<String, CensusDAO> censusStateMap = super.loadCensusData(IndiaCensusCSV.class, csvFilePath[0]);
        this.loadIndiaStateCode(censusStateMap, csvFilePath[1]);
        return censusStateMap;
    }

    public int loadIndiaStateCode(Map<String, CensusDAO> censusDAOMap, String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> csvStateCode = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> stateCodeCSVS = () -> csvStateCode;
            StreamSupport.stream(stateCodeCSVS.spliterator(), false)
                    .filter(csvState -> this.censusDAOMap.get(csvState.stateName) != null)
                    .forEach(csvState -> this.censusDAOMap.get(csvState.stateName).stateCode = csvState.stateCode);
            return this.censusDAOMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException | csvbulider_jar.CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }
    }
    }
