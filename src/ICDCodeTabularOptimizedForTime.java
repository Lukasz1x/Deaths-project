import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ICDCodeTabularOptimizedForTime implements ICDCodeTabular
{
    String path;
    Map<String, String> codeDescription  = new HashMap<>();
    public ICDCodeTabularOptimizedForTime(String path)
    {
        this.path = path;
        try {
            Stream<String>lines = Files.lines(Path.of(path));
            lines.skip(88).map(String::trim).filter(s -> s.matches("[A-Z][0-9]{2}.*")).map(s -> s.split(" ",2)).forEach(strings -> codeDescription.put(strings[0], strings[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public String getDescription(String ICD) throws IndexOutOfBoundsException
    {
        if (!codeDescription.containsKey(ICD)) {
            throw new IndexOutOfBoundsException();
        }
        return codeDescription.get(ICD);
    }
}
