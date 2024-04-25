import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeathCauseStatisticList
{
    List<DeathCauseStatistic>stats;

    public void repopulate(String path)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            List<String>lines =new ArrayList<>();
            String line;
            while((line=br.readLine())!=null)
            {
                lines.add(line);
            }
            stats = lines
                    .stream()
                    .skip(2)
                    .map(DeathCauseStatistic::fromCsvLine)
                    .toList();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<DeathCauseStatistic> getStats() {
        return stats;
    }
    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n)
    {
        stats = stats
                .stream()
                .sorted(Comparator.comparing(p -> p.getDeathsForAge(age).deathCount))
                .collect(Collectors.toList());

        return stats.reversed().subList(0,n);
    }
}
