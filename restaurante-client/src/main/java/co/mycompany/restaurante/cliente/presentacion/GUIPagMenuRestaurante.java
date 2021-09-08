package co.mycompany.restaurante.cliente.presentacion;
import co.mycompany.restaurante.cliente.domain.entity.DiaSemana;
import co.mycompany.restaurante.cliente.domain.entity.Restaurante;
import co.mycompany.restaurante.cliente.domain.services.RestauranteService;
import co.mycompany.restaurante.cliente.infra.Security;
import co.mycompany.restaurante.cliente.infra.imgTabla;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
public class GUIPagMenuRestaurante extends javax.swing.JInternalFrame {
    /**
     * Instacia de la vistaPrincipal
     */
    private final GUIPrincipal vistaPrincipal;
    /**
     * atributo: obtiene toda la lista de los restaurantes
     */
    private List<Restaurante> restaurantes = new ArrayList<>();
    /**
     * instacia la clase RestauranteService para poder acceder asus servicios
     */
    private final RestauranteService service;
    /**
     * Constructor con el parametro service
     * @param service
     * @param vistaPrincipal
     */
    public GUIPagMenuRestaurante(RestauranteService service,GUIPrincipal vistaPrincipal) {
        initComponents();
        setSize(900, 682);
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        this.service = service;
        listarRegistro();
        this.vistaPrincipal = vistaPrincipal;
    }   
    /**
     * Configuracion de la tabla para ingresar los registros.
     * @return 
     */
    private DefaultTableModel configuracionTablaMenuRestaurante(){
        boolean[] editable = {false,false,false,true};
        DefaultTableModel tablaMenuRestuarante = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        return tablaMenuRestuarante;
    }  
    /**
     * lista todos los restaurantes en la tabla dependiendo del usuario
     */
    public void listarRegistro(){
        DefaultTableModel tablaRegistroRestaurantes = configuracionTablaMenuRestaurante();
         
        restaurantes = service.getRestaurantes();
        
        for (Restaurante restaurante : restaurantes) {    
            restaurante.setFoto(getImagen("src/main/java/resources/restaurante" + restaurante.getId() + ".jpg"));
        }
        tablaRegistroRestaurantes.addColumn("<html><b><span style='font-size:16px'>Restaurantes</span></b></html>");
        tablaRegistroRestaurantes.addColumn("<html><b><span style='font-size:16px'>Informacion</span></b></html>");
        tablaRegistroRestaurantes.addColumn("<html><b><span style='font-size:16px'>Ciudad</span></b></html>");
        
        if (!Security.usuario.getLogin().name().equals("ADMINISTRADOR")) {
            tablaRegistroRestaurantes.addColumn("<html><b><span style='font-size:16px'>Seleccione Dia</span></b></html>");
            
        }
        Object fila[] = new Object[tablaRegistroRestaurantes.getColumnCount()];
        for (Restaurante restaurante : restaurantes) {
            fila[0] = new JLabel(getImageIcon(restaurante.getFoto(), 170, 70));
            String mensaje = "<html><b>NOMBRE: " + restaurante.getNombre() + "</b><br/>";
            mensaje += "<br/>DIRECCION: "+restaurante.getDireccion();
            mensaje += "<br/>TELEFONO: "+restaurante.getTelefono();
            fila[1] = mensaje + "</html>";
            fila[2] = restaurante.getCiudad();
            
            if (!Security.usuario.getLogin().name().equals("ADMINISTRADOR")) {
                fila[3] = "LUNES";
            }
            tablaRegistroRestaurantes.addRow(fila);
        }
        tabla.setModel(tablaRegistroRestaurantes);
        
        if (!Security.usuario.getLogin().name().equals("ADMINISTRADOR")) {
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
            
            tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
        }
        
        tabla.setRowHeight(70);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(220);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(350);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
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
            } catch (IOException e2) {
            }
            return null;
        }
    } 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MenuRestaurante");
        setLayer(6);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

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

        getContentPane().add(jScrollPane1);

        getAccessibleContext().setAccessibleParent(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /** 
     * Abre la interfaz GUIPagMenuRestaurante si el usurio es cliente o null 
     * Abre la interfaz GUIPagAdicionarPlato si el usuario es administrador
     * @param evt 
     */
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        int row = tabla.getSelectedRow();
        
        if (Security.usuario.getLogin().name().equals("ADMINISTRADOR")) {
            Restaurante restaurante = restaurantes.get(row);
            GUIPagAgregarPlato vistaAgregarPlato = new GUIPagAgregarPlato(restaurante, service,this);
            vistaPrincipal.agregarComponente(vistaAgregarPlato);
            vistaAgregarPlato.show();
        }else{
            Restaurante restaurante = restaurantes.get(row);
            restaurante.setMenuSemanal(service.getMenuComponentes(restaurante.getId(), DiaSemana.valueOf(tabla.getValueAt(row,3).toString())));
            DiaSemana dia = DiaSemana.valueOf(tabla.getValueAt(row,3).toString());
            restaurante.setPlato(service.getPlato(restaurante.getId(),dia));
            GUIPagMenuPlato vistaMenuPlato = new GUIPagMenuPlato(restaurante,this,null);
            vistaPrincipal.agregarComponente(vistaMenuPlato);
            vistaMenuPlato.show();
        }
    }//GEN-LAST:event_tablaMouseClicked
    /**
     * Devuelve una referencia de la vistaPrincipal
     * @return 
     */
    public GUIPrincipal getVistaPrincipal(){
        return this.vistaPrincipal;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
