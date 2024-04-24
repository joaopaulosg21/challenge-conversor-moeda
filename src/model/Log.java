package model;

import java.time.LocalDateTime;

public record Log(String searchTime, ExchangeResponse exchangeResponse, double valor) {
}
