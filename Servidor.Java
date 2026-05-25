import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main ( String [ ] args ) {
        try {

            ServerSocket servidor = new ServerSocket ( 20000 );
            System.out.println ( "\nServidor rodando chefe \n" );
            
            while ( true ) {

                Socket cliente = servidor.accept( );
                System.out.println ( "Conexão feita: " + cliente.getInetAddress ( ));
                
                // Inicia uma thread para cada cliente
                
                new RespostaCliente(cliente).start();

            }
        } catch (IOException e) {
            System.out.println ( "\nFunfou n man, se liga no que deu : " + e.getMessage( ) + "\n " );
            e.printStackTrace( );
        }
    }
}
