package censusanalyser;

import java.util.Comparator;

public class SortByAreaStrategy implements IndianCensusData {

    @Override
    public Comparator IndiancensusFieldStrategy() {
       return Comparator.<IndiaCensusDAO, Integer>comparing(census -> census.areaInSqKm);
    }
}
