import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main ( String [ ] args ) {
        try {

            ServerSocket servidor = new ServerSocket ( 7000 );
            System.out.println ( "Carregando pae... /n" );
            
            while ( true ) {

                RespostaCliente rc = new RespostaCliente ( servidor.accept ( ));
                rc.start ( );


            }
        catch ( Exception e ) {
        System.out.println ( "Ocorreu um erro!/n " );
            }
        }
    }
}
