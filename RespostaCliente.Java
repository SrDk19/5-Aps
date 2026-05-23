import java.net.Socket;

public class RespostaCliente extends Thread{

    Socket cliente;

    RespostaCliente(Socket cli){

        this.cliente = cli;
    }
    @Override
    public void run(){

        System.out.println("Cliente conectando com thread("+this.getId()+")  :/n" + cliente);

        Scanner teclado = new Scanner(System.in);
        Scanner chegada = new Scanner(cliente.getInputStream()); // Scanner para ler o que o cliente envia
        
        inputStreamReader fluxoDados;

        try {

            chegada = new Scanner(cliente.getInputStream());
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            //fluxoDados = new InputStreamReader(cliente.getInputStream());
            //BufferedReader dados = new BufferedReader(fluxoDados);

            while ( chegada.hasNextLine ( )) {
                String msgChegadaC = chegada.nextLine ( );
                System.out.println ( "Informe sua resposta para seu amiguinho " + msgChegadaC "/n" );
                String resposta = teclado.nextLine ( );
                saida.println ( resposta );
            }

            System.out.println ( dado.readLine ( ));

        Thread.sleep ( 20000 );

        System.out.println ( "Cliente F: " + cliente.getInetAddress ( ) +" desconectado/n " );
        
        cliente.close ( );
        }
        catch ( IOexception e ) {
        } e.printSackTrace ( );
    }  
}
 