package censusanalyser;

import java.util.Comparator;

public class SortByStateStrategy implements IndianCensusData {
    @Override
    public Comparator censusFieldStrategy() {
        Comparator.<IndiaCensusDAO, String>comparing(census -> census.state);
        return Comparator.reverseOrder();
    }
}
