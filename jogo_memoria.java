import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class JogoMemoriaRosa extends JFrame {
    private JButton[] botoes;
    private String[] simbolos;
    private JButton primeiroBotao, segundoBotao;
    private Timer timer;
    private int acertos = 0;

    public JogoMemoriaRosa() {
        setTitle(" ⋆｡˚୨୧˚｡⋆Jogo da Memória⋆｡˚୨୧˚｡⋆");
        setSize(400, 400);
        setLayout(new GridLayout(4, 4));
        getContentPane().setBackground(new Color(255, 192, 203)); // rosa claro
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        botoes = new JButton[16];
        simbolos = gerarSimbolos();

        for (int i = 0; i < 16; i++) {
            botoes[i] = new JButton("?");
            botoes[i].setFont(new Font("Arial", Font.BOLD, 20));
            botoes[i].setBackground(new Color(255, 182, 193)); // rosa suave
            botoes[i].setFocusPainted(false);
            botoes[i].addActionListener(new BotaoListener(i));
            add(botoes[i]);
        }

        setVisible(true);
    }

    private String[] gerarSimbolos() {
        String[] base = {"🌸", "💗", "🎀", "💖", "🌷", "🦋", "🍓", "💕"};
        ArrayList<String> lista = new ArrayList<>();
        for (String s : base) {
            lista.add(s);
            lista.add(s); // adiciona o par
        }
        Collections.shuffle(lista);
        return lista.toArray(new String[0]);
    }

    private class BotaoListener implements ActionListener {
        private int index;

        public BotaoListener(int i) {
            this.index = i;
        }

        public void actionPerformed(ActionEvent e) {
            JButton botao = botoes[index];
            botao.setText(simbolos[index]);
            botao.setEnabled(false);

            if (primeiroBotao == null) {
                primeiroBotao = botao;
            } else {
                segundoBotao = botao;
                verificarPar();
            }
        }
    }

    private void verificarPar() {
        if (primeiroBotao.getText().equals(segundoBotao.getText())) {
            acertos++;
            primeiroBotao = null;
            segundoBotao = null;

            if (acertos == 8) {
                JOptionPane.showMessageDialog(this, "Parabéns! Você venceu!");
            }
        } else {
            timer = new Timer(800, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    primeiroBotao.setText("?");
                    segundoBotao.setText("?");
                    primeiroBotao.setEnabled(true);
                    segundoBotao.setEnabled(true);
                    primeiroBotao = null;
                    segundoBotao = null;
                    timer.stop();
                }
            });
            timer.start();
        }
    }

    public static void main(String[] args) {
        new JogoMemoriaRosa();
    }
}
