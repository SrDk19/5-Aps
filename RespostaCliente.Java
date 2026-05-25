import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RespostaCliente extends Thread {

    private final Socket cliente;

    public RespostaCliente( Socket cli ) {
        this.cliente = cli;
    }

    @Override
    public void run( ) {
        try {
            Scanner entrada = new Scanner(cliente.getInputStream( ));
            PrintStream saida = new PrintStream(cliente.getOutputStream( ));

            System.out.println ( "Conexão com o pc: " + cliente.getInetAddress( ) + " = conectado.\n");

            while (entrada.hasNextLine( )) {
                String mensagem = entrada.nextLine( );
                System.out.println ( "[" + cliente.getInetAddress( ) + "] disse: " + mensagem + "\n " );

                // Responde para o cliente/amiguinho
                saida.println ( "Servidor responde: " + mensagem + "\n " );
            }

        } catch ( Exception e ) {
            System.out.println ( "\nAmigo " + cliente.getInetAddress( ) + " desconectou. \n" );
        } finally {
            try {
                cliente.close( );
            } catch ( IOException e ) {
                e.printStackTrace( );
            }
        }
    }
}