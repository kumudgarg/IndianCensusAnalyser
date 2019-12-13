package censusanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class USCensusAdapterPattern {
    private static final String US_CENSUS_EMPTY_FILE = "./src/test/resources/IndianCensusData.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";
    private static final String US_CENSUS_CSV_WRONG_DELIMITER = "/home/admin105/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/USCensusWrongDelimiter.csv";
    private static final String US_CENSUS_CSV_MISSING_Header="/home/admin105/Downloads/CensusAnalyser/CensusAnalyser/src/test/resources/USCensusMissingHeader.csv";

    @Test
    public void givenUSCensusData_ThroughCensusAdapter_ShouldReturnCorrectRecords() {
        CensusAdapter censusAdapter = new USCensusAdapter();
        try {
            Map<String, CensusDAO> censusDAOMap = censusAdapter.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, censusDAOMap.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongDelimiter_InUSCensusData_ShouldReturnCustomExceptionType() {
        CensusAdapter censusAdapter = new USCensusAdapter();
        try {
            Map<String, CensusDAO> censusDAOMap = censusAdapter.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_WRONG_DELIMITER);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenMissingHeader_InUSCensusData_ShouldReturnCustomExceptionType() {
        CensusAdapter censusAdapter = new USCensusAdapter();
        try {
            Map<String, CensusDAO> censusDAOMap = censusAdapter.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_MISSING_Header);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenEmptyCsvFile_ShouldReturnCustomExceptionType() {
        CensusAdapter censusAdapter = new IndiaCensusAdapter();
        try {
            Map<String, CensusDAO> censusDAOMap = censusAdapter.loadCensusData(CensusAnalyser.Country.INDIA, US_CENSUS_EMPTY_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }
}
