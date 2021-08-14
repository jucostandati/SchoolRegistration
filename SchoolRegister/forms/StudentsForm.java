/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchoolRegister.forms;

import java.sql.*;
import SchoolRegister.dal.DataConector;
import javax.swing.JOptionPane;
// import Recurso da Biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

/**
 *
 * @author caule
 */
public class StudentsForm extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form StudentsForm
     */
    public StudentsForm() {
        initComponents();
        conexao = DataConector.conector();
    }

    //Metodo para adicionar Estudantes
    private void add() {
        String sql = "insert into alunos (idaluno,nome,apelido,email) values (?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtStudId.getText());
            pst.setString(2, txtStudFMname.getText());
            pst.setString(3, txtStudLname.getText());
            pst.setString(4, txtStudEmail.getText());

//validação dos campos
            if ((txtStudId.getText().isEmpty()) || (txtStudFMname.getText().isEmpty() || txtStudLname.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Fill in all the required fields ");
            } else {

                //Escrevendo na tabela utilizadores os dados digitados
                //Confirmar a inserção dos dados na base de dados
                int added = pst.executeUpdate();
                //linha de apoio ao entendimento ao código
                // System.out.println(added);
                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "Student added");
                    txtStudId.setText(null);
                    txtStudFMname.setText(null);
                    txtStudLname.setText(null);
                    txtStudEmail.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
// Método para Pesquisar alunos.

    private void search_student() {
        String sql = "select * from alunos where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //Passando o que foi digitado para buscar e o %
            pst.setString(1, txtStudSearch.getText() + "%");
            rs = pst.executeQuery();
            //Preenchendo a tabela com a rs2xml
            tblStudents.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para setar os campos do form com os dados da tabela
    public void set_fields() {

        int set = tblStudents.getSelectedRow();
        txtStudId.setText(tblStudents.getModel().getValueAt(set, 0).toString());
        txtStudFMname.setText(tblStudents.getModel().getValueAt(set, 1).toString());
        txtStudLname.setText(tblStudents.getModel().getValueAt(set, 2).toString());
        txtStudEmail.setText(tblStudents.getModel().getValueAt(set, 3).toString());

        //Desabilitar o botão adicionar durante o set_fields
        btnStudCreate.setEnabled(false);
    }

    //Método para alterar dados dos alunos
    private void alter_student() {
        String sql = "update alunos set nome=?, apelido=?, email=? where idaluno=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtStudFMname.getText());
            pst.setString(2, txtStudLname.getText());
            pst.setString(3, txtStudEmail.getText());
            pst.setString(4, txtStudId.getText());
            //Não pode actualizar com os campos vazios, verificar
            if (txtStudFMname.getText().isEmpty() || txtStudLname.getText().isEmpty() || txtStudEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill out all requirid fields");
            } else {
                pst.executeUpdate();

                //Confirmar a alteração
                int updated = pst.executeUpdate();
                if (updated > 0) {
                    JOptionPane.showMessageDialog(null, "Updated");
                    //Depois limpa os campos
                    txtStudId.setText(null);
                    txtStudFMname.setText(null);
                    txtStudLname.setText(null);
                    txtStudEmail.setText(null);
                    btnStudCreate.setEnabled(true);
                    tblStudents.updateUI();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //Método para apagar Student
    private void delete_Student() {
        String sql = "delete from alunos where idaluno=?";
        //Primeiro a mensagem de confirmação antes de apagar.
        int confirm = JOptionPane.showConfirmDialog(null, "Do want delete?",
                 "Atenttion", JOptionPane.YES_NO_CANCEL_OPTION);
        // Executando se a escolha for yes
        if (confirm == JOptionPane.YES_OPTION) {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtStudId.getText());
                // Definir a mensagem caso dor apagado com sucesso
                int deleted = pst.executeUpdate();
                if (deleted > 0) {
                    JOptionPane.showMessageDialog(null, "Student deleted");
                    //Depois apaga os campos
                    txtStudId.setText(null);
                    txtStudFMname.setText(null);
                    txtStudLname.setText(null);
                    txtStudEmail.setText(null);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtStudFMname = new javax.swing.JTextField();
        txtStudLname = new javax.swing.JTextField();
        txtStudEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnStudCreate = new javax.swing.JButton();
        btnStudAlter = new javax.swing.JButton();
        btnStudDelete = new javax.swing.JButton();
        txtStudSearch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        txtStudId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Students");
        setPreferredSize(new java.awt.Dimension(770, 530));

        jLabel1.setText("* First and Middle name");

        jLabel2.setText("* Last name");

        jLabel3.setText("Email");

        txtStudFMname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudFMnameActionPerformed(evt);
            }
        });

        jLabel4.setText("* Requerid fields");

        btnStudCreate.setText("Add");
        btnStudCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudCreateActionPerformed(evt);
            }
        });

        btnStudAlter.setText("Alter");
        btnStudAlter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudAlterActionPerformed(evt);
            }
        });

        btnStudDelete.setText("Delete");
        btnStudDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudDeleteActionPerformed(evt);
            }
        });

        txtStudSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudSearchActionPerformed(evt);
            }
        });
        txtStudSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStudSearchKeyReleased(evt);
            }
        });

        jLabel5.setText("Search");

        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblStudents);

        txtStudId.setEnabled(false);

        jLabel6.setText("Id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStudCreate)
                .addGap(18, 18, 18)
                .addComponent(btnStudAlter)
                .addGap(18, 18, 18)
                .addComponent(btnStudDelete)
                .addGap(82, 82, 82))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtStudLname, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtStudSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStudFMname)
                            .addComponent(txtStudEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStudId, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStudSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStudId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtStudFMname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtStudLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtStudEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStudCreate)
                    .addComponent(btnStudAlter)
                    .addComponent(btnStudDelete))
                .addGap(54, 54, 54))
        );

        setBounds(0, 0, 770, 520);
    }// </editor-fold>//GEN-END:initComponents

    private void txtStudFMnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudFMnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudFMnameActionPerformed

    private void txtStudSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudSearchActionPerformed

    private void btnStudCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudCreateActionPerformed
        //Chamar o Método para adicionar Students
        add();
    }//GEN-LAST:event_btnStudCreateActionPerformed

    private void txtStudSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudSearchKeyReleased
        // TOEvento abiaxo é do tipo enquanto for digitando
        search_student();
    }//GEN-LAST:event_txtStudSearchKeyReleased

    private void tblStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentsMouseClicked
        // Evento usado para setar os campos da tabela no form, por meio do clique
        set_fields();
    }//GEN-LAST:event_tblStudentsMouseClicked

    private void btnStudAlterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudAlterActionPerformed
        // Chamar o metodo alter_student
        alter_student();
    }//GEN-LAST:event_btnStudAlterActionPerformed

    private void btnStudDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudDeleteActionPerformed
        // Método para apagar Student
        delete_Student();
    }//GEN-LAST:event_btnStudDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStudAlter;
    private javax.swing.JButton btnStudCreate;
    private javax.swing.JButton btnStudDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txtStudEmail;
    private javax.swing.JTextField txtStudFMname;
    private javax.swing.JTextField txtStudId;
    private javax.swing.JTextField txtStudLname;
    private javax.swing.JTextField txtStudSearch;
    // End of variables declaration//GEN-END:variables
}
