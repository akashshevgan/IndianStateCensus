import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class StateCensusAnalyserTest {
    public static final String STATECENSUS_CSVFILE = "D:\\IndianStateCensus\\src\\test\\resources\\IndiaStateCensusData.csv";
    public static final String WRONG_FILE_PATH = "D:\\IndianStateCensus\\src\\test\\resources\\IndiaStateCensu.csv";
    public static final String WRONG_FILE_TYPE = "D:\\IndianStateCensus\\src\\test\\resources\\IndiaStateCensusData.txt";
    public static final String WRONG_DELIMITER = "D:\\IndianStateCensus\\src\\test\\resources\\IndiaStateCensusData.csv";

    @Test
    public void GivenTheStateCodesCsvFile_IfHasCorrectNumberOfRecords_ShouldReturnTrue() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            int count = stateCensusAnalyser.loadCensusData(STATECENSUS_CSVFILE);
            System.out.println(count);
            Assertions.assertEquals(29, count);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GivenTheStateCensusCsvFile_IfDoesntExist_ShouldThrowCensusAnalyserException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            int count = stateCensusAnalyser.loadCensusData(WRONG_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals("file is not found", e.getMessage());
        }
    }

    @Test
    public void givenWrongTypeFile_CheckCsvOrNot_ShouldReturnCustomException() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(WRONG_FILE_TYPE);
        } catch (CensusAnalyserException exception) {
            Assertions.assertEquals("file is not found", exception.getMessage());
        }
    }

    @Test
    public void givenDelimiterErrorStateCsvFile_CheckPresentOrNot_ShouldReturnCustomException() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadCensusData(WRONG_DELIMITER);
        } catch (CensusAnalyserException exception) {
            Assertions.assertEquals("delimiter is improper", exception.getMessage());
        }
    }
}
