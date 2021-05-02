package stockanalyzer.ctrl;

import yahooApi.YahooFinanceException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.Calendar;

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

	public Object getData(String searchString) {return null;}

	public void closeConnection() {}
}