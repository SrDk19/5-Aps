import javax.swing.JFrame;
import javax.swing.JLabel;

public class Janela{
    public static void main(String[]args){
        JFrame janela = new JFrame("My janelinha");

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1000,1000);
        janela.add(new JLabel("Top dms"));
        janela.setVisible(true);
    }
}