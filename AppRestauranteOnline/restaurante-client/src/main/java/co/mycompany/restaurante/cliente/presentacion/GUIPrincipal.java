package co.mycompany.restaurante.cliente.presentacion;
import co.mycompany.restaurante.cliente.domain.services.RestauranteService;
import co.mycompany.restaurante.cliente.domain.services.UserService;
import static co.mycompany.restaurante.cliente.infra.Messages.successMessage;
import static co.mycompany.restaurante.cliente.infra.Messages.warningMessage;
import co.mycompany.restaurante.cliente.infra.Security;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 *
 * @author Kevith Felipe Bastidas
 */
public class GUIPrincipal extends javax.swing.JFrame {
    /**
     * instacia la clase RestauranteService para poder acceder asus servicios
     */
    private final RestauranteService service;
    /**
     * referencia a la interfaz GUIPagMenuRestaurante
     */
    private final GUIPagMenuRestaurante menuRestaurante;   
    /**
     * Creates new form GUIPrincipal
     */
    public GUIPrincipal() {
        initComponents();
        setExtendedState(6);
        this.service = new RestauranteService();
        UserService.ponerUserInvitado();
        menuRestaurante = new GUIPagMenuRestaurante(service,this);
        controlarSeccion();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/logoRestaurante.jpg"));
        Image image = icon.getImage();
        dpDesktop = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuOpciones = new javax.swing.JMenu();
        mnuRestaurantes = new javax.swing.JMenuItem();
        mnuAgregarComponentes = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuPedidos = new javax.swing.JMenuItem();
        mnuConfiguracionCuenta = new javax.swing.JMenuItem();
        mnuCerrarSeccion = new javax.swing.JMenuItem();
        mnuInformes = new javax.swing.JMenu();
        mnuIngresosPorFecha = new javax.swing.JMenuItem();
        mnuConsultaAbierta = new javax.swing.JMenuItem();
        mnuIniciarSecion = new javax.swing.JMenu();
        mnuAyuda = new javax.swing.JMenu();
        mnuAcercaDe = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenu();
        mnuUsuarioLogueado = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restaurante Online");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout dpDesktopLayout = new javax.swing.GroupLayout(dpDesktop);
        dpDesktop.setLayout(dpDesktopLayout);
        dpDesktopLayout.setHorizontalGroup(
            dpDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );
        dpDesktopLayout.setVerticalGroup(
            dpDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        getContentPane().add(dpDesktop);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(jPanel1);

        mnuOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/opciones.png"))); // NOI18N
        mnuOpciones.setText("Opciones");

        mnuRestaurantes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuRestaurantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/menu.png"))); // NOI18N
        mnuRestaurantes.setText("Menu Restaurantes");
        mnuRestaurantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRestaurantesActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuRestaurantes);

        mnuAgregarComponentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mas.png"))); // NOI18N
        mnuAgregarComponentes.setText("AgregarComponentes");
        mnuAgregarComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAgregarComponentesActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuAgregarComponentes);
        mnuOpciones.add(jSeparator1);

        mnuPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pedido.png"))); // NOI18N
        mnuPedidos.setText("Pedidos");
        mnuPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPedidosActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuPedidos);

        mnuConfiguracionCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/configuracion.png"))); // NOI18N
        mnuConfiguracionCuenta.setText("Configuracion De Cuenta");
        mnuConfiguracionCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfiguracionCuentaActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuConfiguracionCuenta);

        mnuCerrarSeccion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuCerrarSeccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/recibo-salida.png"))); // NOI18N
        mnuCerrarSeccion.setText("Cerrar Seccion");
        mnuCerrarSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCerrarSeccionActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuCerrarSeccion);

        jMenuBar1.add(mnuOpciones);

        mnuInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/informe.png"))); // NOI18N
        mnuInformes.setText("Informes");

        mnuIngresosPorFecha.setText("Ingresos por fecha");
        mnuIngresosPorFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuIngresosPorFechaActionPerformed(evt);
            }
        });
        mnuInformes.add(mnuIngresosPorFecha);

        mnuConsultaAbierta.setText("Consulta Abierta");
        mnuConsultaAbierta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConsultaAbiertaActionPerformed(evt);
            }
        });
        mnuInformes.add(mnuConsultaAbierta);

        jMenuBar1.add(mnuInformes);

        mnuIniciarSecion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/loginMenu.png"))); // NOI18N
        mnuIniciarSecion.setText("Iniciar Seccion");
        mnuIniciarSecion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuIniciarSecionMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuIniciarSecion);

        mnuAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ayuda.png"))); // NOI18N
        mnuAyuda.setText("Ayuda");

        mnuAcercaDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuAcercaDe.setText("Acerca de");
        mnuAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAcercaDeActionPerformed(evt);
            }
        });
        mnuAyuda.add(mnuAcercaDe);

        jMenuBar1.add(mnuAyuda);

        mnuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/recibo-salida.png"))); // NOI18N
        mnuSalir.setText("Salir");
        mnuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuSalirMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuSalir);

        mnuUsuarioLogueado.setBackground(java.awt.Color.magenta);
        mnuUsuarioLogueado.setForeground(java.awt.SystemColor.activeCaption);
        mnuUsuarioLogueado.setText("Usuario_logueado");
        mnuUsuarioLogueado.setEnabled(false);
        jMenuBar1.add(mnuUsuarioLogueado);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * abre la interfaz GUIPagAgregarComponente y hace dispose a esta interfaz actual
     * @param evt 
     */
    private void mnuAgregarComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAgregarComponentesActionPerformed
        //dpDesktop.removeAll();
        GUIPagComponente vistaAgregarComponente = new GUIPagComponente(service);
        agregarComponente(vistaAgregarComponente);
        vistaAgregarComponente.show();
    }//GEN-LAST:event_mnuAgregarComponentesActionPerformed
    /**
     * Muestra los pedidos del dia - En construccion
     * @param evt 
     */
    private void mnuPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPedidosActionPerformed
        // TODO add your handling code here:
        warningMessage("En construcción....", "Atención");
    }//GEN-LAST:event_mnuPedidosActionPerformed
    /**
     * Configura la cuenta - En construccion
     * @param evt 
     */
    private void mnuConfiguracionCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConfiguracionCuentaActionPerformed
        // TODO add your handling code here:
        warningMessage("En construcción....", "Atención");
    }//GEN-LAST:event_mnuConfiguracionCuentaActionPerformed
    /**
     * Ingresos del restaurante - En construccion
     * @param evt 
     */
    private void mnuIngresosPorFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIngresosPorFechaActionPerformed
        warningMessage("En construcción....", "Atención");
    }//GEN-LAST:event_mnuIngresosPorFechaActionPerformed
    /**
     * Consulta abierta del restaurante -En contruccion
     * @param evt 
     */
    private void mnuConsultaAbiertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConsultaAbiertaActionPerformed
        warningMessage("En construcción....", "Atención");
    }//GEN-LAST:event_mnuConsultaAbiertaActionPerformed
    /**
     * Abre la interfaz GUIPagLogin para posteriormente poder iniciar seccion
     * @param evt 
     */
    private void mnuIniciarSecionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuIniciarSecionMouseClicked
        // TODO add your handling code here:
        GUIPagLogin vistaLogin = new GUIPagLogin(this);
        agregarComponente(vistaLogin);
        vistaLogin.show();
    }//GEN-LAST:event_mnuIniciarSecionMouseClicked
    /**
     * muestra informacion de la app y soporte
     * @param evt 
     */
    private void mnuAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAcercaDeActionPerformed
        // TODO add your handling code here:
        String str = "MACRESTAURANT \n"
        + "Version 1.0 - Abril 5 de 2021 \n"
        + "Universidad del Cauca";
        successMessage(str, "Acerca De");
    }//GEN-LAST:event_mnuAcercaDeActionPerformed
    /**
     * Cierra la app
     * @param evt 
     */
    private void mnuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mnuSalirMouseClicked
    /**
     * Muestra la lista de los restaurantes disponibles.
     * @param evt 
     */
    private void mnuRestaurantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRestaurantesActionPerformed
        // TODO add your handling code here:
        agregarComponente(menuRestaurante);
        menuRestaurante.show();
    }//GEN-LAST:event_mnuRestaurantesActionPerformed
    /**
     * Cierra la seccion pone el Security.usuario en VISITANTE
     * @param evt 
     */
    private void mnuCerrarSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCerrarSeccionActionPerformed
        // TODO add your handling code here:
        UserService.ponerUserInvitado();    
        controlarSeccion();
        limpiarVista();
    }//GEN-LAST:event_mnuCerrarSeccionActionPerformed
    /**
     * Main principal
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
            java.util.logging.Logger.getLogger(GUIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIPrincipal().setVisible(true);
            }
        });
    }
    /**
     * Elimina todas las vistas de la pagina principal
     */
    public void limpiarVista(){
        try {
            Component[] componentes = dpDesktop.getComponents();
            for (Component componente : componentes) {
                componente.setVisible(false);
                dpDesktop.remove(componente);
            }
        } catch (Exception e) {
        }
    }
     /**
     * Controla los privilegios de los usuaios
     */
    public void controlarSeccion(){
        if (null!=Security.usuario.getLogin()) switch (Security.usuario.getLogin()) {
            case VISITANTE:
                mnuAgregarComponentes.setVisible(false);
                mnuInformes.setVisible(false);
                mnuUsuarioLogueado.setText("User Logueado: "+Security.usuario.getUsername());
                mnuCerrarSeccion.setVisible(false);
                mnuIniciarSecion.setVisible(true);
                mnuPedidos.setVisible(false);
                mnuConfiguracionCuenta.setVisible(false);
                break;
            case CLIENTE:
                mnuUsuarioLogueado.setText("User Logueado: "+Security.usuario.getUsername());
                mnuCerrarSeccion.setVisible(true);
                mnuIniciarSecion.setVisible(false);
                mnuPedidos.setVisible(true);
                mnuConfiguracionCuenta.setVisible(true);
                break;
            case ADMINISTRADOR:
                mnuAgregarComponentes.setVisible(true);
                mnuInformes.setVisible(true);
                mnuIniciarSecion.setVisible(false);
                mnuCerrarSeccion.setVisible(true);
                mnuUsuarioLogueado.setText("User Logueado: "+Security.usuario.getUsername());
                mnuOpciones.setVisible(true);
                break;
            default:
                break;
        }
        menuRestaurante.listarRegistro();
    }
    /**
     * Agrega un componente al dpDesktop y elimina cualquier otro componente anteriormente.
     * @param componente 
     */
    public void agregarComponente(Component componente){
        limpiarVista();
        dpDesktop.add(componente);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dpDesktop;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem mnuAcercaDe;
    private javax.swing.JMenuItem mnuAgregarComponentes;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenuItem mnuCerrarSeccion;
    private javax.swing.JMenuItem mnuConfiguracionCuenta;
    private javax.swing.JMenuItem mnuConsultaAbierta;
    private javax.swing.JMenu mnuInformes;
    private javax.swing.JMenuItem mnuIngresosPorFecha;
    private javax.swing.JMenu mnuIniciarSecion;
    private javax.swing.JMenu mnuOpciones;
    private javax.swing.JMenuItem mnuPedidos;
    private javax.swing.JMenuItem mnuRestaurantes;
    private javax.swing.JMenu mnuSalir;
    private javax.swing.JMenu mnuUsuarioLogueado;
    // End of variables declaration//GEN-END:variables
}
