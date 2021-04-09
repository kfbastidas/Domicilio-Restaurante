/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mycompany.restaurante.cliente.presentacion;
import co.mycompany.restaurante.cliente.domain.services.RestauranteService;
import static co.mycompany.restaurante.cliente.infra.Messages.successMessage;
import static co.mycompany.restaurante.cliente.infra.Messages.warningMessage;
import co.mycompany.restaurante.cliente.infra.Observer;
import co.mycompany.restaurante.cliente.infra.Security;
import co.mycompany.restaurante.cliente.infra.imgTabla;
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
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author kevith Felipe Bastidas
 */
public class GUIPagPrincipalRestaurantes extends javax.swing.JFrame implements Observer{

    /**
     * instacia la clase RestauranteService para poder acceder asus servicios
     */
    private RestauranteService service;
    /**
     * patron singleton, instancia de la misma clase 
     */
    private static GUIPagPrincipalRestaurantes instance;
    /**
     * atributo: obtiene toda la lista de los restaurantes
     */
    private ArrayList<Restaurante> restaurantes = new ArrayList<>();
    /**
     * patron singleton
     * @param service
     * @return retorna una instancia unica de la misma clase
     */
    public static GUIPagPrincipalRestaurantes getInstance(RestauranteService service) {
        if (instance == null) {
            instance = new GUIPagPrincipalRestaurantes();
            instance.service = service;
            instance.listarRegistro();
            instance.tabla.setDefaultRenderer(Object.class, new imgTabla());
        }
        instance.seccion();
        return instance;
    }
    /**
     * Controla los privilegios de los usuaios
     */
    private void seccion(){
        if (Security.usuario==null) {
            instance.mnuAgregarComponentes.setVisible(false);
            instance.mnuInformes.setVisible(false);
            instance.mnuUsuarioLogueado.setVisible(false);
            instance.mnuCerrarSecion.setVisible(false);
            instance.mnuIniciarSecion.setVisible(true);
            instance.mnuOpciones.setVisible(false);
            instance.mnuPedidos.setVisible(true);
            instance.mnuConfiguracionCuenta.setVisible(false);
        }else if(Security.usuario.getLogin().equals("administrador")){
            instance.mnuAgregarComponentes.setVisible(true);
            instance.mnuInformes.setVisible(true);
            instance.mnuUsuarioLogueado.setVisible(true);
            instance.mnuIniciarSecion.setVisible(false);
            instance.mnuCerrarSecion.setVisible(true);
            instance.mnuUsuarioLogueado.setText("User Logueado: "+Security.usuario.getUsername());   
            instance.mnuOpciones.setVisible(true);
        }else if(Security.usuario.getLogin().equals("cliente")){
            instance.mnuUsuarioLogueado.setVisible(true);
            instance.mnuUsuarioLogueado.setText("User Logueado: "+Security.usuario.getUsername());   
            instance.mnuCerrarSecion.setVisible(true);
            instance.mnuIniciarSecion.setVisible(false);
            instance.mnuPedidos.setVisible(true);
            instance.mnuOpciones.setVisible(true);
            instance.mnuConfiguracionCuenta.setVisible(true);
        }
        
    }   
    /**
     * Construtor por defecto privado
     */
    private GUIPagPrincipalRestaurantes() {
        initComponents();
        setSize(800, 700);
        setLocationRelativeTo(null);   
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
     * lista todos los restaurantes en la tabla dependiendo del usuario
     */
    public void listarRegistro(){
        boolean[] editable = {false,false,false,true};
        DefaultTableModel dt = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        restaurantes = service.getRestaurantes();
        
        for (Restaurante restaurante : restaurantes) {
            restaurante.setFoto(getImagen("src/main/java/resources/restaurante" + restaurante.getId() + ".jpg"));
            try {
                restaurante.setMenuSemanal(service.getMenuComponentes(restaurante.getId(), DiaSemana.valueOf(tabla.getValueAt(restaurante.getId(),3).toString())));
            } catch (Exception e) {
                restaurante.setMenuSemanal(service.getMenuComponentes(restaurante.getId(), DiaSemana.LUNES));
            }
            
            restaurante.setPlato(service.getPlato(restaurante.getId()));
        }
        dt.addColumn("<html><b>Restaurantes</b></html>");
        dt.addColumn("<html><b>Informacion</b></html>");
        dt.addColumn("<html><b>Ciudad</b></html>");
        if (Security.usuario != null) {
            if (!Security.usuario.getLogin().equals("administrador")) {
                dt.addColumn("<html><b>Seleccione Dia</b></html>");
            }
        } else {
            dt.addColumn("<html><b>Seleccione Dia</b></html>");
        }

        
        Object fila[] = new Object[dt.getColumnCount()];
        for (Restaurante restaurante : restaurantes) {
            fila[0] = new JLabel(getImageIcon(restaurante.getFoto(), 300, 200));
            String mensaje = "<html><b>NOMBRE: " + restaurante.getNombre() + "</b><br/>";
            if (!(restaurante.getMenuSemanal() == null)) {
                mensaje += "<br/>ENTRADA: ";
                for (Componente componente : restaurante.getMenuSemanal()) 
                    if (componente.getTipo().name().equals("ENTRADA")) 
                        mensaje += componente.getNombre() + ",";
                mensaje += "<br/>PRINCIPIO: ";
                for (Componente componente : restaurante.getMenuSemanal()) 
                    if (componente.getTipo().name().equals("PRINCIPIO")) 
                        mensaje += componente.getNombre() + ",";
                mensaje += "<br/>PROTEINA: ";
                for (Componente componente : restaurante.getMenuSemanal()) 
                    if (componente.getTipo().name().equals("PROTEINA")) 
                        mensaje += componente.getNombre() + ",";
                mensaje += "<br/>BEBIDA: ";
                for (Componente componente : restaurante.getMenuSemanal()) 
                    if (componente.getTipo().name().equals("BEBIDA")) 
                        mensaje += componente.getNombre() + ",";
            }
            
            
            fila[1] = mensaje + "</html>";
            fila[2] = restaurante.getCiudad();
            if (Security.usuario!=null) {
                if (!Security.usuario.getLogin().equals("administrador")) {
                    fila[3] = "LUNES";
                }
            }else{
                fila[3] = "LUNES";
            }
            
            dt.addRow(fila);
            
        }
        tabla.setModel(dt);
        if (Security.usuario != null) {
            if (!Security.usuario.getLogin().equals("administrador")) {
                JComboBox<String> cbxDia = new JComboBox<>();
                cbxDia.addItem("LUNES");
                cbxDia.addItem("MARTES");
                cbxDia.addItem("MIERCOLES");
                cbxDia.addItem("JUEVES");
                cbxDia.addItem("VIERNES");
                cbxDia.addItem("SABADO");
                cbxDia.addItem("DOMINGO");

                tabla.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbxDia));
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setToolTipText("Selecione dia");
                tabla.getColumnModel().getColumn(3).setCellRenderer(renderer);
            }
        } else {
            JComboBox<String> cbxDia = new JComboBox<>();
            cbxDia.addItem("LUNES");
            cbxDia.addItem("MARTES");
            cbxDia.addItem("MIERCOLES");
            cbxDia.addItem("JUEVES");
            cbxDia.addItem("VIERNES");
            cbxDia.addItem("SABADO");
            cbxDia.addItem("DOMINGO");

            tabla.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbxDia));
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setToolTipText("Selecione dia");
            tabla.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        }
       
        tabla.setRowHeight(200);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuOpciones = new javax.swing.JMenu();
        mnuAgregarComponentes = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuPedidos = new javax.swing.JMenuItem();
        mnuConfiguracionCuenta = new javax.swing.JMenuItem();
        mnuInformes = new javax.swing.JMenu();
        mnuIngresosPorFecha = new javax.swing.JMenuItem();
        mnuConsultaAbierta = new javax.swing.JMenuItem();
        mnuCerrarSecion = new javax.swing.JMenu();
        mnuIniciarSecion = new javax.swing.JMenu();
        mnuAyuda = new javax.swing.JMenu();
        mnuAcercaDe = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenu();
        mnuUsuarioLogueado = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restaurantes");
        setExtendedState(6);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(926, 431));

        tabla.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        mnuOpciones.setText("Opciones");

        mnuAgregarComponentes.setText("AgregarComponentes");
        mnuAgregarComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAgregarComponentesActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuAgregarComponentes);
        mnuOpciones.add(jSeparator1);

        mnuPedidos.setText("Pedidos");
        mnuPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPedidosActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuPedidos);

        mnuConfiguracionCuenta.setText("Configuracion De Cuenta");
        mnuConfiguracionCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfiguracionCuentaActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuConfiguracionCuenta);

        jMenuBar1.add(mnuOpciones);

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

        mnuCerrarSecion.setText("Cerrar Seccion");
        mnuCerrarSecion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuCerrarSecionMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuCerrarSecion);

        mnuIniciarSecion.setText("Iniciar Seccion");
        mnuIniciarSecion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuIniciarSecionMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuIniciarSecion);

        mnuAyuda.setText("Ayuda");

        mnuAcercaDe.setText("Acerca de");
        mnuAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAcercaDeActionPerformed(evt);
            }
        });
        mnuAyuda.add(mnuAcercaDe);

        jMenuBar1.add(mnuAyuda);

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
        GUIPagAgregarComponente.getInstance(service).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnuAgregarComponentesActionPerformed
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
     * Abre la interfaz GUIPagMenuRestaurante si el usurio es cliente o null
 Abre la interfaz GUIPagAdicionarPlato si el usuario es administrador
     * @param evt 
     */
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        int row = tabla.getSelectedRow();
        if (Security.usuario != null) {
            if (Security.usuario.getLogin().equals("administrador")) {
                GUIPagAdicionarPlato.getInstance(restaurantes.get(row),service).setVisible(true);
                this.dispose();
            }
        } else {
            
            GUIPagMenuRestaurante.getInstance(restaurantes.get(row),service,DiaSemana.valueOf(tabla.getValueAt(row,3).toString())).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_tablaMouseClicked
    /**
     * Cierra la seccion pone el Security.usuario en null
     * @param evt 
     */
    private void mnuCerrarSecionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuCerrarSecionMouseClicked
        // TODO add your handling code here:
        Security.usuario = null;
        seccion();
        listarRegistro();
    }//GEN-LAST:event_mnuCerrarSecionMouseClicked
    /**
     * Abre la interfaz GUIPagLogin y hace dispose a esta interfaz actual
     * @param evt 
     */
    private void mnuIniciarSecionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuIniciarSecionMouseClicked
        // TODO add your handling code here:
        GUIPagLogin.getInstance(service).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnuIniciarSecionMouseClicked
    /**
     * Cierra la app
     * @param evt 
     */
    private void mnuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mnuSalirMouseClicked
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem mnuAcercaDe;
    private javax.swing.JMenuItem mnuAgregarComponentes;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenu mnuCerrarSecion;
    private javax.swing.JMenuItem mnuConfiguracionCuenta;
    private javax.swing.JMenuItem mnuConsultaAbierta;
    private javax.swing.JMenu mnuInformes;
    private javax.swing.JMenuItem mnuIngresosPorFecha;
    private javax.swing.JMenu mnuIniciarSecion;
    private javax.swing.JMenu mnuOpciones;
    private javax.swing.JMenuItem mnuPedidos;
    private javax.swing.JMenu mnuSalir;
    private javax.swing.JMenu mnuUsuarioLogueado;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object o) {
        listarRegistro();
    }
}
