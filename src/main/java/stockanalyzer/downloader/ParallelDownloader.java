package stockanalyzer.downloader;

import yahooApi.YahooFinanceException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader {

    @Override
    public int process(List<String> tickers) throws YahooFinanceException {

        ExecutorService execute = Executors.newFixedThreadPool(4);
        List<Future<String>> List = new ArrayList<>();

        for (String ticker : tickers) {

            Callable<String> request = () -> saveJson2File(ticker);
            Future<String> File = execute.submit(request);
            List.add(File);
        }

        for (Future<String> future : List ){
            try {
                String File = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return List.size();
    }
}