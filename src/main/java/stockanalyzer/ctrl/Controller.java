package stockanalyzer.ctrl;

import stockanalyzer.downloader.Downloader;
import stockanalyzer.downloader.SequentialDownloader;
import yahooApi.YahooFinanceException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Controller {

	Stock stock = null;

	public void process(String ticker) throws YahooFinanceException {

		try {
			stock = YahooFinance.get(ticker);

			Calendar from = Calendar.getInstance();
			from.add(Calendar.WEEK_OF_MONTH, -10);

			var maximalPrice = stock.getHistory(from, Interval.DAILY).stream()
					.mapToDouble(a -> a.getClose().doubleValue())
					.max()
					.orElse(0.0);

			var average = stock.getHistory(from,Interval.DAILY).stream()
					.mapToDouble(a -> a.getClose().doubleValue())
					.average()
					.orElse(0.0);

			var amount = stock.getHistory().stream()
					.mapToDouble(q -> q.getClose().doubleValue())
					.count();

			System.out.println(stock.getName());
			System.out.println("!!! Max Price !!!");
			System.out.println(maximalPrice);
			System.out.println("!!! Average Price !!!");
			System.out.println(average);
			System.out.println("Amount");
			System.out.println(amount);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void downloadTickers(List<String> list, Downloader downloader) throws YahooFinanceException{
		downloader.process(list);
	}

	public Object getData(String searchString) {return null;}

	public void closeConnection() {}
}