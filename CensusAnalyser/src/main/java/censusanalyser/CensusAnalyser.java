package censusanalyser;

import com.google.gson.Gson;

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

    public String getFieldWiseSortedCensusData(SortByField.Parameter parameter) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        SortByField sortByField = new SortByField();
        Comparator<CensusDAO> censusComparator = sortByField.getParameter(parameter);
        ArrayList censusDTOS = censusStateMap.values().stream().
                sorted(censusComparator).
                map(censusDAO -> censusDAO.getCensusDTO(country)).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(censusDTOS);
        return sortedStateCensusJson;
    }

    public String censusData(SortByField.Parameter parameter, SortByField.Parameter density) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        SortByField sortByField = new SortByField();
        Comparator<CensusDAO> censusComparator = sortByField.getParameter(parameter).thenComparing(sortByField.getParameter(density));
        ArrayList censusDTOS = censusStateMap.values().stream().
                sorted(censusComparator).
                map(censusDAO -> censusDAO.getCensusDTO(country)).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(censusDTOS);
        return sortedStateCensusJson;
    }

    private String getFiledWiseUniqueCensusData(List<CensusDAO> censusDAOSData) throws CensusAnalyserException {
        for (int i = 0; i < censusDAOSData.size(); i++) {
            for (int j = i + 1; j < censusDAOSData.size(); j++) {
                if ((censusDAOSData.get(i).population) == (censusDAOSData.get(j).population)) {
                    if ((censusDAOSData.get(i).populationDensity) < (censusDAOSData.get(i + 1).populationDensity)) {
                        CensusDAO censusDAOTemp = censusDAOSData.get(i);
                        censusDAOSData.add(i, censusDAOSData.get(i + 1));
                        censusDAOSData.add(i + 1, censusDAOTemp);
                    }

                }

            }
        }
        String sortedStateCensusJson = new Gson().toJson( censusDAOSData);
        return sortedStateCensusJson;
    }
}

