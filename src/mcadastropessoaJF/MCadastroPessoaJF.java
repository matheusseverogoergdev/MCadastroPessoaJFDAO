/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcadastropessoaJF;

import br.com.senactech.MCadastroPessoa.controller.CCarro;
import br.com.senactech.MCadastroPessoa.controller.CPessoa;
import br.com.senactech.MCadastroPessoa.view.carroCadastro;
import br.com.senactech.MCadastroPessoa.view.pessoaCadastro;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jairb
 */
public class MCadastroPessoaJF implements ActionListener {

    public static CPessoa cadPessoas = new CPessoa();
    public static CCarro cadCarros = new CCarro();

    //Criar JFrame e seus componentes
    JFrame janela = new JFrame("Menu Principal");
    JPanel painel = new JPanel();
    JButton btnCadPessoa = new JButton("Cad. Pessoas");
    JButton btnCadCarros = new JButton("Cad. Carros");

    private MCadastroPessoaJF() {
        janela.setSize(350, 100);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        painel.add(btnCadPessoa);
        painel.add(btnCadCarros);
        janela.add(painel);
        janela.setVisible(true);
        btnCadPessoa.addActionListener(this);
        btnCadCarros.addActionListener(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        cadPessoas.mokPessoas();
//        cadCarros.mokCarro();
        new MCadastroPessoaJF();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadPessoa) {
            try {
                pessoaCadastro pcad = new pessoaCadastro();
                pcad.setVisible(true);
                pcad.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            } catch (SQLException ex) {
                Logger.getLogger(MCadastroPessoaJF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == btnCadCarros) {
            try {
                carroCadastro cCad = new carroCadastro();
                cCad.setVisible(true);
                cCad.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            } catch (SQLException ex) {
                Logger.getLogger(MCadastroPessoaJF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
