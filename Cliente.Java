import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main ( String [ ] args ) {
        try {
            Socket cliente = new Socket ( "127.0.0.1" , 20000 );
            System.out.println ( "\nPode conversar chefe, lembre que " + "/sair" + " fecha a convera.\n" );

            Scanner teclado = new Scanner ( System.in );
            Scanner entrada = new Scanner ( cliente.getInputStream ( ) );
            PrintStream saida = new PrintStream ( cliente.getOutputStream ( ) );

            // Thread para receber mensagens do servidor
            
            new Thread ( ( ) -> {
                while ( entrada.hasNextLine ( ) ) {
                    System.out.println ( entrada.nextLine ( ) );
                }
            }).start ( );

            String msg;
            while ( true ) {
                msg = teclado.nextLine ( ) ;
                saida.println ( msg ) ;

                if ( msg.equalsIgnoreCase ( "sair" ) ) {
                    break;
                }
            }

            cliente.close ( ) ;
            System.out.println ( "Desconectado do servidor.\n" ) ;

        } catch ( IOException e ) {
            System.out.println("Deu ruim man, ó o erro aí: " + e.getMessage ( ) + "\n " );
        }
    }
}
