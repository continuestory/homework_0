package interfaces;
import vo.StockInfo;
public interface StockSorter {
    StockInfo[] sort(StockInfo[] info);
    StockInfo[] sort(StockInfo[] info,boolean order);
}
