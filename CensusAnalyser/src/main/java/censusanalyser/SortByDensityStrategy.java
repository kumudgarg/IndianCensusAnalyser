package censusanalyser;

import java.util.Comparator;

public class SortByDensityStrategy implements IndianCensusData {
    @Override
    public Comparator censusFieldStrategy() {
        return Comparator.<IndiaCensusDAO, Integer>comparing(census -> census.densityPerSqKm);
    }
}
