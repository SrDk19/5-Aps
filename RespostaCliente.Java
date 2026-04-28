import java.net.Socket;

public class RespostaCliente extends Thread{
    Socket cliente;

    RespostaCliente(Socket cli){
        this.cliente = cli;
    }
    @Override
    public void run(){
        System.out.println("Cliente conectando com thread("+this.getId()+")  :" + cliente);

        inputStreamReader fluxoDados
    }  
}
