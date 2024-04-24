import java.util.Scanner;

import http.WebClient;
import model.ExchangeResponse;
import model.Historic;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        WebClient client = new WebClient();
        Historic historic = new Historic();
        String menu = """
                    \n
                    0 - Para ver o menu novamente
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

        while(true) {
            System.out.print("Bem vindo ao conversor de moedas, selecione uma opção no menu (Digite 0 para ver o menu novamente): ");
            int opcao = scanner.nextInt();

            if(opcao < 0 || opcao > 8) {
                System.out.println("Opção invalida");
                continue;
            } else if(opcao == 7) {
                historic.viewHistoric();
                continue;
            } else if(opcao == 8) {
                break;
            } else if(opcao == 0) {
                System.out.println(menu);
                continue;
            }

            System.out.print("Digite o valor que deseja converter: ");
            double valor = scanner.nextDouble();
            ExchangeResponse response = client.converter(opcao,valor);
            historic.add(response,valor);

            System.out.println("******************************************\n");
            System.out.printf("O valor %.2f em [%s], corresponde ao valor %.4f em [%s]\n",valor,response.baseCode(),response.conversionResult(),response.targetCode());
            System.out.println("\n******************************************");
        }

        scanner.close();

    }
}
