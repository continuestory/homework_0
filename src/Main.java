import interfaces.FileHandler;
import interfaces.StockSorter;
import vo.StockInfo;

import java.io.*;
import java.util.Formatter;

public abstract class Main implements FileHandler, StockSorter{
    private static FileHandler fileHandler;
    private static StockSorter stockSorter;
    static {
        fileHandler = new FileHandler() {


            @Override
            public int setStockInfo2File(String filePath, StockInfo[] s) {
                byte[] buff = new byte[]{};

                try (OutputStream os = new FileOutputStream(filePath)) {
                    for (int x = 0; x < 10002; x++) {
                        for (int y = 0; y < 8; y++) {
                            buff = s[x].getInformation(y).getBytes();
                            os.write(buff, 0, buff.length);
                            os.write('\011');
                        }
                        String t = "\n";
                        os.write(t.getBytes());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 0;
            }

            @Override
            public StockInfo[] getStockInfoFromFile(String filePath) {
                StockInfo[] dataStock = new StockInfo[10002];

                try {
                    FileReader myfile = new FileReader(new File(filePath));
                    BufferedReader myBuff = new BufferedReader(myfile);
                    String s = null;
                    for (int a = 0; a < 10002; a++) {
                        dataStock[a] = new StockInfo();
                    }
                    int i = 0;
                    while ((s = myBuff.readLine()) != null) {
                        String tempS[] = s.split("\t");
                        for (int d = 0; d < tempS.length; d++) {
                            if (tempS[d].equals("")) {
                                dataStock[i].setInformation(d, "nothing");
                            } else {
                                dataStock[i].setInformation(d, tempS[d]);
                            }
                            System.out.println(dataStock[i].getInformation(d) + "\t");
                        }
                        System.out.println();
                        i++;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return dataStock;
            }


        };

        stockSorter = new StockSorter() {
            @Override
            public StockInfo[] sort(StockInfo[] info) {
                for (int j = 1; j < info.length - 1; j++) {
                    for (int i = 1; i < info.length - 1 - j; i++) {
                        if ((info[i].getInformation(7).compareTo(info[i+1].getInformation(7))) > 0) {
                            StockInfo temp = info[i];
                            info[i] = info[i + 1];
                            info[i + 1] = temp;
                        }
                    }
                }
                return info;
            }

            @Override
            public StockInfo[] sort(StockInfo[] info, boolean order) {
                return new StockInfo[0];
            }
        };
    }


    public static void main(String[] args) {
        String filePath = "D:\\11272\\data.txt";
        String targetPath = "D:\\11272\\test.txt";

        StockInfo[] stocks = fileHandler.getStockInfoFromFile(filePath);
        if(stocks != null)
            System.out.println("数据读入成功");

        //数据排序
        StockInfo[] sortedStocks = stockSorter.sort(stocks);
        System.out.println("排序结束");

        int writeLenght = fileHandler.setStockInfo2File(targetPath,sortedStocks);
        Formatter formatter = new Formatter(System.out);
        if(writeLenght == sortedStocks.length)
            formatter.format("写入操作成功，共写入%d条数据",2);
        else
            formatter.format("写入失败");
    }


}
