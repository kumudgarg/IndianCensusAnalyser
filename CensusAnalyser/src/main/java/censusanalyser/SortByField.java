package censusanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByField {

     Map<SortByField.Parameter, Comparator> sortParameterComparator = new HashMap<>();

    enum Parameter {
        STATE, POPULATION, AREA, DENSITY;
    }

    SortByField() {

    }
    public  Comparator getParameter(SortByField.Parameter parameter) {

        Comparator<CensusDAO> stateComparator = Comparator.comparing(census -> census.state);
        Comparator<CensusDAO> areaComparator = Comparator.comparing(census -> census.totalArea);
        Comparator<CensusDAO> populationComparator = Comparator.comparing(census -> census.population);
        Comparator<CensusDAO> densityComparator = Comparator.comparing(census -> census.populationDensity);

        this.sortParameterComparator.put(Parameter.STATE, stateComparator);
        this.sortParameterComparator.put(Parameter.POPULATION, populationComparator);
        this.sortParameterComparator.put(Parameter.AREA,areaComparator);
        this.sortParameterComparator.put(Parameter.DENSITY, densityComparator);


        Comparator<CensusDAO> comparator = this.sortParameterComparator.get(parameter);

        return comparator;
    }
}
