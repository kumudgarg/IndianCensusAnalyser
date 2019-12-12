package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "State Id", required = true)
    public String StateId;

    @CsvBindByName(column = "Population", required = true)
    public int Population;

    @CsvBindByName(column = "Total area", required = true)
    public Double totalArea;

    @CsvBindByName(column = "Population Density", required = true)
    public Double populationDensity;



}
