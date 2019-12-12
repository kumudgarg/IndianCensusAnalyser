/*
package censusanalyser;

public class SortCensusTitleStrategy {

    static Map<SortParameter.Parameter, Comparator> sortParameterComparator = new HashMap<>();

    enum Parameter {
        STATE, POPULATION, AREA, DENSITY;
    }

    SortParameter() {

    }
    public static Comparator getParameter(SortParameter.Parameter parameter) {

        Comparator<IndiaCensusDAO> stateComparator = Comparator.comparing(census -> census.state);
        Comparator<IndiaCensusDAO> areaComparator = Comparator.comparing(census -> census.areaInSqKm);
        Comparator<IndiaCensusDAO> populationComparator = Comparator.comparing(census -> census.population);
        Comparator<IndiaCensusDAO> densityComparator = Comparator.comparing(census -> census.densityPerSqKm);

        sortParameterComparator.put(Parameter.STATE, stateComparator);
        sortParameterComparator.put(Parameter.POPULATION, areaComparator);
        sortParameterComparator.put(Parameter.AREA, populationComparator);
        sortParameterComparator.put(Parameter.DENSITY, densityComparator);


        Comparator<IndiaCensusDAO> comparator = sortParameterComparator.get(parameter);

        return comparator;
    }
}
*/
