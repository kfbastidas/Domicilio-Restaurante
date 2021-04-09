/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mycompany.restaurante.cliente.presentacion;
import co.mycompany.restaurante.cliente.domain.services.RestauranteService;
import static co.mycompany.restaurante.cliente.infra.Messages.warningMessage;
import co.mycompany.restaurante.cliente.infra.Observer;
import co.mycompany.restaurante.cliente.infra.Security;
import co.mycompany.restaurante.commons.domain.Componente;
import co.mycompany.restaurante.commons.domain.DiaSemana;
import co.mycompany.restaurante.commons.domain.Restaurante;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author kevith felipe bastidas
 */
public class GUIPagMenuRestaurante extends javax.swing.JFrame implements Observer{
    /**
     * patron singleton, instancia de la misma clase 
     */
    private static GUIPagMenuRestaurante instance;
    private RestauranteService service;
    private DiaSemana dia;
    /**
     * atributo: restaurante con el que trabaja la interfaz
     */
    private Restaurante restaurante;
    /**
     * Constructor privado: new form GUIMenuRestaurante
     */
    private GUIPagMenuRestaurante() {
        initComponents();
        setSize(694,377);
        setLocationRelativeTo(null);
    }
    /**
     * patron singleton: deveulve instancia unica si no crea la unica instancia
     * @param restaurante 
     * @param service 
     * @param dia 
     * @return 
     */
    public static GUIPagMenuRestaurante getInstance(Restaurante restaurante,RestauranteService service,DiaSemana dia){
        if (instance==null) {
            instance = new GUIPagMenuRestaurante();
            instance.service = service;
        }
        instance.restaurante = restaurante;
        instance.dia = dia;
        instance.actualizarDatos();
        return instance;
    }
    /**
     * get un vector byte[] de una ruta especificada
     * @param pathname ruta de la imagen
     * @return -
     */
    private byte[] getImagen(String pathname) {
        File archivo = new File(pathname);
        byte[] image = new byte[(int) archivo.length()];
        try {
            FileInputStream entrada = new FileInputStream(archivo);
            entrada.read(image);
        } catch (Exception e) {
        }
        return image;
    }
    /**
     * convierte un vector de byte[] en una ImageIcon con un ancho y alto
     * especificado
     * @param imagenEnbyte
     * @param width
     * @param high
     * @return una ImageIcon
     */
    private ImageIcon getImageIcon(byte[] imagenEnbyte,int width,int height) {
        try {
            InputStream in = new ByteArrayInputStream(imagenEnbyte);
            BufferedImage image = ImageIO.read(in);
            ImageIcon img = new ImageIcon(image.getScaledInstance(width, height, 0));
            return img;
        } catch (Exception e) {
            try {
                InputStream in = new ByteArrayInputStream(getImagen("src/main/java/resources/sincaratula.jpg"));
                BufferedImage image = ImageIO.read(in);
                ImageIcon img = new ImageIcon(image.getScaledInstance(width, height, 0));
                return img;
            } catch (Exception e2) {
            }
            return null;
        }
    }
    /**
     * Actualiza los datos que maneja la interfaz actual
     * @param restaurante 
     */
    private void actualizarDatos() {
        if (Security.usuario != null) {
            if (Security.usuario.getLogin().equals("administrador")) {
                btnGenerarPedido.setVisible(false);
                btnVolver.setVisible(false);
                lblDatosDelRestaurante.setText("<html><b>VISTA PREVIA DEL PLATO EJECUTIVO</b></html>");
            } else {
                btnGenerarPedido.setVisible(true);
                btnVolver.setVisible(true);
                lblDatosDelRestaurante.setText("<html><b>RESTAURANTE:</b>"
                        + "    " + restaurante.getNombre() + "  <b>CIUDAD:</b>    " + restaurante.getCiudad() + "  <b>TELEFONO:</b>    "
                        + "" + restaurante.getTelefono() + "</html>");
            }
        } else {
            btnGenerarPedido.setVisible(true);
            btnVolver.setVisible(true);
            lblDatosDelRestaurante.setText("<html><b>RESTAURANTE:</b>"
                    + "    " + restaurante.getNombre() + "  <b>CIUDAD:</b>    " + restaurante.getCiudad() + "  <b>TELEFONO:</b>    "
                    + "" + restaurante.getTelefono() + "</html>");
        }
        
        lblImagen.setText("");
        lblImagen.setIcon(getImageIcon(getImagen("src/main/java/resources/plato"+restaurante.getId()+dia + "1.jpg"), 300, 300));
        
        lblDescripcion.setText(restaurante.getPlato().getDescripcion());
        //ENTRADA
        cbxEntrada.removeAllItems();
        cbxEntrada.addItem("Seleccione una opcion");
        for (Componente componente : restaurante.getMenuSemanal()) 
            if (componente.getTipo().name().equals("ENTRADA")) 
                cbxEntrada.addItem(componente.getNombre());
        //PRINCIPIO
        cbxPrincipio.removeAllItems();
        cbxPrincipio.addItem("Seleccione una opcion");
        for (Componente componente : restaurante.getMenuSemanal()) 
            if (componente.getTipo().name().equals("PRINCIPIO")) 
                cbxPrincipio.addItem(componente.getNombre());
        //PROTEINA
        cbxProteina.removeAllItems();
        cbxProteina.addItem("Seleccione una opcion");
        for (Componente componente : restaurante.getMenuSemanal()) 
            if (componente.getTipo().name().equals("PROTEINA")) 
                cbxProteina.addItem(componente.getNombre());
        //BEBIDA
        cbxBebida.removeAllItems();
        cbxBebida.addItem("Seleccione una opcion");
        for (Componente componente : restaurante.getMenuSemanal())
            if (componente.getTipo().name().equals("BEBIDA"))
                cbxBebida.addItem(componente.getNombre());
    }
    /**
     * Patron observe: actualiza el registro cuando notifique el observado
     * @param o 
     */
    @Override
    public void update(Object o) {
        ArrayList<Restaurante> restaurantes = service.getRestaurantes();
        for (Restaurante r : restaurantes) {
            if (r.getId()==(int)o) {
                this.restaurante = r;
                this.restaurante.setMenuSemanal(service.getMenuComponentes(r.getId(), dia));
                actualizarDatos();
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

        jPanel1 = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lblDescripcion = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxEntrada = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbxPrincipio = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxProteina = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbxBebida = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        lblDatosDelRestaurante = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnGenerarPedido = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(6);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        lblImagen.setText("imagen");
        jPanel1.add(lblImagen);

        lblDescripcion.setEditable(false);
        jScrollPane2.setViewportView(lblDescripcion);

        jPanel1.add(jScrollPane2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.GridLayout(8, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ENTRADA");
        jPanel2.add(jLabel1);

        cbxEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cbxEntrada);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PRINCIPIO");
        jPanel2.add(jLabel2);

        cbxPrincipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cbxPrincipio);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("PROTEINA");
        jPanel2.add(jLabel3);

        cbxProteina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cbxProteina);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("BEBIDA");
        jPanel2.add(jLabel4);

        cbxBebida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cbxBebida);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDatosDelRestaurante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDatosDelRestaurante.setText("RESTAURANTE: NOMBRE");
        jPanel3.add(lblDatosDelRestaurante);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnGenerarPedido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGenerarPedido.setText("Generar Pedido");
        btnGenerarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPedidoActionPerformed(evt);
            }
        });
        jPanel4.add(btnGenerarPedido);

        btnVolver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVolver.setText("Volver Menu Principal");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel4.add(btnVolver);

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Se devuelve a la interfaz principal y hace dispose a la interfaz actual
     * @param evt 
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        GUIPagPrincipalRestaurantes.getInstance(service).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed
    /**
     * Genera el pedido del cliente - En construccion
     * @param evt 
     */
    private void btnGenerarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPedidoActionPerformed
        // TODO add your handling code here:
        warningMessage("En construcción....", "Atención");      
    }//GEN-LAST:event_btnGenerarPedidoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarPedido;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbxBebida;
    private javax.swing.JComboBox<String> cbxEntrada;
    private javax.swing.JComboBox<String> cbxPrincipio;
    private javax.swing.JComboBox<String> cbxProteina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDatosDelRestaurante;
    private javax.swing.JTextPane lblDescripcion;
    private javax.swing.JLabel lblImagen;
    // End of variables declaration//GEN-END:variables

    
}
