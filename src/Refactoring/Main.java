package Refactoring;

import java.io.*;

class MyApp implements DataConnection {

        public static MyApp create(String y) {
            return new MyApp(y);
        }

    public MyApp(String y) {
        this.y = y;
    }

    public String getY(){
            return this.y;
    }

    public void setY(String y){
            this.y = y;
    }
    
    private String y;
    private static int COUNT = 0;
    private static int COUNT1 = 0;
    protected static int startYear = 1990;
    protected static int endYear = 2020;
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        try {
            System.out.println("app v.1.13");
            for (int i = startYear; i < endYear; i++) {
                int sum = 0;
                String y = i + "";
                sum =  create(y).loadDatas(sum);
//                System.out.println("sum = "+sum);
                double qq = sum > 0 ? (double) sum / (double) COUNT : 0;
                if (qq > 0) {
                    System.out.println(i + " " + qq);
                }
                 /*new*/ create(y).saveData(i, (int) qq);
            }
            System.out.println("gotovo");
        } catch (Exception e) {
            System.out.println("oshibka!" + e);
        }
    }

    public int loadDatas(int sum) throws Exception {
//try {
//FileOutputStream fis = new FileOutputStream(file);
        FileInputStream fis = new FileInputStream("1.txt");
        StringBuilder string = new StringBuilder();
        int endOfString = fis.read();
        do {
            string.append(new String(new byte[]{(byte) endOfString}));
            endOfString = fis.read();
        } while (endOfString != -1);

        int begin = 0;

        while (true) {
            int indexEndOfString = string.indexOf("\n", begin + 1);

            if (indexEndOfString == -1) {
                break;
            }

            String ss = string.substring(begin, indexEndOfString);

            String[] sss = ss.split(" ");
//            for (String string : sss) {
//                System.out.println("asd = "+string);
//            }
            if (sss[2].contains(this.y) /*|| sss[2].contains(y)*/) {
                sum = sum + Integer.parseInt(sss[3]);
            }
            COUNT++;
            begin = indexEndOfString;
        }
        return sum;
    }
    public void saveData(int year, int qq) throws IOException {
        FileOutputStream fis = new FileOutputStream("statistika.txt", true);
        String s = "";
        s = COUNT1 + " " + year + " " + qq + "\n";
        fis.write(s.getBytes());
        COUNT1++;
    }
}