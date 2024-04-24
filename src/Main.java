import http.WebClient;
import model.ExchangeResponse;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        WebClient client = new WebClient();
        List<String> conversoes = new ArrayList<>();
        while(true) {
            String menu = """
                    \n
                    1 - Real brasileiro --> Peso argentino
                    2 - Peso argentino --> Real brasileiro
                    3 - Dolar americano --> Peso colombiano
                    4 - Peso colombiano --> Dolar americano
                    5 - Peso chileno --> Boliviano boliviano
                    6 - Boliviano boliviano --> Peso chileno
                    7 - Verificar historico de conversões
                    8 - Sair
                """;
            System.out.println(menu);
            System.out.print("Bem vindo ao conversor de moedas, selecione uma opção no menu: ");
            int opcao = scanner.nextInt();

            if(opcao < 1 || opcao > 8) {
                System.out.println("Opção invalida");
            }
            if(opcao == 7) {
                conversoes.forEach(System.out::println);
                Thread.sleep(2000);
                continue;
            }
            if(opcao == 8) {
                break;
            }

            System.out.print("Digite o valor que deseja converter: ");
            double valor = scanner.nextDouble();
            ExchangeResponse response = client.converter(opcao,valor);
            String conversao = String.format("O valor %.2f em [%s], corresponde ao valor %.4f em [%s]\n",valor,response.baseCode(),response.conversionResult(),response.targetCode());
            conversoes.add(conversao);

            System.out.println("******************************************\n");
            System.out.printf(conversao);
            System.out.println("\n******************************************");
            Thread.sleep(2000);
        }

    }
}
