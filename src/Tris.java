import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Tris extends JFrame {
	private static final long serialVersionUID = 1L;
    private JButton[][] bottoni;
    private JButton reset;
    private JPanel pannello;
    private JLabel titolo;
    private final char x = 'X';
    private final char o = 'O';
    private boolean isX = true;
    private boolean vittoria = false;

    public Tris() {
        super("Tris");
        creaGui();
    }

    public void creaGui() {
        bottoni = new JButton[3][3];
        reset = new JButton();
        pannello = new JPanel(new GridLayout(3, 3));
        titolo = new JLabel();
        
        JOptionPane.showMessageDialog(this, "Regole del gioco:"+
        "\n1)Due giocatori che si alternano ogni volta"+
        "\n2)Il primo a giocare ha la X, in caso doveste resettarlo parte l'altro giocatore(O)"+
        "\n3)Divertitevi!");

        this.setSize(500, 600);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        reset.setText("RESET");
        this.add(reset, BorderLayout.SOUTH);
        
        titolo.setText("TRIS");
        titolo.setFont(new Font("Courier New", Font.BOLD, 40));
        this.add(titolo, BorderLayout.NORTH);
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        

        this.add(pannello);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bottoni[i][j] = new JButton();
                pannello.add(bottoni[i][j]);
                bottoni[i][j].setFont(new Font("Courier New", Font.BOLD, 100));
                bottoni[i][j].addActionListener(new AscoltaBottoni());
            }
        }
        
        reset.addActionListener(new AscoltaReset());
        this.setVisible(true);
    }

    /**
     * assegna un valore al bottone schiacciato
     * 'X' o 'O'
     */
    class AscoltaBottoni implements ActionListener {
        public void actionPerformed(ActionEvent e) {    
            if (((JButton) e.getSource()).getText().equals("") && vittoria == false) {
                if (isX == true)((JButton) e.getSource()).setText(String.valueOf(x));
                else ((JButton) e.getSource()).setText(String.valueOf(o));
                vittoria = vittoria(); 
                isX = !isX;
            }
        }
    }
    
    /**
     * questo metodo serve per determinare un possibile giocatore o un pareggio
     * @return
     */
    public boolean vittoria() {
        for (int i = 0; i < 3; i++) {
            if (!bottoni[i][0].getText().equals("") && bottoni[i][0].getText().equals(bottoni[i][1].getText()) && bottoni[i][1].getText().equals(bottoni[i][2].getText())) {
                JOptionPane.showMessageDialog(this, "Ha vinto " + bottoni[i][0].getText());
                return true; //ritorna un booleano che servirà poi per finire il gioco che è stato trovato il vincitore
            }
        }

        for (int j = 0; j < 3; j++) {
            if (!bottoni[0][j].getText().equals("") && bottoni[0][j].getText().equals(bottoni[1][j].getText()) && bottoni[1][j].getText().equals(bottoni[2][j].getText())) {
                JOptionPane.showMessageDialog(this, "Ha vinto " + bottoni[0][j].getText());
                return true; 
            }
        }
        if (!bottoni[0][0].getText().equals("") && bottoni[0][0].getText().equals(bottoni[1][1].getText()) && bottoni[1][1].getText().equals(bottoni[2][2].getText())) {
            JOptionPane.showMessageDialog(this, "Ha vinto " + bottoni[0][0].getText());
            return true; 
        }

        if (!bottoni[0][2].getText().equals("") && bottoni[0][2].getText().equals(bottoni[1][1].getText()) && bottoni[1][1].getText().equals(bottoni[2][0].getText())) {
            JOptionPane.showMessageDialog(this, "Ha vinto " + bottoni[0][2].getText());
            return true; 
        }
        
        boolean pareggio = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (bottoni[i][j].getText().equals("")) {
                	pareggio = false;
                    break;
                }
            }
        }

        if (pareggio == true){
            JOptionPane.showMessageDialog(this, "Pareggio... Ricominciate!");
            return true;
        }
        return false;
    }
    
    /**
     * quando viene schiacciato il reset, il gioco ricomincia da 'O'
     */
    class AscoltaReset implements ActionListener{
    	public void actionPerformed (ActionEvent e) {
    		for(int i = 0; i < bottoni.length; i++) {
    			for(int j = 0; j < bottoni[i].length; j++) {
    				bottoni[i][j].setText("");
    				vittoria = false;
                    //isX = true; //questo comando serve per farlo ripartire da 'X'
    			}
    		}
    	}
    }

    public static void main(String args[]) {
        new Tris();
    }
}
