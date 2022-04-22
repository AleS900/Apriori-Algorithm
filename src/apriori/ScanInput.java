import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ScanInput {
    public static String getInput()
    {
        String input="";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            input = reader.readLine();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return input;
    }
}
