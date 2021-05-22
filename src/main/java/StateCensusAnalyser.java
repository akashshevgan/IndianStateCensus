import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

    private int count;

    public int loadCensusData(String path) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader).withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true).build();
            Iterator<CSVStateCensus> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                count++;
                csvUserIterator.next();
            }
        } catch (RuntimeException exception) {
            throw new CensusAnalyserException(CensusAnalyserException.exceptionType.WRONG_FILE,
                    "might be some error related to delimiter");
        } catch (NoSuchFileException exception) {
            throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
                    "no such file exists. ");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return count;
    }
}