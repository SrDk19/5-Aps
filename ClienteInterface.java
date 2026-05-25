import java.awt.BorderLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClienteInterface extends JFrame {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private JTextArea areaChat;
    private JTextField campoMensagem;
    private String nomeUsuario;

    public ClienteInterface ( ) {
        setTitle( "APS 5° Semestre" );
        setSize( 700, 500 );
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo ( null );

        // Área de chat

        areaChat = new JTextArea ( );
        areaChat.setEditable( false );
        areaChat.setFont ( new Font( "Arial", Font.PLAIN, 14 ));
        JScrollPane scroll = new JScrollPane ( areaChat );

        // Campo de mensagem

        campoMensagem = new JTextField ( );
        JButton btnEnviar = new JButton ( "Enviar" );
        JPanel painelInferior = new JPanel ( new BorderLayout( ));
        painelInferior.add (campoMensagem, BorderLayout.CENTER );
        painelInferior.add ( btnEnviar, BorderLayout.EAST );

        add ( scroll, BorderLayout.CENTER );
        add ( painelInferior, BorderLayout.SOUTH );

        // Ações

        btnEnviar.addActionListener ( e -> enviarMensagem ( ));
        campoMensagem.addActionListener ( e -> enviarMensagem ( ));

        conectar( );
    }

    private void conectar( ) {
        try {
            nomeUsuario = JOptionPane.showInputDialog ( this, "Digite seu nome de usuário: ", "Bem-vindo ao Chat ", JOptionPane.PLAIN_MESSAGE );
            if ( nomeUsuario == null || nomeUsuario.trim( ).isEmpty( )) nomeUsuario = "Usuário" + ( int )( Math.random ( ) * 999);

            String ip = JOptionPane.showInputDialog ( this, "Digite o IP do Servidor:", "127.0.0.1" );
            if ( ip == null ) ip = "127.0.0.1";

            socket = new Socket ( ip, 20000 );
            out = new PrintWriter ( socket.getOutputStream ( ), true );
            in = new BufferedReader ( new InputStreamReader ( socket.getInputStream ( )));

            out.println ( nomeUsuario );  // Envia o nome

            // Thread para receber mensagens

            new Thread ( this::receberMensagens ).start( );

        } catch ( IOException e ) {
            JOptionPane.showMessageDialog ( this, "Erro ao conectar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE );
            System.exit ( 1 );
        }
    }

    private void receberMensagens ( ) {
        try {
            String mensagem;
            while (( mensagem = in.readLine( )) != null ) {
                String finalMensagem = mensagem;
                SwingUtilities.invokeLater(( ) -> {
                    areaChat.append ( finalMensagem + "\n" );
                    areaChat.setCaretPosition ( areaChat.getDocument( ).getLength( ));
                });
            }
        } catch ( IOException e ) {
            SwingUtilities.invokeLater(( ) -> areaChat.append("\nDesconectado do servidor.\n " ));
        }
    }

    private void enviarMensagem ( ) {
        String texto = campoMensagem.getText( ).trim( );
        if ( !texto.isEmpty( ) && out != null ) {
            out.println( texto );
            campoMensagem.setText("");
        }
    }

    public static void main ( String [ ] args ) {
        SwingUtilities.invokeLater(( ) -> new ClienteInterface( ).setVisible( true ));
    }
}