/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mycompany.restaurante.cliente.presentacion;
import co.mycompany.restaurante.cliente.domain.services.RestauranteService;
import co.mycompany.restaurante.cliente.domain.services.UserService;
/**
 *
 * @author Kevith Felipe Bastidas
 */
public class GUIPagLogin extends javax.swing.JFrame {
    private RestauranteService service;
    /**
     * patron singleton, instancia de la misma clase 
     */
    private static GUIPagLogin instance;
    /**
     * patron singleton
     * @param service
     * @return retorna una instancia unica de la misma clase
     */
    public static GUIPagLogin getInstance(RestauranteService service){
        if (instance==null) {
            instance = new GUIPagLogin();
            instance.service = service;
        }
        clean();
        return instance;
    }
    /**
     * limpia las cajas de texto
     */
    private static void clean(){
        instance.txtUsuario.setText("");
        instance.txtClave.setText("");
    }    
    /**
     * Constructor privado form GUILogin1
     */
    private GUIPagLogin() {
        initComponents();
        setSize(550, 250);
        setLocationRelativeTo(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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
        btnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN USER");
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, null));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setText("Usuario");
        jPanel1.add(jLabel1);
        jPanel1.add(txtUsuario);

        jLabel2.setText("Password");
        jPanel1.add(jLabel2);
        jPanel1.add(txtClave);

        jPanel4.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnIniciarSeccion.setText("Iniciar sesión");
        btnIniciarSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSeccionActionPerformed(evt);
            }
        });
        jPanel2.add(btnIniciarSeccion);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir);

        jPanel4.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
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
            this.dispose();
            GUIPagPrincipalRestaurantes ins = GUIPagPrincipalRestaurantes.getInstance(service);
            ins.listarRegistro();
            ins.setVisible(true);
        }
    }//GEN-LAST:event_btnIniciarSeccionActionPerformed
    /**
     * Cierra la interfaz y abre la interfaz principal
     * @param evt 
     */
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        GUIPagPrincipalRestaurantes.getInstance(service).setVisible(true);
        this.dispose();        
    }//GEN-LAST:event_btnSalirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSeccion;
    private javax.swing.JButton btnSalir;
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
