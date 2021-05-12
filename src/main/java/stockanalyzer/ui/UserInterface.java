package stockanalyzer.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import stockanalyzer.ctrl.Controller;
import stockanalyzer.downloader.Downloader;
import stockanalyzer.downloader.ParallelDownloader;
import stockanalyzer.downloader.SequentialDownloader;
import yahooApi.YahooFinanceException;

public class UserInterface
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		try {
			ctrl.process("BMW.DE");
		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl2(){
		try {
			ctrl.process("VWAGY");
		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl3() {
		try {
			ctrl.process("GM");
		} catch (YahooFinanceException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromDownloaderSequential() {

		try {
			Downloader downloader;
			ArrayList<String> list = new ArrayList<>();

			list.add("FB");
			list.add("AMZN");
			list.add("BMW.DE");
			list.add("GM");
			list.add("AAPL");

			SequentialDownloader sequentialDownloader = new SequentialDownloader();
			ctrl.downloadTickers(list, sequentialDownloader);

		}catch (YahooFinanceException e)
		{
			e.printStackTrace();
		}
	}

	public void getDataFromDownloaderParallel() {

		try {
			Downloader downloader;
			ArrayList<String> list = new ArrayList<>();

			list.add("FB");
			list.add("AMZN");
			list.add("BMW.DE");
			list.add("GM");
			list.add("AAPL");

			ParallelDownloader parallelDownloader = new ParallelDownloader();
			ctrl.downloadTickers(list, parallelDownloader);

		}catch (YahooFinanceException e)
		{
			e.printStackTrace();
		}
	}

	public void start() throws YahooFinanceException {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Bayerische Motoren Werke Aktiengesellschaft", this::getDataFromCtrl1);
		menu.insert("b", "Volkswagen AG", this::getDataFromCtrl2);
		menu.insert("c", "General Motors Company.", this::getDataFromCtrl3);
		menu.insert("d","Download Tickers sequential", this::getDataFromDownloaderSequential);
		menu.insert("e","Download Tickets parallel",this::getDataFromDownloaderParallel );
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			choice.run();
		}
		System.out.println("Program finished");
	}

	protected String readLine()
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit)
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}