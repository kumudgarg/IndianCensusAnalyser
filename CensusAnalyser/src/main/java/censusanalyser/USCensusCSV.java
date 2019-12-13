package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "State Id", required = true)
    public String StateId;

    @CsvBindByName(column = "Population", required = true)
    public int Population;

    @CsvBindByName(column = "Population Density", required = true)
    public Double populationDensity;

    @CsvBindByName(column = "Total area", required = true)
    public Double totalArea;

    public USCensusCSV() {
    }

    public USCensusCSV(String state, String stateId, int population, Double populationDensity, Double totalArea) {
        State = state;
        StateId = stateId;
        Population = population;
        this.populationDensity = populationDensity;
        this.totalArea = totalArea;
    }
}
