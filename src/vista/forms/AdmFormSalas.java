/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.forms;

/**
 *
 * @author adria
 */
public class AdmFormSalas extends javax.swing.JFrame
{

    /**
     * Creates new form AdmFormSalas
     */
    public AdmFormSalas()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        panelSalas = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblMenu = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFilas = new javax.swing.JTextField();
        txtId1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCols = new javax.swing.JTextField();
        panelSig = new javax.swing.JPanel();
        lblnext = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtEstatus = new javax.swing.JComboBox<>();
        txtId = new javax.swing.JLabel();
        panelSig1 = new javax.swing.JPanel();
        lblnext1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNumSala = new javax.swing.JTextField();

        jLabel4.setText("Filas:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelSalas.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(54, 33, 89));

        lblMenu.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(lblMenu))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(title)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMenu))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Filas:");

        txtFilas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtId1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtId1.setText("x");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Columnas:");

        txtCols.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        panelSig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblnext.setBackground(new java.awt.Color(51, 102, 255));
        lblnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/siguiente.png"))); // NOI18N

        javax.swing.GroupLayout panelSigLayout = new javax.swing.GroupLayout(panelSig);
        panelSig.setLayout(panelSigLayout);
        panelSigLayout.setHorizontalGroup(
            panelSigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSigLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblnext)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSigLayout.setVerticalGroup(
            panelSigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSigLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblnext))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tipo:");

        txtTipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2D", "3D", "4D", "Platino" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Estatus:");

        txtEstatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No disponible", "Disponible" }));
        txtEstatus.setSelectedIndex(1);

        lblnext1.setBackground(new java.awt.Color(51, 102, 255));

        javax.swing.GroupLayout panelSig1Layout = new javax.swing.GroupLayout(panelSig1);
        panelSig1.setLayout(panelSig1Layout);
        panelSig1Layout.setHorizontalGroup(
            panelSig1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSig1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblnext1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSig1Layout.setVerticalGroup(
            panelSig1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSig1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblnext1))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Num. Sala");

        txtNumSala.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelSalasLayout = new javax.swing.GroupLayout(panelSalas);
        panelSalas.setLayout(panelSalasLayout);
        panelSalasLayout.setHorizontalGroup(
            panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, Short.MAX_VALUE)
            .addGroup(panelSalasLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(panelSig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelSalasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSalasLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSalasLayout.createSequentialGroup()
                        .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelSalasLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(txtCols, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelSalasLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel6))
                            .addGroup(panelSalasLayout.createSequentialGroup()
                                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSalasLayout.createSequentialGroup()
                        .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSalasLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSalasLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNumSala, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
                            .addGroup(panelSalasLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSalasLayout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(panelSig1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(71, Short.MAX_VALUE)))
        );
        panelSalasLayout.setVerticalGroup(
            panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSalasLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelSalasLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSalasLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId1)
                    .addComponent(txtCols, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelSig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSalasLayout.createSequentialGroup()
                    .addGap(105, 105, 105)
                    .addComponent(panelSig1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(191, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSalas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSalas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JLabel lblMenu;
    public javax.swing.JLabel lblnext;
    public javax.swing.JLabel lblnext1;
    public javax.swing.JPanel panelSalas;
    public javax.swing.JPanel panelSig;
    public javax.swing.JPanel panelSig1;
    public javax.swing.JLabel title;
    public javax.swing.JTextField txtCols;
    public javax.swing.JComboBox<String> txtEstatus;
    public javax.swing.JTextField txtFilas;
    public javax.swing.JLabel txtId;
    private javax.swing.JLabel txtId1;
    public javax.swing.JTextField txtNumSala;
    public javax.swing.JComboBox<String> txtTipo;
    // End of variables declaration//GEN-END:variables
}