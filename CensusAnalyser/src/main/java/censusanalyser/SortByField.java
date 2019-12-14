package censusanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByField {

     static Map<SortByField.Parameter, Comparator> sortParameterComparator = new HashMap<>();

    enum Parameter {
        STATE, POPULATION, AREA, DENSITY;
    }

    SortByField() {

    }
    public static Comparator getParameter(SortByField.Parameter parameter) {

        Comparator<CensusDAO> stateComparator = Comparator.comparing(census -> census.state);
        Comparator<CensusDAO> areaComparator = Comparator.comparing(census -> census.totalArea);
        Comparator<CensusDAO> populationComparator = Comparator.comparing(census -> census.population);
        Comparator<CensusDAO> densityComparator = Comparator.comparing(census -> census.populationDensity);

        sortParameterComparator.put(Parameter.STATE, stateComparator);
        sortParameterComparator.put(Parameter.POPULATION, populationComparator);
        sortParameterComparator.put(Parameter.AREA,areaComparator);
        sortParameterComparator.put(Parameter.DENSITY, densityComparator);


        Comparator<CensusDAO> comparator = sortParameterComparator.get(parameter);

        return comparator;
    }
}
