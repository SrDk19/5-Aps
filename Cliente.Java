import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args){
        try{
            //Declarando e criando o socket com recurso desejado na porta
            //o host nao e o meu pc e um aleatorio tem que mudar isso depois
            Socket cliente = new Socket("127.0.0.1",7000);
           
            //captando o teclado
            
            Scanner teclado = new Scanner(System.in);
            Scanner chegada = new Scanner(cliente.getInputStream());
            
            //Declaraçao e fluxo de dados vulgo menssagens
            PrintStream saida = new PrintStream(cliente.getOutputStream());
           
            //aqui e onde a menssagem e escrita e enviada
            String msg ="";
            do{
                System.out.println("Informe a sua menssagem");
                
                // menssagem enviada para o servidor
                msg = teclado.nextLine();
                saida.println(msg);
                String resposta = chegada.nextLine();
                System.out.println("resposta para a menssagem ("+ msg + ")" + "é"+ resposta);
            
            }while(msg.length() !=0);
            
            //imprime uma linha para o fluxo de dados (no caso a linha da menssagem XD)
            //eu entendi so por cima como funciona essa linha de codigo a baixo 
            
            saida.println("opa,lassana aqui... Bom dio" + (int) (Math.random()*10000));

            cliente.close();

        }catch(Exception e){

            System.out.println("problema na conexao do server");

        }

    }
    
}
