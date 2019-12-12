package censusanalyser;

import java.util.Comparator;

public class SortByStateStrategy implements IndianCensusData {
    @Override
    public Comparator IndiancensusFieldStrategy() {
        Comparator.<IndiaCensusDAO, String>comparing(census -> census.state);
        return Comparator.reverseOrder();
    }
}
