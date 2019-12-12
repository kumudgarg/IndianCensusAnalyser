package censusanalyser;

import java.util.Comparator;

public class SortByDensityStrategy implements IndianCensusData {
    @Override
    public Comparator IndiancensusFieldStrategy() {
        return Comparator.<IndiaCensusDAO, Integer>comparing(census -> census.densityPerSqKm);
    }
}
