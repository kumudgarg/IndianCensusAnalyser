package censusanalyser;

import com.google.gson.Gson;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    private Country country;

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    enum Country {INDIA, US}

    Map<String, CensusDAO> censusStateMap;

    public CensusAnalyser() {
        this.censusStateMap = new HashMap<String, CensusDAO>();
    }

    public int loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        censusStateMap = new CensusAdapterFactory().censusFactory(country, csvFilePath);
        return censusStateMap.size();
    }

    public String getFieldWiseSortedCensusData(SortByField.Parameter... parameter) throws CensusAnalyserException {
        Comparator<CensusDAO> censusComparator = null;
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        if (parameter.length == 2)
            censusComparator = SortByField.getParameter(parameter[0]).thenComparing(SortByField.getParameter(parameter[1]));
        censusComparator = SortByField.getParameter(parameter[0]);
        ArrayList censusDTOS = censusStateMap.values().stream().
                sorted(censusComparator).
                map(censusDAO -> censusDAO.getCensusDTO(country)).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(censusDTOS);
        return sortedStateCensusJson;
    }
}

