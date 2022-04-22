import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

class AprioriCalculation
{
    Vector<String> candidates= new Vector<>(); //the current candidates
    String configFile="config.txt"; //configuration file
    String transaFile="transa.txt"; //transaction file
    String outputFile="apriori-output.txt";//output file

    int numItems; //number of items per transaction
    int numTransactions; //number of transactions
    double minSup; //minimum support for a frequent itemset

    String[] oneVal; //array of value per column that will be treated as a '1'
    String itemSep = " "; //the separator value for items in the database


    public void aprioriProcess() {
        Date d;
        long start, end;
        int itemsetNumber=0;
        //get config
        getConfig();
        System.out.println("Apriori algorithm has started.\n");
        d = new Date();
        start = d.getTime();
        do
        {
            itemsetNumber++;
            generateCandidates(itemsetNumber);
            calculateFrequentItemsets();
            if(candidates.size()!=0)
            {
                System.out.println("Frequent " + itemsetNumber + "-itemsets");
                System.out.println(candidates);
            }
        }
        while(candidates.size()>1);
        d = new Date();
        end = d.getTime();
        System.out.println("Execution time is: "+((double)(end-start)/1000) + " seconds.");
    }

    private void getConfig() {
        FileWriter fw;
        BufferedWriter file_out;

        String input;
        System.out.println("Default Configuration: ");
        System.out.println("\tConfiguration File: " + configFile);
        System.out.println("\tTransaction File  : " + transaFile);
        System.out.println("\tOutput File: " + outputFile);
        System.out.println("\nPress any other key to continue.");
        input= ScanInput.getInput();

        if(input.compareToIgnoreCase("c")==0)
        {
            System.out.print("Enter new transaction filename (return for '"+transaFile+"'): ");
            input= ScanInput.getInput();
            if(input.compareToIgnoreCase("")!=0)
                transaFile=input;

            System.out.print("Enter new configuration filename (return for '"+configFile+"'): ");
            input= ScanInput.getInput();
            if(input.compareToIgnoreCase("")!=0)
                configFile=input;

            System.out.print("Enter new output filename (return for '"+outputFile+"'): ");
            input= ScanInput.getInput();
            if(input.compareToIgnoreCase("")!=0)
                outputFile=input;

            System.out.println("Filenames changed");

            System.out.print("Enter the separating character(s) for items (return for '"+itemSep+"'): ");
            input= ScanInput.getInput();
            if(input.compareToIgnoreCase("")!=0)
                itemSep=input;
        }

        try
        {
            FileInputStream file_in = new FileInputStream(configFile);
            BufferedReader data_in = new BufferedReader(new InputStreamReader(file_in));
            numItems= Integer.parseInt(data_in.readLine());

            numTransactions= Integer.parseInt(data_in.readLine());

            minSup=(Double.parseDouble(data_in.readLine()));

            System.out.println("\nInput configuration: " );
            System.out.println("      - Items       : " +numItems);
            System.out.println("      - Transactions: " +numTransactions);
            System.out.println("      - Min. Support:" +minSup+" %, ");
            System.out.println();
            minSup/=100.0;

            oneVal = new String[numItems];
            System.out.print("Enter 'y' to change the value each row recognizes as a '1':");
            if(ScanInput.getInput().compareToIgnoreCase("y")==0)
            {
                for(int i=0; i<oneVal.length; i++)
                {
                    System.out.print("Enter value for column #" + (i+1) + ": ");
                    oneVal[i] = ScanInput.getInput();
                }
            }
            else
                Arrays.fill(oneVal, "1");

            fw= new FileWriter(outputFile);
            file_out = new BufferedWriter(fw);
            file_out.write(numTransactions + "\n");
            file_out.write(numItems + "\n******\n");
            file_out.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    private void generateCandidates(int n)
    {
        Vector<String> tempCandidates = new Vector<>();
        StringBuilder str1;
        StringBuilder str2;
        StringTokenizer st1, st2;

        if(n==1)
        {
            for(int i=1; i<=numItems; i++)
            {
                tempCandidates.add(Integer.toString(i));
            }
        }
        else if(n==2)
        {
            for(int i=0; i<candidates.size(); i++)
            {
                st1 = new StringTokenizer(candidates.get(i));
                str1 = new StringBuilder(st1.nextToken());
                for(int j=i+1; j<candidates.size(); j++)
                {
                    st2 = new StringTokenizer(candidates.elementAt(j));
                    str2 = new StringBuilder(st2.nextToken());
                    tempCandidates.add(str1 + " " + str2);
                }
            }
        }
        else
        {

            for(int i=0; i<candidates.size(); i++)
            {

                for(int j=i+1; j<candidates.size(); j++)
                {
                    str1 = new StringBuilder();
                    str2 = new StringBuilder();
                    st1 = new StringTokenizer(candidates.get(i));
                    st2 = new StringTokenizer(candidates.get(j));

                    for(int s=0; s<n-2; s++)
                    {
                        str1.append(" ").append(st1.nextToken());
                        str2.append(" ").append(st2.nextToken());
                    }

                    if(str2.toString().compareToIgnoreCase(str1.toString())==0)
                        tempCandidates.add((str1 + " " + st1.nextToken() + " " + st2.nextToken()).trim());
                }
            }
        }
        candidates.clear();
        candidates = new Vector<>(tempCandidates);
        tempCandidates.clear();
    }

    private void calculateFrequentItemsets()
    {
        Vector<String> frequentCandidates = new Vector<>();
        FileInputStream file_in;
        BufferedReader data_in;
        FileWriter fw;
        BufferedWriter file_out;

        StringTokenizer st, stFile;
        boolean match;
        boolean[] trans = new boolean[numItems];
        int[] count = new int[candidates.size()];

        try
        {
            fw= new FileWriter(outputFile, true);
            file_out = new BufferedWriter(fw);
            file_in = new FileInputStream(transaFile);
            data_in = new BufferedReader(new InputStreamReader(file_in));

            for(int i=0; i<numTransactions; i++)
            {
                stFile = new StringTokenizer(data_in.readLine(), itemSep);

                for(int j=0; j<numItems; j++)
                {
                    trans[j]=(stFile.nextToken().compareToIgnoreCase(oneVal[j])==0);
                }

                for(int c=0; c<candidates.size(); c++)
                {
                    match = false; //reset match to false
                    st = new StringTokenizer(candidates.get(c));
                    while(st.hasMoreTokens())
                    {
                        match = (trans[Integer.parseInt(st.nextToken())-1]);
                        if(!match)
                            break;
                    }
                    if(match)
                        count[c]++;
                }
            }
            for(int i=0; i<candidates.size(); i++)
            {
                if((count[i]/(double)numTransactions)>=minSup)
                {
                    frequentCandidates.add(candidates.get(i));
                    file_out.write(candidates.get(i) + "," + count[i]/(double)numTransactions + "\n");
                }
            }
            file_out.write("-\n");
            file_out.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        candidates.clear();
        candidates = new Vector<>(frequentCandidates);
        frequentCandidates.clear();
    }
}