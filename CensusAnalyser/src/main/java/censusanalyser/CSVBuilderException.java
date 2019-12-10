package censusanalyser;

public class CSVBuilderException extends Throwable {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE
    }

    ExceptionType type;

    public CSVBuilderException(String message,ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CSVBuilderException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
