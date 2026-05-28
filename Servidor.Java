import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class Servidor {
    private static final int PORTA = 20000;
    private static final List < ClienteHandler > clientes = new ArrayList < > ( );

    public static void main ( String [ ] args ) {
        try ( ServerSocket servidor = new ServerSocket ( PORTA )) {
        System.out.println( "\nServidor iniciado  irmão.\n\nPorta: " + PORTA + "\n\nIP: 127.0.0.1\n" );
            System.out.println( "Conectado com os amiguinhos.\n");

            while ( true ) {
                Socket socket = servidor.accept ( );
                ClienteHandler handler = new ClienteHandler ( socket );
                clientes.add ( handler );
                new Thread ( handler ).start( );
            }
        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }

    public static synchronized void broadcast ( String mensagem, ClienteHandler remetente ) {
        for ( ClienteHandler cliente : clientes ) {
            if ( cliente != remetente ) {
                cliente.enviarMensagem ( mensagem );
            }
        }
    }

    public static synchronized void removerCliente ( ClienteHandler cliente ) {
        clientes.remove ( cliente );
    }
}

class ClienteHandler implements Runnable {
    private final Socket socket;
    private PrintWriter out;
    private String nome = "\nAnônimo\n";

    public ClienteHandler ( Socket socket ) {
        this.socket = socket;
    }

    @Override
    public void run ( ) {
        try {
            BufferedReader in = new BufferedReader ( new InputStreamReader ( socket.getInputStream ( )));
            out = new PrintWriter(socket.getOutputStream ( ), true );

            // Pede nome do usuário
            out.println ( "\nDigite seu nome: \n" );
            nome = in.readLine ( );

            System.out.println ( "\n" + nome + " conectou.\n" );
            Servidor.broadcast ( "\n" + nome + " entrou na sala.\n", this );

            String mensagem;
            while ( ( mensagem = in.readLine( ) ) != null ) {
                if ( mensagem.equalsIgnoreCase ( "sair" )) break;
                Servidor.broadcast( "[ " + nome + " ] : " + mensagem, this );
            }

        } catch ( IOException e) {

            // Cliente desconectou
        
        } finally {
            Servidor.broadcast ( nome + " kitou.\n", this );
            Servidor.removerCliente ( this );
            try { socket.close ( ); } catch ( IOException ignored ) { }
        }
    }

    public void enviarMensagem ( String mensagem ) {
        if ( out != null ) out.println ( mensagem );
    }
}