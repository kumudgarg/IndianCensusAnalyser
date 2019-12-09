package censusanalyser;

public class IndiaCensusDAO {
    public String state;
    public int densityPerSqKm;
    public int areaInSqKm;
    public int population;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        population = indiaCensusCSV.population;
        state = indiaCensusCSV.state;
    }
}
