import java.io.*;

public class Laba2 {

    private String delIn = ",";
    private String delOut = "+";

    public Laba2(){}

    public Laba2(String delimIn, String delimOut) {
        this.delIn = delimIn;
        this.delOut = delimOut;
    }
    public void checkFile(String fileInput, String fileOutput) {
        BufferedReader bufR;
        BufferedWriter bufW;
        try {
            bufR = new BufferedReader(new FileReader(fileInput));
            bufW = new BufferedWriter(new FileWriter(fileOutput));
            String string = bufR.readLine();
            while (string != null) {
                bufW.write(this.checkStr(string) + "\n");
                bufW.flush();
                string = bufR.readLine();
            }
            bufR.close();
            bufW.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String checkStr(String in) {
        String cI = in;
        StringBuilder result = new StringBuilder();

        while (true) {
            String resault = "";
            int index = this.workWithCsv(cI);
            if (cI.charAt(0) == '"') {
                resault = cI.substring(1, index);
                index += 2;
            } else if (cI.contains(",")) {
                index = cI.indexOf(',') + 1;
                resault = cI.substring(0, index - 1);
            }
            if (index != -1 && index < cI.length()) {
                result.append(String.format("%d%s", resault.length(), delOut));
                cI = cI.substring(index);
            } else {
                result.append(String.format("%d", cI.length() - (cI.charAt(0) == '"' ? 2 : 0)));
                break;
            }
        }
        return result.toString();

    }

    private int workWithCsv(String in) {
        if (in.charAt(0) == '"') {
            long quotesNumb = in.chars().filter(ch -> ch == '"').count();
            if (quotesNumb % 2 != 0)
                throw new IllegalArgumentException("!!!No close!!!! \"");
            String temp = in.substring(1);
            int cutLeng = 1;
            int numQuote = 1;
            while (true) {
                int quoteIndex = temp.indexOf('"');
                if (quoteIndex == temp.length() - 1 || (numQuote % 2 != 0 && temp.charAt(quoteIndex + 1) == ','))
                    return quoteIndex + cutLeng;
                else {
                    numQuote++;
                    cutLeng += quoteIndex + 1;
                    temp = temp.substring(quoteIndex + 1);
                }
            }
        } else {
            return in.indexOf(",");
        }
    }

    public String getDelIn(){
        return this.delIn;
    }

    public String getDelOut(){
        return this.delOut;
    }
}