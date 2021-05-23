import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class StateCensusAnalyserTest {
    public static final String STATECENSUS_CSVFILE = "D:\\IndianStateCensus\\src\\test\\resources\\IndiaStateCensusData.csv";
    public static final String WRONG_FILE_PATH = "D:\\IndianStateCensus\\src\\test\\resources\\IndiaStateCensu.csv";

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
            Assertions.assertEquals("no such file exists.", e.getMessage());
        }
    }
}
