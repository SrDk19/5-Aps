// import java.io.*;
// import java.net.Socket;
// import java.util.Scanner;
// 
// public class RespostaCliente extends Thread {
// 
//     private final Socket cliente;
// 
//     public RespostaCliente( Socket cli ) {
//         this.cliente = cli;
//     }
// 
//     @Override
//     public void run( ) {
//         try {
//             Scanner entrada = new Scanner(cliente.getInputStream( ));
//             PrintStream saida = new PrintStream(cliente.getOutputStream( ));
// 
//             System.out.println ( "\nConexão com o pc: " + cliente.getInetAddress( ) + " realizada com sucesso.\n");
// 
//             while (entrada.hasNextLine( )) {
//                 String mensagem = entrada.nextLine( );
//                 System.out.println ( "\n[" + cliente.getInetAddress( ) + "] disse: " + mensagem + "\n " );
// 
//                 // Responde para o cliente/amiguinho
//                 saida.println ( "\nServidor responde: " + mensagem + "\n " );
//             }
// 
//         } catch ( Exception e ) {
//             System.out.println ( "\nAmigo " + cliente.getInetAddress( ) + " desconectou. \n" );
//         } finally {
//             try {
//                 cliente.close( );
//             } catch ( IOException e ) {
//                 e.printStackTrace( );
//             }
//         }
//     }
// } Tá obsoleto por conta do Handler, mas é bom deixar o passado como aprendizado.