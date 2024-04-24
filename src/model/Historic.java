package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Historic {
    private List<Log> historic;

    public Historic() {
        this.historic = new ArrayList<>();
    }

    public void add(ExchangeResponse exchangeResponse, double valor) {
        var format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String date = LocalDateTime.now().format(format);
        historic.add(new Log(date,exchangeResponse,valor));
    }

    public void viewHistoric() {
        for(Log log : historic) {
            System.out.println("*******************************************************");
            System.out.println("Data da busca: " + log.searchTime());
            System.out.print("Valor: " + log.valor() + "(" + log.exchangeResponse().baseCode() + ")\t" );
            System.out.println("Convertido para: " + log.exchangeResponse().conversionResult() + "(" + log.exchangeResponse().targetCode() + ")\n");
        }
    }

}
