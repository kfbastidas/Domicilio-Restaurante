package co.mycompany.restaurante.cliente.presentacion;
import co.mycompany.restaurante.cliente.domain.services.RestauranteService;
import static co.mycompany.restaurante.cliente.infra.Messages.successMessage;
import co.mycompany.restaurante.cliente.domain.entity.Componente;
import co.mycompany.restaurante.cliente.domain.entity.TipoComponente;
/**
 *
 * @author kevith felipe bastidas
 */
public class GUIPagComponente extends javax.swing.JInternalFrame {
    /**
     * instacia la clase RestauranteService para poder acceder asus servicios
     */
    private final RestauranteService service;
    /**
     * Constructor con el parametro service
     * @param service
     */
    public GUIPagComponente(RestauranteService service) {
        initComponents();
        this.service = service;
        setSize(614, 388);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlDatosDelComponente = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblTipoComponente = new javax.swing.JLabel();
        cbxTipoComponente = new javax.swing.JComboBox<>();
        pnlTitiulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlButtons = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Agregar Componente");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        pnlDatosDelComponente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlDatosDelComponente.setLayout(new java.awt.GridLayout(3, 0));

        lblId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblId.setText("Id");
        pnlDatosDelComponente.add(lblId);

        txtId.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIdCaretUpdate(evt);
            }
        });
        pnlDatosDelComponente.add(txtId);

        lblNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("Nombre");
        pnlDatosDelComponente.add(lblNombre);

        txtNombre.setEnabled(false);
        txtNombre.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNombreCaretUpdate(evt);
            }
        });
        pnlDatosDelComponente.add(txtNombre);

        lblTipoComponente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTipoComponente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoComponente.setText("Tipo Componente");
        pnlDatosDelComponente.add(lblTipoComponente);

        cbxTipoComponente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbxTipoComponente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un tipo", "ENTRADA", "PRINCIPIO", "PROTEINA", "BEBIDA" }));
        cbxTipoComponente.setEnabled(false);
        cbxTipoComponente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbxTipoComponenteMousePressed(evt);
            }
        });
        pnlDatosDelComponente.add(cbxTipoComponente);

        jPanel1.add(pnlDatosDelComponente, java.awt.BorderLayout.CENTER);

        pnlTitiulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlTitiulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagenComponentes.png"))); // NOI18N
        lblTitulo.setText("INGRESAR LOS DATOS DEL COMPONENTE");
        pnlTitiulo.add(lblTitulo);

        jPanel1.add(pnlTitiulo, java.awt.BorderLayout.NORTH);

        pnlButtons.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlButtons.setLayout(new java.awt.GridLayout(1, 0));

        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/confirmar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        pnlButtons.add(btnAgregar);

        jPanel1.add(pnlButtons, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Activa las caja de texto nombre
     * @param evt 
     */
    private void txtIdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdCaretUpdate
        // TODO add your handling code here:
        txtNombre.setEnabled(true);
        txtNombre.setEditable(true);
    }//GEN-LAST:event_txtIdCaretUpdate
    /**
     * Activa el comboBox Componente si la caja de texto nombre esta activado
     * @param evt 
     */
    private void txtNombreCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNombreCaretUpdate
        // TODO add your handling code here:
        if(txtNombre.isEnabled() == true){
            cbxTipoComponente.setEnabled(true);
        }
    }//GEN-LAST:event_txtNombreCaretUpdate
    /**
     * Activa el button Agregar Componente si el comboBox Componente esta activado
     * @param evt 
     */
    private void cbxTipoComponenteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTipoComponenteMousePressed
        // TODO add your handling code here:
        if (cbxTipoComponente.isEnabled() == true) {
            btnAgregar.setEnabled(true);
            btnAgregar.setEnabled(true);
        }

    }//GEN-LAST:event_cbxTipoComponenteMousePressed
    /**
     * Agrega un componente a la base de datos
     * @param evt 
     */
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        Componente componente = new Componente();

        if ("".equals(txtId.getText())) {
            successMessage("Campo id vacío.", "Atención");
        }else if("".equals(txtNombre.getText())){
            successMessage("Campo Nombre vacío.", "Atención");
        }else if(cbxTipoComponente.getItemAt(cbxTipoComponente.getSelectedIndex()).equals("Seleccione un tipo")){
            successMessage("Seleccione un tipo de componente.", "Atención");
        }else{
            try {
                componente.setId(Integer.parseInt(txtId.getText()));
                componente.setNombre(txtNombre.getText());

                componente.setTipo(TipoComponente.valueOf(cbxTipoComponente.getItemAt(cbxTipoComponente.getSelectedIndex())));
                String respuesta = service.addComponente(componente);
                if (!respuesta.equals("Error, el componente con ese id y nombre ya existe")) {
                    txtId.setText("");
                    txtNombre.setText("");
                    cbxTipoComponente.setSelectedIndex(0);

                    txtNombre.setEnabled(false);
                    cbxTipoComponente.setEnabled(false);
                    btnAgregar.setEnabled(false);
                }
                successMessage(respuesta, "Atención");

            } catch (NumberFormatException ex) {
                successMessage("Id incorrecto", "Atención");
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JComboBox<String> cbxTipoComponente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTipoComponente;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlDatosDelComponente;
    private javax.swing.JPanel pnlTitiulo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
