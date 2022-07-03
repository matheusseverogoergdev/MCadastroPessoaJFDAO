/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senactech.MCadastroPessoa.view;

import br.com.senactech.MCadastroPessoa.model.Pessoa;
import javax.swing.JOptionPane;

import br.com.senactech.MCadastroPessoa.util.ValidaCPF;
import java.util.regex.PatternSyntaxException;
import javax.swing.JButton;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static mcadastropessoaJF.MCadastroPessoaJF.cadPessoas;
import br.com.senactech.MCadastroPessoa.services.PessoaServicos;
import br.com.senactech.MCadastroPessoa.services.ServicosFactory;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jairb
 */
public class pessoaCadastro extends javax.swing.JFrame {

    JButton btnClick = null;

    /**
     * Creates new form pessoaCadastro
     */
    public pessoaCadastro() throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
//        cadPessoas.mokPessoas();
        addRowToTableBD();
    }

    public void addRowToTable() {
        //Cria obj model e recebe a modelagem da tabela JtPessoa do JFrame
        DefaultTableModel model = (DefaultTableModel) jtPessoas.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        Object rowData[] = new Object[4]; //cria vetor para as colunas da tabela
        for (Pessoa p : cadPessoas.getAll()) {
            rowData[0] = p.getNomePessoa();
            rowData[1] = p.getCpf();
            rowData[2] = p.getTelefone();
            if (p.isStatus()) {
                rowData[3] = "Ativo";
            } else {
                rowData[3] = "Inativo";
            }
            model.addRow(rowData);
        }
    }
    
    public void addRowToTableBD() throws SQLException {
        //Cria obj model e recebe a modelagem da tabela JtPessoa do JFrame
        DefaultTableModel model = (DefaultTableModel) jtPessoas.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        Object rowData[] = new Object[4]; //cria vetor para as colunas da tabela
        PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
        for (Pessoa p : pessoaS.getPessoas()) {
            rowData[0] = p.getNomePessoa();
            rowData[1] = p.getCpf();
            rowData[2] = p.getTelefone();
            if (p.isStatus()) {
                rowData[3] = "Ativo";
            } else {
                rowData[3] = "Inativo";
            }
            model.addRow(rowData);
        }
    }

    public void jTableFilterClear() {
        DefaultTableModel model = (DefaultTableModel) jtPessoas.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        jtPessoas.setRowSorter(sorter);
        sorter.setRowFilter(null);
    }

    public Boolean validaInputs() {
        String telefone = jtfTelefone.getText();
        if (jtfNome.getText().isEmpty()
                || jtfCPFFormated.getText().isEmpty()
                || jtfEndereco.getText().isEmpty()
                || jtfIdade.getText().isEmpty()
                || jtfTelefone.getText().isEmpty()
                || bgStatus.getSelection() == null) {
            JOptionPane.showMessageDialog(this,
                    "Todos os campos devem ser preenchidos!",
                    ".: Erro :.", JOptionPane.ERROR_MESSAGE);
            jtfNome.requestFocus();
            return false;
        }
        if (telefone.length() < 14) {
            JOptionPane.showMessageDialog(this,
                    "Telefone informado esta incorreto",
                    ".: Erro :.", JOptionPane.ERROR_MESSAGE);
            jtfTelefone.requestFocus();
            return false;
        }
        if (!jtfIdade.getText().isEmpty()) {
            int idade = Integer.parseInt(jtfIdade.getText());
            if (idade == 0 || idade > 120) {
                JOptionPane.showMessageDialog(this,
                        "Idade informada esta incorreta!",
                        ".: Erro :.", JOptionPane.ERROR_MESSAGE);
                jtfIdade.requestFocus();
                return false;
            }
        }
        
        if (btnClick.getText() == "Salvar") {
            PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
            String cpf = jtfCPFFormated.getText().substring(0, 3)
                    + jtfCPFFormated.getText().substring(4, 7)
                    + jtfCPFFormated.getText().substring(8, 11)
                    + jtfCPFFormated.getText().substring(12, 14);
            if (!ValidaCPF.isCPF(cpf)) {
                JOptionPane.showMessageDialog(this,
                        "CPF informado esta incorreto!!!",
                        ".: Erro :.", JOptionPane.ERROR_MESSAGE);
                jtfCPFFormated.requestFocus();
                return false;
            } else try {
                if (pessoaS.verCPF(jtfCPFFormated.getText())) {
                    JOptionPane.showMessageDialog(this,
                            "CPF já cadastrado!!!",
                            ".: Erro :.", JOptionPane.ERROR_MESSAGE);
                    jtfCPFFormated.requestFocus();
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(pessoaCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgStatus = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtfEndereco = new javax.swing.JTextField();
        jtfNome = new javax.swing.JTextField();
        jtfIdade = new javax.swing.JTextField();
        jrbAtivo = new javax.swing.JRadioButton();
        jrbInativo = new javax.swing.JRadioButton();
        jbSalvar = new javax.swing.JButton();
        jbLimpar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPessoas = new javax.swing.JTable();
        jbSair = new javax.swing.JButton();
        jbDeletar = new javax.swing.JButton();
        jbConfirmar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbPesqCPF = new javax.swing.JButton();
        jtfTelefone = new javax.swing.JFormattedTextField();
        jtfCPFFormated = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro Pessoa");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cadastro Pessoa");

        jSeparator1.setForeground(new java.awt.Color(255, 204, 51));

        jLabel1.setText("Nome:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Endereço:");

        jLabel5.setText("Celular:");

        jLabel6.setText("Idade:");

        jLabel7.setText("Status:");

        jtfEndereco.setToolTipText("Endereço completo");
        jtfEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEnderecoFocusLost(evt);
            }
        });
        jtfEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfEnderecoActionPerformed(evt);
            }
        });

        jtfNome.setToolTipText("Nome completo");
        jtfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfNomeFocusLost(evt);
            }
        });
        jtfNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfNomeKeyTyped(evt);
            }
        });

        jtfIdade.setToolTipText("Somente números");
        jtfIdade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfIdadeKeyTyped(evt);
            }
        });

        bgStatus.add(jrbAtivo);
        jrbAtivo.setText("Ativo");

        bgStatus.add(jrbInativo);
        jrbInativo.setText("Inativo");

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbLimpar.setText("Limpar");
        jbLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimparActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(255, 204, 0));

        jtPessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Telefone", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPessoas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPessoasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtPessoas);

        jbSair.setText("Sair");
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });

        jbDeletar.setText("Deletar");
        jbDeletar.setEnabled(false);
        jbDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDeletarActionPerformed(evt);
            }
        });

        jbConfirmar.setText("Confirmar");
        jbConfirmar.setEnabled(false);
        jbConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConfirmarActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbPesqCPF.setForeground(new java.awt.Color(0, 0, 255));
        jbPesqCPF.setText("Pesquisar CPF");
        jbPesqCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesqCPFActionPerformed(evt);
            }
        });

        try {
            jtfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jtfCPFFormated.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtfCPFFormated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCPFFormatedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7))
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jrbAtivo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jrbInativo))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jtfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jtfIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jtfCPFFormated, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jbPesqCPF))
                                            .addComponent(jtfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 18, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(27, 27, 27)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jbSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbLimpar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jbDeletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbSair))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jbPesqCPF)
                    .addComponent(jtfCPFFormated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jtfIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jrbAtivo)
                    .addComponent(jrbInativo))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(jbLimpar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSair)
                    .addComponent(jbDeletar)
                    .addComponent(jbConfirmar)
                    .addComponent(jbEditar))
                .addGap(187, 187, 187))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimparActionPerformed
        // TODO add your handling code here:
        jtfCPFFormated.setText("");
        jtfEndereco.setText("");
        jtfIdade.setText("");
        jtfNome.setText("");
        jtfTelefone.setText("");
        bgStatus.clearSelection();
        jtfNome.requestFocus();

        jtfCPFFormated.setEnabled(true);
        jbSalvar.setEnabled(true);
        jbPesqCPF.setEnabled(true);
        jbEditar.setEnabled(false);
        jbConfirmar.setEnabled(false);
        jbDeletar.setEnabled(false);
        
        jTableFilterClear();
    }//GEN-LAST:event_jbLimparActionPerformed

    private void jtfIdadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfIdadeKeyTyped
        // TODO add your handling code here:
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jtfIdadeKeyTyped

    /**
     * jtfNomeKeyTyped Aceita qualquer tecla diferente da String abaixo
     *
     * @param evt
     */
    private void jtfNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNomeKeyTyped
        // TODO add your handling code here:
        String caracteres = "0987654321/[]{}=+-_)(*&¨%$#@!<>;:?";
        if (caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jtfNomeKeyTyped

    /**
     * jtfTelefoneKeyTyped Aceita somente números
     *
     * @param evt
     */
    private void jtfTelefoneKeyTyped(java.awt.event.KeyEvent evt) {                                     
        // TODO add your handling code here:
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }                                    
  
    
    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        // TODO add your handling code here:
        btnClick = (JButton) evt.getSource();
        System.out.println(btnClick.getText());

        if (validaInputs()) {
            int id = cadPessoas.gerarId();
            String nomePessoa = jtfNome.getText();
            String cpf = jtfCPFFormated.getText();
            String endereco = jtfEndereco.getText();
            String telefone = jtfTelefone.getText();
            int idade = Integer.parseInt(jtfIdade.getText());
            boolean status = jrbAtivo.isSelected();

            Pessoa p = new Pessoa(id, nomePessoa, cpf, endereco, telefone, idade, status);
//            cadPessoas.add(p);
            PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
            
            try {
                pessoaS.cadPessoa(p);
                jbLimpar.doClick();
                addRowToTableBD();
                JOptionPane.showMessageDialog(this, "Pessoa foi salva com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Erro! " + ex.getMessage(),
                        "erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jbSairActionPerformed

    private void jtPessoasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPessoasMouseClicked
        // TODO add your handling code here:
        jbDeletar.setEnabled(true);
        jbEditar.setEnabled(true);
    }//GEN-LAST:event_jtPessoasMouseClicked

    private void jbDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDeletarActionPerformed
        // TODO add your handling code here:
        jbEditar.setEnabled(false);
        int linha;
        String cpf;
        linha = jtPessoas.getSelectedRow();
        cpf = (String) jtPessoas.getValueAt(linha, 1);
//        Pessoa p = cadPessoas.getByDoc(cpf);
        PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
        Pessoa p = new Pessoa();
        try {
            p = pessoaS.buscarPessoaBD(cpf);
        } catch (SQLException ex) {
            Logger.getLogger(pessoaCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
//        int resposta = JOptionPane.showConfirmDialog(this,
//                "Deseja realmente deletar " + p.getNomePessoa() + "?",
//                ".: Deletar :.", JOptionPane.YES_NO_OPTION,
//                JOptionPane.QUESTION_MESSAGE);
        Object[] resp = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(this,
                "Deseja realmente deletar " + p.getNomePessoa() + "?",
                ".: Deletar :.", JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE, null, resp, resp[0]);
        if (resposta == 0) {
            try {
                //            cadPessoas.deletar(p);
                pessoaS.deletarPessoaBD(p.getIdPessoa());
                //            addRowToTable();
                addRowToTableBD();
                JOptionPane.showMessageDialog(this, "Pessoa deletada com sucesso!",
                    ".: Deletar :.", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(pessoaCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Entendemos sua decisão!",
                    ".: Deletar :.", JOptionPane.INFORMATION_MESSAGE);
        }
        jbDeletar.setEnabled(false);
    }//GEN-LAST:event_jbDeletarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        try {
            // TODO add your handling code here:
            //ajustando comportamento dos botões
            jbDeletar.setEnabled(false);
            jbSalvar.setEnabled(false);
            jbEditar.setEnabled(false);
            jtfCPFFormated.setEnabled(false);
            jbPesqCPF.setEnabled(false);
            jbConfirmar.setEnabled(true);
            jbLimpar.setText("Cancelar");
            
            //carregar os dados da pessoa selecionada nos JTextFields
            int linha;
            String cpf;
            linha = jtPessoas.getSelectedRow();
            cpf = (String) jtPessoas.getValueAt(linha, 1);
            //        Pessoa p = cadPessoas.getByDoc(cpf);
            PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
            Pessoa p = pessoaS.buscarPessoaBD(cpf);

            jtfNome.setText(p.getNomePessoa());
            jtfCPFFormated.setText(p.getCpf());
            jtfEndereco.setText(p.getEndereco());
            jtfTelefone.setText(p.getTelefone());
            jtfIdade.setText(Integer.toString(p.getIdade()));
            if (p.isStatus()) {
                jrbAtivo.setSelected(true);
                jrbInativo.setSelected(false);
            } else {
                jrbAtivo.setSelected(false);
                jrbInativo.setSelected(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pessoaCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConfirmarActionPerformed
        // TODO add your handling code here:
        btnClick = (JButton) evt.getSource();
        if (validaInputs()) {
            try {
                // Pessoa p = cadPessoas.getByDoc(jtfCPFFormated.getText());
                PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
                Pessoa p = pessoaS.buscarPessoaBD(jtfCPFFormated.getText());

                p.setNomePessoa(jtfNome.getText());
//                p.setCpf(jtfCPFFormated.getText());
                p.setEndereco(jtfEndereco.getText());
                p.setIdade(Integer.parseInt(jtfIdade.getText()));
                p.setTelefone(jtfTelefone.getText());
                if (jrbAtivo.isSelected()) {
                    p.setStatus(true);
                } else {
                    p.setStatus(false);
                }
                
                pessoaS.atualizarPessoaBD(p);
                addRowToTableBD();

                jbLimpar.doClick();
                jbLimpar.setText("Limpar");

                jTableFilterClear();

                String msg = "Dados atualizados com sucesso!";
                JOptionPane.showMessageDialog(this, msg, ".: Atualizar :.",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(pessoaCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jbLimpar.doClick();
            jtfCPFFormated.setEnabled(true);
        }
    }//GEN-LAST:event_jbConfirmarActionPerformed

    private void jbPesqCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesqCPFActionPerformed
        // TODO add your handling code here:
        PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
        String cpf = jtfCPFFormated.getText().substring(0, 3)
                    + jtfCPFFormated.getText().substring(4, 7)
                    + jtfCPFFormated.getText().substring(8, 11)
                    + jtfCPFFormated.getText().substring(12, 14);
        
        if (!ValidaCPF.isCPF(cpf)) {
            JOptionPane.showMessageDialog(this,
                    "CPF informado esta incorreto!!!",
                    ".: Erro :.", JOptionPane.ERROR_MESSAGE);
            jtfCPFFormated.requestFocus();
        } else try {
            if (pessoaS.verCPF(jtfCPFFormated.getText())) {
                DefaultTableModel model = (DefaultTableModel) jtPessoas.getModel();
                final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
                jtPessoas.setRowSorter(sorter);
                String text = jtfCPFFormated.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(this, "Registro não encontrado!");
                    }
                }

//            Pessoa p = cadPessoas.getByDoc(jtfCPFFormated.getText());
//
//            jtfNome.setText(p.getNomePessoa());
//            jtfCPFFormated.setText(p.getCpf());
//            jtfEndereco.setText(p.getEndereco());
//            jtfTelefone.setText(p.getTelefone());
//            jtfIdade.setText(Integer.toString(p.getIdade()));
//            if (p.isStatus()) {
//                jrbAtivo.setSelected(true);
//                jrbInativo.setSelected(false);
//            } else {
//                jrbAtivo.setSelected(false);
//                jrbInativo.setSelected(true);
//            }
//
//            jbConfirmar.setEnabled(true);
//            jbSalvar.setEnabled(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pessoaCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbPesqCPFActionPerformed

    private void jtfTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTelefoneActionPerformed

    private void jtfNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfNomeFocusLost
        // TODO add your handling code here:
        jtfNome.setText(jtfNome.getText().toUpperCase());
    }//GEN-LAST:event_jtfNomeFocusLost

    private void jtfEnderecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnderecoFocusLost
        // TODO add your handling code here:
        jtfEndereco.setText(jtfEndereco.getText().toUpperCase());
    }//GEN-LAST:event_jtfEnderecoFocusLost

    private void jtfCPFFormatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCPFFormatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCPFFormatedActionPerformed

    private void jtfEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfEnderecoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pessoaCadastro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pessoaCadastro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pessoaCadastro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pessoaCadastro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new pessoaCadastro().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(pessoaCadastro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbConfirmar;
    private javax.swing.JButton jbDeletar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbLimpar;
    private javax.swing.JButton jbPesqCPF;
    private javax.swing.JButton jbSair;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JRadioButton jrbAtivo;
    private javax.swing.JRadioButton jrbInativo;
    private javax.swing.JTable jtPessoas;
    private javax.swing.JFormattedTextField jtfCPFFormated;
    private javax.swing.JTextField jtfEndereco;
    private javax.swing.JTextField jtfIdade;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JFormattedTextField jtfTelefone;
    // End of variables declaration//GEN-END:variables

}
