package co.mycompany.restaurante.cliente.presentacion;
import co.mycompany.restaurante.cliente.domain.services.UserService;
import static co.mycompany.restaurante.cliente.infra.Messages.successMessage;
/**
 *
 * @author Kevith Felipe Bastidas
 */
public class GUIPagLogin extends javax.swing.JInternalFrame {
    /**
     * Instacia de la vistaPrincipal
     */
    private final GUIPrincipal vistaPrincipal;
    /**
     * Constructor form GUILogin
     * @param vistaPrincipal
     */
    public GUIPagLogin(GUIPrincipal vistaPrincipal) {
        initComponents();
        setSize(550, 275);
        this.vistaPrincipal = vistaPrincipal;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        btnIniciarSeccion = new javax.swing.JButton();
        btnIngresarInvitado = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Iniciar Seccion");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, null));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/usuarioLogin.png"))); // NOI18N
        jLabel1.setText("Usuario");
        jPanel1.add(jLabel1);
        jPanel1.add(txtUsuario);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/usuarioPassword.png"))); // NOI18N
        jLabel2.setText("Password");
        jPanel1.add(jLabel2);
        jPanel1.add(txtClave);

        jPanel4.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnIniciarSeccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnIniciarSeccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/confirmar.png"))); // NOI18N
        btnIniciarSeccion.setText("Iniciar sesión");
        btnIniciarSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSeccionActionPerformed(evt);
            }
        });
        jPanel2.add(btnIniciarSeccion);

        btnIngresarInvitado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnIngresarInvitado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/recibo-entrada.png"))); // NOI18N
        btnIngresarInvitado.setText("Entrar Como Invitado");
        btnIngresarInvitado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarInvitadoActionPerformed(evt);
            }
        });
        jPanel2.add(btnIngresarInvitado);

        jPanel4.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/login.png"))); // NOI18N
        jLabel3.setText("LOGIN USER");
        jPanel3.add(jLabel3);

        jPanel4.add(jPanel3, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel4);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * inicia seccion siempre y cuando exista el usuario y constraseña sea correcta
     * @param evt 
     */
    private void btnIniciarSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSeccionActionPerformed
        // TODO add your handling code here:
        if (UserService.autenticacion(txtUsuario.getText(), txtClave.getText())) {
            successMessage("Inicio Seccion Correctamente.", "Atención");
            vistaPrincipal.controlarSeccion();
            this.dispose();
        }
    }//GEN-LAST:event_btnIniciarSeccionActionPerformed
    /**
     * Inicia seccion como invitado.
     * @param evt 
     */
    private void btnIngresarInvitadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarInvitadoActionPerformed
        // TODO add your handling code here:
        UserService.ponerUserInvitado();
        successMessage("Inicio Seccion Como Invitado.", "Atención");
        vistaPrincipal.controlarSeccion();
        this.dispose();
    }//GEN-LAST:event_btnIngresarInvitadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresarInvitado;
    private javax.swing.JButton btnIniciarSeccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
