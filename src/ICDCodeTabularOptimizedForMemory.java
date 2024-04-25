import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ICDCodeTabularOptimizedForMemory implements ICDCodeTabular
{
    String path;
    public ICDCodeTabularOptimizedForMemory(String path){
        this.path = path;
    }
    @Override
    public String getDescription(String ICD) throws IndexOutOfBoundsException
    {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line;
            for(int i =0; i<88; i++)
                bf.readLine();
            while((line= bf.readLine())!=null)
            {
                if(line.contains(ICD)) {
                    String[] parts = line.trim().split(" ", 2);
                    if(parts[0].equals(ICD))
                    {
                        bf.close();
                        return parts[1];
                    }
                }
            }
            bf.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        throw new IndexOutOfBoundsException();
    }
}
