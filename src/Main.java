//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        DeathCauseStatistic dcs=DeathCauseStatistic.fromCsvLine("A15.0          ,197,-,-,-,-,-,2,4,5,13,19,21,24,33,29,18,5,15,5,4,-");
        for(int i=0; i<=100; i+=5)
        {
            System.out.println(dcs.getDeathsForAge(i) + "\n");
        }

    }
}