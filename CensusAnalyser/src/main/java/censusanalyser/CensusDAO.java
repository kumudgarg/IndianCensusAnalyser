package censusanalyser;

public class CensusDAO {
    public String state;
    public String stateCode;
    public int population;
    public double totalArea;
    public double populationDensity;

    public CensusDAO(IndiaCensusCSV next) {
        state = next.state;
        population = next.population;
        populationDensity = next.densityPerSqKm;
        totalArea = next.areaInSqKm;
    }

    public CensusDAO(USCensusCSV next) {
        state = next.State;
        population = next.Population;
        totalArea = next.totalArea;
        populationDensity = next.populationDensity;
        stateCode = next.StateId;
    }

    public Object getCensusDTO(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(state, stateCode, population, populationDensity, totalArea);
        return new IndiaCensusCSV(state, population, (int) populationDensity, (int) totalArea);
    }
}
