import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main ( String [] args ) {

        try {

            //Declarando e criando o socket com recurso desejado na porta
            //o host nao e o meu pc e um aleatorio tem que mudar isso depois
            
            Socket cliente = new Socket ( "127.0.0.1/n " , 7000 );
           
            //captando o teclado
            
            Scanner teclado = new Scanner ( System.in );
            Scanner chegada = new Scanner ( cliente.getInputStream ( ));
            
            //Declaraçao e fluxo de dados vulgo menssagens
            
            PrintStream saida = new PrintStream ( cliente.getOutputStream ( ));
           
            //aqui e onde a menssagem e escrita e enviada
            
            String msg = " " ;

            do {

                System.out.println ( "Informe a sua mensagem:/n " );
                
                // menssagem enviada para o servidor
                
                msg = teclado.nextLine ( );
                saida.println  ( msg );

                //Mensagem enviada pro servidor

                String resposta = chegada.nextLine ( );
                System.out.println("Resposta para a mensagem ("+msg+")" + "é" + resposta "/n" );
            
            } while ( msg.length ( ) != 0 );
            
            cliente.close ( );

        } catch ( Exception e ) {

            System.out.println ( "Deu ruim man, não conectou no Servidor./n " );

        }

    }
    
}
