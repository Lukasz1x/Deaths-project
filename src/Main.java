import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        DeathCauseStatisticList list = new DeathCauseStatisticList();
        list.repopulate("zgony.csv");
//        for(var dsc : list.getStats())
//        {
//            System.out.println(dsc.toString());
//        }

        List<DeathCauseStatistic> dcslist = list.mostDeadlyDiseases(20,10);
        for(var l : dcslist)
        {
            System.out.println(l.toString());
        }

        var codes = new ICDCodeTabularOptimizedForMemory("icd10.txt");

        list.mostDeadlyDiseases(90, 1000).forEach(currentStat -> {
            String code = currentStat.getICD_10();
            try {
                System.out.println(
                        code + ": " + codes.getDescription(code)
                );
            }catch (IndexOutOfBoundsException e)
            {
                System.err.println(code + ": " + e.getMessage());
            }
        });
    }
}