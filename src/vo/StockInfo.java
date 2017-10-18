package vo;

import interfaces.FileHandler;
import interfaces.StockSorter;

import java.io.FileReader;

public class StockInfo {
    private String ID;
    private String Title;
    private String Author;
    private String Date;
    private String LastUpDate;
    private String Content;
    private String AnswerAuthor;
    private String Answer;
    public String getInformation(int type){
        switch (type){
            case 0:return ID;
            case 1:return Title;
            case 2:return Author;
            case 3:return Date;
            case 4:return LastUpDate;
            case 5:return Content;
            case 6:return AnswerAuthor;
            case 7:return Answer;
            default:return "nothing";
        }
    }
    public void setInformation(int type, String replace){
        switch (type){
            case 0:ID = replace;break;
            case 1:Title = replace;break;
            case 2:Author = replace;break;
            case 3:Date = replace;break;
            case 4:LastUpDate = replace;break;
            case 5:Content = replace;break;
            case 6:AnswerAuthor = replace;break;
            case 7:Answer = replace;break;
            default:;
        }
    }

}
