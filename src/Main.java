import http.WebClient;
import model.ExchangeResponse;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        WebClient client = new WebClient();

        while(true) {
            String menu = """
                    \n
                    1 - Real brasileiro --> Peso argentino
                    2 - Peso argentino --> Real brasileiro
                    3 - Dolar americano --> Peso colombiano
                    4 - Peso colombiano --> Dolar americano
                    5 - Peso chileno --> Boliviano boliviano
                    6 - Boliviano boliviano --> Peso chileno
                    7 - sair
                """;
            System.out.println(menu);
            System.out.print("Bem vindo ao conversor de moedas, selecione uma opção no menu: ");
            int opcao = scanner.nextInt();

            if(opcao < 1 || opcao > 7) {
                System.out.println("Opção invalida");
            }
            if(opcao == 7) {
                break;
            }

            System.out.print("Digite o valor que deseja converter: ");
            double valor = scanner.nextDouble();
            ExchangeResponse response = client.converter(opcao,valor);

            System.out.println("******************************************\n");
            System.out.printf("O valor %.2f em [%s], corresponde ao valor %.4f em [%s]\n",valor,response.baseCode(),response.conversionResult(),response.targetCode());
            System.out.println("\n******************************************");
            Thread.sleep(2000);
        }

    }
}
