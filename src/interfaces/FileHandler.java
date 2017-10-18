package interfaces;
import vo.StockInfo;
public interface FileHandler {

    int setStockInfo2File(String filePath,StockInfo[] s);
    StockInfo[] getStockInfoFromFile(String filePath);
}
