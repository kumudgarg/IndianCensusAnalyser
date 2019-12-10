package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder implements ICSVBuilder{
   public  <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
       return this.getCSVBean(reader,csvClass).iterator();
    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException {
       return this.getCSVBean(reader,csvClass).parse();

    }

    private <E> CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
