package application;

import controller.DataScraping;
import util.ExcelWriter;

public class App {
    public static void main(String[] args) {
        DataScraping ds = new DataScraping();
        ExcelWriter ew = new ExcelWriter();
        ew.createSheet(ds.scraping());
    }
}
