/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Communication.DataManager;
import static Communication.DataManager.RemoveById;
import static Communication.Engine.GetRowSize;
import static Communication.Engine.GetSingleData;
import static Communication.Engine.RowToComboBox;
import static Communication.Engine.VendaToTable;
import static Communication.Engine.isNumeric;
import Communication.LabelTimer;
import javax.swing.JOptionPane;

/**
 *
 * @author LeoFuking
 */
public class Venda extends javax.swing.JPanel {
    int venda_id;
    MainFrame mf = null;
    /** Creates new form Venda */
    public Venda(MainFrame mf) {
        this.mf = mf;
        initComponents();
        salvo.setVisible(false);
        venda_id = GetRowSize("venda")+1;
        if(mf.funcionario[0] == 1){
            vendedor.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{(String)GetSingleData("SELECT nome FROM funcionario WHERE funcionario_id = "+mf.funcionario[1],"nome")}));
        }else{
            vendedor.setModel(RowToComboBox("funcionario","nome"));
        }
        cliente.setModel(RowToComboBox("cliente","nome"));
        ResetFrame();
    }

    public void Trigger(){
        tabela.setModel(VendaToTable(venda_id));
        valor.setText(String.valueOf(GetTotalValue()));
        quantidade.setText("");
        codigo.setText("");
    }
    
    private float GetTotalValue(){
        int count = 0;
        float valorTotal = 0;
        while(tabela.getRowCount() > count){
            valorTotal += (Float.parseFloat(String.valueOf(tabela.getValueAt(count,1))));
            count++;
        }
        if(count == 0) valorTotal = (float)0.0;
        return valorTotal;
    }
    
    private void ResetFrame(){
        Object i = 0;
        i = GetSingleData("SELECT venda_id FROM venda","venda_id");
        if(i != null) venda_id = (int) i +1;
        tabela.setModel(VendaToTable(venda_id));
        cliente.setSelectedIndex(0);
        vendedor.setSelectedIndex(0);
        valor.setText(String.valueOf(GetTotalValue()));
        quantidade.setText("");
        codigo.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        limpar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cliente = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        valor = new javax.swing.JLabel();
        vendedor = new javax.swing.JComboBox<>();
        procurar = new javax.swing.JButton();
        remover = new javax.swing.JButton();
        salvar = new javax.swing.JButton();
        codigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        quantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        confirmar = new javax.swing.JButton();
        salvo = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Copperplate Gothic Light", 0, 14))); // NOI18N

        limpar.setText("Limpar");
        limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparActionPerformed(evt);
            }
        });

        jLabel1.setText("Vendedor");

        jLabel2.setText("Cliente");

        cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                clienteItemStateChanged(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Valor Total");

        valor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        valor.setText("0.00");
        valor.setToolTipText("");

        vendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        vendedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                vendedorItemStateChanged(evt);
            }
        });

        procurar.setText("Procurar");
        procurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procurarActionPerformed(evt);
            }
        });

        remover.setText("Remover");
        remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerActionPerformed(evt);
            }
        });

        salvar.setText("Salvar");
        salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarActionPerformed(evt);
            }
        });

        jLabel4.setText("Código");

        jLabel5.setText("Quantidade");

        confirmar.setText("Confirmar");
        confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarActionPerformed(evt);
            }
        });

        salvo.setForeground(new java.awt.Color(51, 204, 0));
        salvo.setText("Salvo com sucesso!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(remover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salvo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(23, 23, 23)
                                .addComponent(cliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confirmar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(procurar))
                            .addComponent(codigo))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(procurar)
                    .addComponent(jLabel5)
                    .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(limpar)
                        .addComponent(salvar)
                        .addComponent(remover)
                        .addComponent(salvo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(valor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparActionPerformed
        int count = 0;
        while(tabela.getRowCount() > count){
            RemoveById("itenvenda",String.valueOf(tabela.getValueAt(count,2)));
            count++;
        }
        tabela.setModel(VendaToTable(venda_id));
        cliente.setSelectedIndex(0);
        vendedor.setSelectedIndex(0);
        valor.setText("0.00");
    }//GEN-LAST:event_limparActionPerformed

    private void clienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_clienteItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_clienteItemStateChanged

    private void tabelaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseReleased
        if(tabela.getSelectedRowCount() > 1){
            int[] i = tabela.getSelectedRows();
            tabela.setRowSelectionInterval(i[0],i[0]);
        }
    }//GEN-LAST:event_tabelaMouseReleased

    private void vendedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_vendedorItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vendedorItemStateChanged

    private void salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarActionPerformed
        if(Float.parseFloat(valor.getText()) == 0){JOptionPane.showMessageDialog(null,"Adicione pelomenos um item");}
        else{
            if(String.valueOf(cliente.getSelectedItem()) == ""){JOptionPane.showMessageDialog(null,"Selecione um cliente");}
            else{
                if(String.valueOf(vendedor.getSelectedItem()) == ""){JOptionPane.showMessageDialog(null,"Selecione um vendedor");}
                else{
                    String[] save = new String[3];
                    save[0] = valor.getText();
                    save[1] = (String) GetSingleData("SELECT cliente_id FROM cliente WHERE nome LIKE '"+cliente.getSelectedItem()+"'","cliente_id");
                    save[2] = (String) GetSingleData("SELECT vendedor_id FROM vendedor WHERE nome LIKE '"+vendedor.getSelectedItem()+"'","vendedor_id");
                    int s1 = GetRowSize("venda");
                    DataManager.CadastrarVenda(save);
                    int s2 = GetRowSize("venda");
                    if(s2 > s1){salvo.setVisible(true);new LabelTimer(2,salvo);}
                    Trigger();
                }
            }
        }
    }//GEN-LAST:event_salvarActionPerformed

    private void removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerActionPerformed
        if(tabela.getSelectedRow() == -1){JOptionPane.showMessageDialog(null,"Selecione uma linha na tabela para remover");}
        else{
            if(tabela.getValueAt(tabela.getSelectedRow(),0) == null){JOptionPane.showMessageDialog(null,"Selecione uma linha válida");}
            else{
                RemoveById("itenvenda",(String)tabela.getValueAt(tabela.getSelectedRow(),2));
                tabela.setModel(VendaToTable(venda_id));
                valor.setText(String.valueOf(GetTotalValue()));
            }
        }
    }//GEN-LAST:event_removerActionPerformed

    private void procurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procurarActionPerformed
        ItemVenda c = new ItemVenda();
        c.venda_id = venda_id;
        c.reference = this;
        c.setVisible(true);
    }//GEN-LAST:event_procurarActionPerformed

    private void confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarActionPerformed
        if(codigo.getText().length() != 8){JOptionPane.showMessageDialog(null,"Código inválido");}
        else{
            if(!isNumeric(quantidade.getText())){JOptionPane.showMessageDialog(null,"Digite um valor válido para quantidade");}
            else{
                String bc = codigo.getText();
                String[] save = new String[4];
                save[0] = quantidade.getText();
                save[1] = String.valueOf(venda_id);
                save[2] = String.valueOf(GetSingleData("SELECT produto_id FROM produto WHERE codigo = "+bc,"produto_id"));
                save[3] = String.valueOf((float) GetSingleData("SELECT valor FROM produto WHERE codigo = "+bc,"valor")*Integer.valueOf(quantidade.getText()));
                DataManager.CadastrarItemVenda(save);
                ResetFrame();
            }
        }
    }//GEN-LAST:event_confirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cliente;
    private javax.swing.JTextField codigo;
    private javax.swing.JButton confirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpar;
    private javax.swing.JButton procurar;
    private javax.swing.JTextField quantidade;
    private javax.swing.JButton remover;
    private javax.swing.JButton salvar;
    private javax.swing.JLabel salvo;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel valor;
    private javax.swing.JComboBox<String> vendedor;
    // End of variables declaration//GEN-END:variables

}
