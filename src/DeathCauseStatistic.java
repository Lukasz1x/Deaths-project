import java.util.Arrays;

public class DeathCauseStatistic
{
    private String ICD_10;
    private int[] deaths;

    public DeathCauseStatistic(String ICD_10, int[] deaths)
    {
        this.ICD_10=ICD_10;
        this.deaths=deaths;
    }

    public String getICD_10() {
        return ICD_10;
    }

    @Override
    public String toString() {
        return "DeathCauseStatistic{" +
                "ICD_10='" + ICD_10 + '\'' +
                ", deaths=" + Arrays.toString(deaths) +
                '}';
    }

    public static DeathCauseStatistic fromCsvLine(String Line)
    {
        String[] parts = Line.split(",", -1);
        String ICD = parts[0].trim();
        int[] deaths = Arrays
                .stream(parts)
                .skip(2)
                .mapToInt(p -> p.equals("-") ? 0 : Integer.parseInt(p))
                .toArray();
        return new DeathCauseStatistic(ICD, deaths);
    }

    public class AgeBracketDeaths
    {
        public final int young;
        public final int old;
        public final int deathCount;

        public AgeBracketDeaths(int young, int old, int deathCount) {
            this.young = young;
            this.old = old;
            this.deathCount = deathCount;
        }

        @Override
        public String toString() {
            return "AgeBracketDeaths{" +
                    "young=" + young +
                    ", old=" + old +
                    ", deathCount=" + deathCount +
                    '}';
        }
    }

    public AgeBracketDeaths getDeathsForAge(int age)
    {
        int index = age>=100 ? deaths.length-1 : age/5;
        return new AgeBracketDeaths(index*5, index*5 == deaths.length-1 ? null : index*5+4, deaths[index]);
    }
}
