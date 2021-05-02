import stockanalyzer.ui.UserInterface;
import yahooApi.YahooFinanceException;

public class MCP {

	public static void main(String args[]) throws YahooFinanceException {
		UserInterface ui = new UserInterface();
		ui.start();
	}
}
