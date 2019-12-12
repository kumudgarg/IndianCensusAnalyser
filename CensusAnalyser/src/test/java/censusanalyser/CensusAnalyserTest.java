package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIAN_CSV_STATE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCensusDataWrongDelimiter.csv";
    private static final String INDIAN_CENSUS_CSV_MISSING = "./src/test/resources/IndiaStateCensusDataMissingHeader.csv";
    private static final String INDIAN_CENSUS_EMPTY_FILE = "./src/test/resources/IndianCensusData.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongDelimiter_InIndiaCensusData_ShouldReturnCustomExceptionType() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIAN_CENSUS_CSV_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenMissingHeader_InIndiaCensusData_ShouldReturnCustomExceptionType() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIAN_CENSUS_CSV_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenEmptyCsvFile_ShouldReturnCustomExceptionType() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIAN_CENSUS_CSV_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnStateWrong_ShouldReturnWrongSortedResult() {
        String sortedCensusData = null;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertNotEquals("Goa", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        String sortedCensusData = null;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_ShouldReturnCorrectRecords() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int data = 0;
        try {
            data = censusAnalyser.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, data);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_ThroughCensusAdapter_ShouldReturnCorrectRecords() {
        CensusAdapter censusAdapter = new USCensusAdapter();
        try {
            Map<String, CensusDAO> censusDAOMap = censusAdapter.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51,censusDAOMap.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_ThroughCensusAdapter_ShouldReturnCorrectRecords() {
        CensusAdapter censusAdapter = new IndiaCensusAdapter();
        try {
            Map<String, CensusDAO> censusDAOMap = censusAdapter.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIAN_CSV_STATE_PATH);
            Assert.assertEquals(29,censusDAOMap.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
