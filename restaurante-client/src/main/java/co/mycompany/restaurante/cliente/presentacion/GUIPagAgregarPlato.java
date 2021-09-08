package co.mycompany.restaurante.cliente.presentacion;
import co.mycompany.restaurante.cliente.domain.services.RestauranteService;
import static co.mycompany.restaurante.cliente.infra.Messages.warningMessage;
import co.mycompany.restaurante.cliente.domain.entity.Componente;
import co.mycompany.restaurante.cliente.domain.entity.DiaSemana;
import co.mycompany.restaurante.cliente.domain.entity.Plato;
import co.mycompany.restaurante.cliente.domain.entity.Restaurante;
import co.mycompany.restaurante.cliente.domain.entity.TipoComponente;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Kevith Felipe Bastidas
 */
public class GUIPagAgregarPlato extends javax.swing.JInternalFrame {
    /**
     * atributo: instacia la clase RestauranteService para poder acceder asus servicios
     */
    private final RestauranteService service;
    /**
     * atributo: restaurante con el que trabaja la interfaz
     */
    private final Restaurante restaurante;   
    /**
     * atributo: todos los componentes perteneciente a un restaurante en un dia determinado
     */
    ArrayList<Componente> menuComponentes = new ArrayList<>();
    /**
     * atributo: todos los componentes existentes
     */
    ArrayList<Componente> componentes = new ArrayList<>();
    /**
     * atributo: maneja el numero de registros a mostrar en la tablaComponente
     */
    private int numeroRegistros;
    /**
     * atributo: intervalo del numero de registro a mostrar
     */
    private int contadorRegistros;
    /**
     * atributo: guarda la ruta donde se encuentra la imagen del plato
     */
    private String rutaImagen;
    /**
     * atributo: informa si cambio la ruta de la imagen
     */
    private boolean cambioRutaImagen = false;
    /**
     * atributo: Referencia de la vista MenuRestaurante
     */
    GUIPagMenuRestaurante vistaMenuRestaurantes;
    /**
     * Creates new form GUIPagAgregarPlato
     * @param restaurante
     * @param service
     * @param vistaMenuRestaurantes
     */
    public GUIPagAgregarPlato(Restaurante restaurante,RestauranteService service,GUIPagMenuRestaurante vistaMenuRestaurantes) {
        initComponents();
        setSize(1112, 682);
        this.service = service;
        this.restaurante = restaurante;
        this.vistaMenuRestaurantes = vistaMenuRestaurantes;
        this.contadorRegistros = 0;
        this.rutaImagen = "";
        ponerDatosPreterminados();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel14 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbxSeleccionarDia = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbxNumeroDeRegistros = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbxOrdenamiento = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaComponentes = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        btnVerAnterioresComp = new javax.swing.JButton();
        btnVerSiguienteComp = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        btnAgregarMenucomponente = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaMenuSemanal = new javax.swing.JTable();
        btnEliminarcomponenteMenu = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        btnEliminarImagen = new javax.swing.JButton();
        btnSubirImagen = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        lblMensajePrecioPlato = new javax.swing.JLabel();
        txtPrecioPlato = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextPane();
        jPanel13 = new javax.swing.JPanel();
        btnActualizarPlato = new javax.swing.JButton();
        btnVistaPlato = new javax.swing.JButton();
        btnVolverPagPrincipal = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("EDITAR PLATO EJECUTIVO");
        jPanel3.add(jLabel6);

        jPanel14.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Dia Semana:");
        jPanel12.add(jLabel5);

        cbxSeleccionarDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el dia", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO" }));
        cbxSeleccionarDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSeleccionarDiaActionPerformed(evt);
            }
        });
        jPanel12.add(cbxSeleccionarDia);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Numero De Registros:");
        jPanel12.add(jLabel7);

        cbxNumeroDeRegistros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "5", "10", "20" }));
        cbxNumeroDeRegistros.setSelectedIndex(1);
        cbxNumeroDeRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNumeroDeRegistrosActionPerformed(evt);
            }
        });
        jPanel12.add(cbxNumeroDeRegistros);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Ordenar Por:");
        jPanel12.add(jLabel8);

        cbxOrdenamiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NOMBRE", "TIPO" }));
        cbxOrdenamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrdenamientoActionPerformed(evt);
            }
        });
        jPanel12.add(cbxOrdenamiento);

        jPanel5.add(jPanel12, java.awt.BorderLayout.NORTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.BorderLayout());

        tablaComponentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaComponentes);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("TABLA DE COMPONENTES");
        jPanel7.add(jLabel2);

        jPanel1.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        btnVerAnterioresComp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVerAnterioresComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/anterior.png"))); // NOI18N
        btnVerAnterioresComp.setText("anteriores");
        btnVerAnterioresComp.setEnabled(false);
        btnVerAnterioresComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerAnterioresCompActionPerformed(evt);
            }
        });
        jPanel16.add(btnVerAnterioresComp);

        btnVerSiguienteComp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVerSiguienteComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/siguiente.png"))); // NOI18N
        btnVerSiguienteComp.setText("siguientes");
        btnVerSiguienteComp.setEnabled(false);
        btnVerSiguienteComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerSiguienteCompActionPerformed(evt);
            }
        });
        jPanel16.add(btnVerSiguienteComp);

        jPanel8.add(jPanel16);

        jPanel17.setLayout(new java.awt.GridLayout(1, 0));

        btnAgregarMenucomponente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregarMenucomponente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mas.png"))); // NOI18N
        btnAgregarMenucomponente.setText("Agregar componente al menu");
        btnAgregarMenucomponente.setEnabled(false);
        btnAgregarMenucomponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMenucomponenteActionPerformed(evt);
            }
        });
        jPanel17.add(btnAgregarMenucomponente);

        jPanel8.add(jPanel17);

        jPanel1.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new java.awt.BorderLayout());

        tablaMenuSemanal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaMenuSemanal);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        btnEliminarcomponenteMenu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminarcomponenteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/eliminar.png"))); // NOI18N
        btnEliminarcomponenteMenu.setText("Eliminar componente del menu");
        btnEliminarcomponenteMenu.setEnabled(false);
        btnEliminarcomponenteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarcomponenteMenuActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminarcomponenteMenu, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TABLA DE COMPONENTES DEL MENU");
        jPanel6.add(jLabel1);

        jPanel4.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel4);

        jPanel9.setLayout(new java.awt.BorderLayout());

        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sincaratula.jpg"))); // NOI18N
        jPanel15.add(lblImagen);

        jPanel9.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel18.setLayout(new java.awt.GridLayout(1, 0));

        btnEliminarImagen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/eliminar.png"))); // NOI18N
        btnEliminarImagen.setText("Eliminar Imagen");
        btnEliminarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarImagenActionPerformed(evt);
            }
        });
        jPanel18.add(btnEliminarImagen);

        btnSubirImagen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSubirImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/subirArchivo.png"))); // NOI18N
        btnSubirImagen.setText("Subir Imagen");
        btnSubirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirImagenActionPerformed(evt);
            }
        });
        jPanel18.add(btnSubirImagen);

        jPanel9.add(jPanel18, java.awt.BorderLayout.SOUTH);

        jPanel2.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setLayout(new java.awt.GridLayout(2, 0));

        jPanel19.setLayout(new java.awt.GridLayout());

        lblMensajePrecioPlato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMensajePrecioPlato.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMensajePrecioPlato.setText("PRECIO DEL PLATO:");
        jPanel19.add(lblMensajePrecioPlato);
        jPanel19.add(txtPrecioPlato);

        jPanel11.add(jPanel19);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DESCRIPCION DEL PLATO");
        jPanel11.add(jLabel4);

        jPanel10.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jScrollPane3.setViewportView(txtDescripcion);

        jPanel10.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel10);

        jPanel5.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel13.setLayout(new java.awt.GridLayout(1, 0));

        btnActualizarPlato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnActualizarPlato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/confirmar.png"))); // NOI18N
        btnActualizarPlato.setText("Actualizar Menu Plato");
        btnActualizarPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPlatoActionPerformed(evt);
            }
        });
        jPanel13.add(btnActualizarPlato);

        btnVistaPlato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVistaPlato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/recibo-entrada.png"))); // NOI18N
        btnVistaPlato.setText("Vista Del Plato");
        btnVistaPlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVistaPlatoActionPerformed(evt);
            }
        });
        jPanel13.add(btnVistaPlato);

        btnVolverPagPrincipal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVolverPagPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/recibo-salida.png"))); // NOI18N
        btnVolverPagPrincipal.setText("Volver Menu Principal");
        btnVolverPagPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverPagPrincipalActionPerformed(evt);
            }
        });
        jPanel13.add(btnVolverPagPrincipal);

        jPanel14.add(jPanel13, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel14, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /***
     * pone los JButton en un estado inicial
     */
    private void ponerDatosPreterminados(){
        btnEliminarImagen.setEnabled(false);
        btnSubirImagen.setEnabled(false);
        btnVistaPlato.setEnabled(false);
        btnActualizarPlato.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtPrecioPlato.setEnabled(false);
        txtPrecioPlato.setText("");
    }
    /**
     * Actualiza la informacion del plato dependiendo del dia seleccionado en el comboBox
     */
    private void actualizarRegistrosDelDia(){
        if (!cbxSeleccionarDia.getSelectedItem().equals("Seleccione el dia")) {
            this.contadorRegistros = 0;
            this.numeroRegistros = Integer.parseInt(cbxNumeroDeRegistros.getSelectedItem().toString());
            this.menuComponentes = this.service.getMenuComponentes(restaurante.getId(), DiaSemana.valueOf(cbxSeleccionarDia.getSelectedItem().toString()));
            this.componentes = separarElementos(menuComponentes, service.getComponentes());
            
            Plato p = service.getPlato(restaurante.getId(), DiaSemana.valueOf(cbxSeleccionarDia.getSelectedItem().toString()));
            txtDescripcion.setText(p.getDescripcion());
            lblImagen.setText("");
            txtPrecioPlato.setText(p.getPrecio()+"");
            
            
            lblImagen.setIcon(getImageIcon(getImagen(p.getUrl_imagen()),300 , 250));
            this.restaurante.setPlato(p);
            
            btnEliminarImagen.setEnabled(true);
            btnSubirImagen.setEnabled(true);
            btnVistaPlato.setEnabled(true);
            btnActualizarPlato.setEnabled(true);
            txtDescripcion.setEnabled(true);
            txtPrecioPlato.setEnabled(true);
            
            listarRegistros();
        }else{
            limpiarVistaAgregarPlato();
        }
    }
    /**
     * comboBox: Maneja las tablas en un dia determinado: LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
     * @param evt 
     */
    private void cbxSeleccionarDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeleccionarDiaActionPerformed
        // TODO add your handling code here:      
        actualizarRegistrosDelDia();
    }//GEN-LAST:event_cbxSeleccionarDiaActionPerformed
    /**
     * muestra toda la informacion de las tablas y del plato del restaurante
     */
    private void listarRegistros(){
        DefaultTableModel dt = inicializaTabla(new String[]{"ID","NOMBRE","TIPO"});
        DefaultTableModel dt2 = inicializaTabla(new String[]{"ID","NOMBRE","TIPO"});
        agregarRegistros_TableComponentes(componentes, dt);
        agregarRegistros_TableMenuComponentes(menuComponentes, dt2);
        agregarDafaultTableModel_Table(tablaComponentes, dt);
        agregarDafaultTableModel_Table(tablaMenuSemanal, dt2);
        validarButtonComponente(menuComponentes,btnEliminarcomponenteMenu);
        validarButtonComponente(componentes,btnAgregarMenucomponente);
    }
    /**
     * Configuracion de la tabla para ingresar los registros.
     * @return 
     */
    private DefaultTableModel configuracionTablaMenuRestaurante(){
        boolean[] editable = {false,false,false};
        DefaultTableModel tablaMenuRestuarante = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        return tablaMenuRestuarante;
    }  
    /**
     * Inicializa un DefaultTableModel con unas colmnas predefinidas en el parametro
     * @param columnas
     * @return 
     */
    private DefaultTableModel inicializaTabla(String[] columnas) {
        DefaultTableModel dt = configuracionTablaMenuRestaurante();
        for (String columna : columnas) {
            dt.addColumn(columna);
        }
        return dt;
    }
    /**
     * Agregar un DefaultTableModel a una Jtable
     * @param tabla
     * @param dt 
     */
    private void agregarDafaultTableModel_Table(JTable tabla,DefaultTableModel dt){
        tabla.setModel(dt);
    }
    /**
     * Agrega los primeros componentes en la tabla componentes
     * @param componentes
     * @param dt 
     */
    public void agregarRegistros_TableComponentes(ArrayList<Componente> componentes, DefaultTableModel dt) {
        int contadorNumeroRegistros = 0;
        int numeroDeComponente = componentes.size();
        for(int i=contadorRegistros;i<numeroDeComponente;i++){
            if (contadorNumeroRegistros++ < this.numeroRegistros) {
                Object fila[] = new Object[dt.getColumnCount()];
                fila[0] = componentes.get(i).getId();
                fila[1] = componentes.get(i).getNombre();
                fila[2] = componentes.get(i).getTipo().name();
                dt.addRow(fila);
            }else{
                this.contadorRegistros += this.numeroRegistros;
                break;
            }
        }
        validarButtonAnterior((contadorRegistros-(this.numeroRegistros*2)));
        validarButtonSiguiente(numeroDeComponente, contadorRegistros);
    }
    /**
     * Agrega los primeros componentes de menuComponentes en la tabla MenuComponentes
     * @param componentes
     * @param dt 
     */
    public void agregarRegistros_TableMenuComponentes(ArrayList<Componente> componentes, DefaultTableModel dt) {
        int numeroDeComponente = componentes.size();
        for (int i = 0; i < numeroDeComponente; i++) {
            Object fila[] = new Object[dt.getColumnCount()];
            fila[0] = componentes.get(i).getId();
            fila[1] = componentes.get(i).getNombre();
            fila[2] = componentes.get(i).getTipo().name();
            dt.addRow(fila);
        }
    }
    /**
     * valida el button siguiente
     * @param numeroDecomponentes
     * @param contadorRegistros 
     */
    private void validarButtonSiguiente(int numeroDecomponentes,int contadorRegistros){
        if (contadorRegistros>=numeroDecomponentes || numeroDecomponentes<=this.numeroRegistros) {
            btnVerSiguienteComp.setEnabled(false);
        }else{
            btnVerSiguienteComp.setEnabled(true);
        }
    }
    /**
     * valida el button anterior
     * @param contadorRegistros 
     */
    private void validarButtonAnterior(int contadorRegistros){
        if (contadorRegistros<0) {
            btnVerAnterioresComp.setEnabled(false);
        }else{
            btnVerAnterioresComp.setEnabled(true);
        }
    }
    /**
     * valida un botton respecto al tamaño de una lista de componentes
     * @param componentes
     * @param button 
     */
    private void validarButtonComponente(ArrayList<Componente> componentes,JButton button){
        if (componentes.size()<=0) {
            button.setEnabled(false);
        }else{
            button.setEnabled(true);
        }
    }
    /**
     * comboBox: Maneja el numero de registros a mostrar en la tabla de componentes: 5, 10 y 20
     * @param evt 
     */
    private void cbxNumeroDeRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNumeroDeRegistrosActionPerformed
        // TODO add your handling code here:
        this.contadorRegistros = 0;
        this.numeroRegistros = Integer.parseInt(cbxNumeroDeRegistros.getSelectedItem().toString());
        listarRegistros();
    }//GEN-LAST:event_cbxNumeroDeRegistrosActionPerformed
    /**
     * Muestra los anteriores registros de la tabla Componentes
     * @param componentes 
     */
    private void mostrarMenosRegistros(ArrayList<Componente> componentes){
        DefaultTableModel dt = inicializaTabla(new String[]{"ID","NOMBRE","TIPO"});
        agregarDafaultTableModel_Table(tablaComponentes, dt);
        
        int contadorNumeroRegistros = 0;
        int numeroDeComponente = componentes.size();
        for(int i=(contadorRegistros-(this.numeroRegistros*2));i>=0;i++){
            if (contadorNumeroRegistros++ < this.numeroRegistros && i>=0) {
                Object fila[] = new Object[dt.getColumnCount()];
                fila[0] = componentes.get(i).getId();
                fila[1] = componentes.get(i).getNombre();
                fila[2] = componentes.get(i).getTipo().name();
                dt.addRow(fila);
            }else{
                this.contadorRegistros -= this.numeroRegistros;
                break;
            }
        }
        validarButtonAnterior((contadorRegistros-(this.numeroRegistros*2)));
        validarButtonSiguiente(numeroDeComponente, contadorRegistros);
    }
    /**
     * Muestra lso anteriores registros en la tabla componentes dependiendo 
     * del numero de registros seleccionado en el comboBox: 5, 10 y 20
     * @param evt 
     */
    private void btnVerAnterioresCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerAnterioresCompActionPerformed
        // TODO add your handling code here:
        mostrarMenosRegistros(componentes);
    }//GEN-LAST:event_btnVerAnterioresCompActionPerformed
    /**
     * Muestro los siguientes registros de la tabla Componentes
     * @param componentes 
     */
    private void mostrarMasRegistros(ArrayList<Componente> componentes){
        
        //DefaultTableModel dt = (DefaultTableModel)tablaRegistros.getModel();
        DefaultTableModel dt = inicializaTabla(new String[]{"ID","NOMBRE","TIPO"});
        agregarDafaultTableModel_Table(tablaComponentes, dt);
        
        int contadorNumeroRegistros = 0;
        int numeroDeComponente = componentes.size();
        for(int i=contadorRegistros;i<numeroDeComponente+1;i++){
            if (contadorNumeroRegistros++ < this.numeroRegistros && i<numeroDeComponente) {
                Object fila[] = new Object[dt.getColumnCount()];
                fila[0] = componentes.get(i).getId();
                fila[1] = componentes.get(i).getNombre();
                fila[2] = componentes.get(i).getTipo().name();
                dt.addRow(fila);
            }else{
                this.contadorRegistros += this.numeroRegistros;
                break;
            }
        }
        validarButtonSiguiente(numeroDeComponente, contadorRegistros);
        validarButtonAnterior((contadorRegistros-(this.numeroRegistros*2)));
    }
    /**
     * Muestra los siguientes registros en la tabla componentes dependiendo 
     * del numero de registros seleccionado en el comboBox: 5, 10 y 20
     * @param evt 
     */
    private void btnVerSiguienteCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerSiguienteCompActionPerformed
        // TODO add your handling code here:
        mostrarMasRegistros(componentes);
    }//GEN-LAST:event_btnVerSiguienteCompActionPerformed
    /**
     * Agrega uno o muchos componentes a la tabla menuComponentes
     * @param row 
     */
    @SuppressWarnings("empty-statement")
    private void agregarMenuComponente(int row){
        Componente componente = new Componente();
        componente.setId(Integer.parseInt(tablaComponentes.getValueAt(row,0).toString()));
        componente.setNombre(tablaComponentes.getValueAt(row,1).toString());
        componente.setTipo(TipoComponente.valueOf(tablaComponentes.getValueAt(row,2).toString()));
        
        this.contadorRegistros = 0;
        menuComponentes.add(componente);
        menuComponentes.sort(Componente.ID);
        int i;
        for (i = 0; componentes.get(i).getId()!=componente.getId(); i++);
        componentes.remove(i);
    }
    /**
     * Agrega un componente a un restaurante determinado en un dia determiando
     * @param evt 
     */
    private void btnAgregarMenucomponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMenucomponenteActionPerformed
        // TODO add your handling code here:
        int[] rows = tablaComponentes.getSelectedRows();
        for (int r : rows)
            agregarMenuComponente(r);
        if (rows.length>0) 
            listarRegistros();
    }//GEN-LAST:event_btnAgregarMenucomponenteActionPerformed
    /**
     * Ordena una lista de componentes dependiendo de su tipo de ordenamiento
     * @param componentes 
     */
    private void ordenarComponentes(ArrayList<Componente> componentes){
        switch(cbxOrdenamiento.getSelectedItem().toString()){
            case "ID":
                componentes.sort(Componente.ID);
                break;
            case "NOMBRE":
                componentes.sort(Componente.NAME);
                break;
            case "TIPO":
                componentes.sort(Componente.TYPE);
                break;
            default:
                break;
        }
    }
    /**
     * Elimina uno o muchos componentes de la tabla menuComponentes y
     * lo agrega a la tabla Componentes
     * @param row 
     */
    @SuppressWarnings("empty-statement")
    private void eliminarMenuComponente(int row){
        Componente componente = new Componente();
        componente.setId(Integer.parseInt(tablaMenuSemanal.getValueAt(row,0).toString()));
        componente.setNombre(tablaMenuSemanal.getValueAt(row,1).toString());
        componente.setTipo(TipoComponente.valueOf(tablaMenuSemanal.getValueAt(row,2).toString()));
        
        this.contadorRegistros = 0;
        componentes.add(componente);
        ordenarComponentes(componentes);
        
        int i;
        for (i = 0; menuComponentes.get(i).getId()!=componente.getId(); i++);
        menuComponentes.remove(i);
    }
    /**
     * Elimina un restcomponente de un dia y restaurante terminado. 
     * @param evt 
     */
    private void btnEliminarcomponenteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarcomponenteMenuActionPerformed
        // TODO add your handling code here:
        int[] rows = tablaMenuSemanal.getSelectedRows();
        for (int row : rows) 
            eliminarMenuComponente(row);
        if (rows.length>0) 
            listarRegistros();
    }//GEN-LAST:event_btnEliminarcomponenteMenuActionPerformed
    /**
     * Elimina la imagen del plato y pone una imagen sin caratula
     * @param evt 
     */
    private void btnEliminarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarImagenActionPerformed
        // TODO add your handling code here:
        this.rutaImagen = "src/main/java/resources/sincaratula.jpg";
        lblImagen.setIcon(getImageIcon(getImagen(rutaImagen), 300, 300));
        cambioRutaImagen = true;
    }//GEN-LAST:event_btnEliminarImagenActionPerformed
    /**
     * Abre el administrador de archivos de windows en descargas para seleccionar la foto
     * del plato ejecutivo en formato png, jpg y gif
     * @param evt 
     */
    private void ponerImagenEnPlato(String path){
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        jf.setFileFilter(fil);
        jf.setCurrentDirectory(new File(path));
        int el = jf.showOpenDialog(this);
        if(el == JFileChooser.APPROVE_OPTION){
            rutaImagen = jf.getSelectedFile().getAbsolutePath();
            lblImagen.setText("");
            lblImagen.setIcon(getImageIcon(getImagen(rutaImagen), 300, 300));
            
            cambioRutaImagen = true;
            
        }
    }
    /**
     * Pone una imagen en el plato
     * @param evt 
     */
    private void btnSubirImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirImagenActionPerformed
        // TODO add your handling code here:
        ponerImagenEnPlato("C:/Users/kevit/Downloads");
        
    }//GEN-LAST:event_btnSubirImagenActionPerformed
    /**
     * limpia las tablas, pone imagen preterminada y limpia la caja de Descripcion
     */
    private void limpiarVistaAgregarPlato() {
        DefaultTableModel dt = inicializaTabla(new String[]{"ID","NOMBRE","TIPO"});
        DefaultTableModel dt2 = inicializaTabla(new String[]{"ID","NOMBRE","TIPO"});
        btnAgregarMenucomponente.setEnabled(false);
        btnEliminarcomponenteMenu.setEnabled(false);
        
        btnVerSiguienteComp.setEnabled(false);
        btnVerAnterioresComp.setEnabled(false);
        
        ponerDatosPreterminados();
        
        tablaComponentes.setModel(dt);
        tablaMenuSemanal.setModel(dt2);
        lblImagen.setText("");
        lblImagen.setIcon(getImageIcon(getImagen(""), 300, 300));
        txtDescripcion.setText("");
    }
    /**
     * guarda la ruta de la imagen del plato en el plato
     */
    private void guardarRutaImagen(Plato plato){
        if (!"".equals(rutaImagen) && !"src/main/java/resources/sincaratula.jpg".equals(rutaImagen)) {
            String ruta = "src/main/java/resources/plato"+restaurante.getId()+cbxSeleccionarDia.getSelectedItem().toString()+".jpg";
            plato.setUrl_imagen(ruta);
            guardarImagen(ruta,getImagen(rutaImagen));
            rutaImagen = "";
        }else{
            String rutaPredeterminada = "src/main/java/resources/sincaratula.jpg";
            plato.setUrl_imagen(rutaPredeterminada);
        }
    }
    /**
     * actualiza el registro del menuComponente de una dia determinado
     */
    private void actualizarMenuComponente() {
        ArrayList<Componente> menuComponenteOriginal = this.service.getMenuComponentes(restaurante.getId(), DiaSemana.valueOf(cbxSeleccionarDia.getSelectedItem().toString()));
        ArrayList<Componente> componentesAgregar = separarElementos(menuComponenteOriginal, menuComponentes);

        for (Componente componente : componentesAgregar) {
            service.addComponenteSemanal(restaurante.getId(), componente, DiaSemana.valueOf(cbxSeleccionarDia.getSelectedItem().toString()));
        }
        ArrayList<Componente> componentesEliminar = separarElementos(menuComponentes, menuComponenteOriginal);
        for (Componente componente : componentesEliminar) {
            service.deleteComponenteSemanal(restaurante.getId(), componente, DiaSemana.valueOf(cbxSeleccionarDia.getSelectedItem().toString()));
        }

    }
    private boolean esValidoPrecio(){
        try {
            int precio = Integer.parseInt(txtPrecioPlato.getText());
            if (precio>0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    /**
     * Actualiza el plato con sus respectivos componentes de un determinado restaurante
     * @param evt 
     */
    private void actualizarPlato(){
        
        Plato plato = restaurante.getPlato();
        plato.setCantidad(0);
        plato.setRest_id(restaurante.getId());
        plato.setDia(DiaSemana.valueOf(cbxSeleccionarDia.getSelectedItem().toString()));
        plato.setUrl_imagen(restaurante.getPlato().getUrl_imagen());
        
        if (esValidoPrecio()) {
            plato.setPrecio(Integer.parseInt(txtPrecioPlato.getText()));
            
        }
        
        if (plato.getUrl_imagen()==null) {
            plato.setUrl_imagen("src/main/java/resources/sincaratula.jpg");
        }
        
        if (cambioRutaImagen) {
            deleteImagen(plato.getUrl_imagen());
            guardarRutaImagen(plato);
            cambioRutaImagen = false;
        }
        

        plato.setDescripcion(txtDescripcion.getText());
        service.updatePlato(restaurante.getId(),plato.getDia(), plato);

        actualizarMenuComponente();
        
        warningMessage("Se actualizo el plato menu exitosamente", "Atención");
        //cbxSeleccionarDia.setSelectedIndex(0);
        actualizarRegistrosDelDia();
    }
    /**
     * Actualiza el plato
     * @param evt 
     */
    private void btnActualizarPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPlatoActionPerformed
        // TODO add your handling code here:
        actualizarPlato();
    }//GEN-LAST:event_btnActualizarPlatoActionPerformed

    private void btnVistaPlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVistaPlatoActionPerformed
        // TODO add your handling code here:
        if (!cbxSeleccionarDia.getSelectedItem().equals("Seleccione el dia")) {
            Plato p = service.getPlato(restaurante.getId(), DiaSemana.valueOf(cbxSeleccionarDia.getSelectedItem().toString()));
            restaurante.setPlato(p);
            restaurante.setMenuSemanal(service.getMenuComponentes(restaurante.getId(), DiaSemana.valueOf(cbxSeleccionarDia.getSelectedItem().toString())));
            //GUIPagMenuPlato2.getInstance(restaurante,service,dia).setVisible(true);
            GUIPagMenuPlato vistaMenuPlato = new GUIPagMenuPlato(restaurante,null, this);
            vistaMenuRestaurantes.getVistaPrincipal().agregarComponente(vistaMenuPlato);
            vistaMenuPlato.show();
        }

    }//GEN-LAST:event_btnVistaPlatoActionPerformed

    private void btnVolverPagPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverPagPrincipalActionPerformed
        // TODO add your handling code here:
        //GUIPagPrincipalRestaurantes.getInstance(service).setVisible(true);
        vistaMenuRestaurantes.getVistaPrincipal().agregarComponente(vistaMenuRestaurantes);
        vistaMenuRestaurantes.show();
    }//GEN-LAST:event_btnVolverPagPrincipalActionPerformed
    /**
     * Ordena la tabla Componentes dependiendo del item seleccionado: ID, NOMBRE O TIPO
     * @param evt 
     */
    private void cbxOrdenamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrdenamientoActionPerformed
        // TODO add your handling code here:
        this.contadorRegistros = 0;
        ordenarComponentes(componentes);
        listarRegistros();
    }//GEN-LAST:event_cbxOrdenamientoActionPerformed

    /**
     * Guarda una imagen en un path determinado
     * @param nombre
     * @param imagen
     * @return 
     */
    public boolean guardarImagen(String nombre,byte[] imagen){
        File archivo = new File(nombre);
        try {
            FileOutputStream salida = new FileOutputStream(archivo);
            salida.write(imagen);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    /**
     * get un vector byte[] de una ruta especificada
     * @param pathname ruta de la imagen
     * @return -
     */
    private byte[] getImagen(String pathname) {   
        try {
            File archivo = new File(pathname);
            byte[] image = new byte[(int) archivo.length()];
            FileInputStream entrada = new FileInputStream(archivo);
            entrada.read(image);
            return image;
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * get un vector byte[] de una ruta especificada
     * @param pathname ruta de la imagen
     * @return -
     */
    private void deleteImagen(String pathname) {   
        if (!pathname.equals("src/main/java/resources/sincaratula.jpg")) {
            try {
                File archivo = new File(pathname);
                archivo.delete();
            } catch (Exception e) {
            }
        }

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
     * Separa todos los elementos de la lista elementos1 que no estan en la lista de elementos2
     * @param <T>
     * @param elementos1
     * @param elementos2
     * @return la lista de elementos no existentes en la lista elementos1
     */
    private<T extends Comparable> ArrayList<T> separarElementos(ArrayList<T> elementos1,ArrayList<T> elementos2){
        ArrayList<T> elementosSeparados = new ArrayList<>();
        for (T elemento : elementos2) {
            if (!existeElemento(elementos1, elemento)) {
                elementosSeparados.add(elemento);
            }
        }
        return elementosSeparados;
    }
    /**
     * busca en la lista elementos si existe el elemento
     * @param <T>
     * @param elementos
     * @param elemento
     * @return verdadero si lo encuentra de lo contrario falso
     */
    private<T extends Comparable> boolean existeElemento(ArrayList<T> elementos, T elemento){
        for (T elemento2 : elementos) 
            if (elemento2.compareTo(elemento)==0) 
                return true;
        return false;
    }
    /**
     * devuelve referencia de la vista principal
     * @return 
     */
    public GUIPrincipal getVistaPrincipal(){
        return this.vistaMenuRestaurantes.getVistaPrincipal();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarPlato;
    private javax.swing.JButton btnAgregarMenucomponente;
    private javax.swing.JButton btnEliminarImagen;
    private javax.swing.JButton btnEliminarcomponenteMenu;
    private javax.swing.JButton btnSubirImagen;
    private javax.swing.JButton btnVerAnterioresComp;
    private javax.swing.JButton btnVerSiguienteComp;
    private javax.swing.JButton btnVistaPlato;
    private javax.swing.JButton btnVolverPagPrincipal;
    private javax.swing.JComboBox<String> cbxNumeroDeRegistros;
    private javax.swing.JComboBox<String> cbxOrdenamiento;
    private javax.swing.JComboBox<String> cbxSeleccionarDia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblMensajePrecioPlato;
    private javax.swing.JTable tablaComponentes;
    private javax.swing.JTable tablaMenuSemanal;
    private javax.swing.JTextPane txtDescripcion;
    private javax.swing.JTextField txtPrecioPlato;
    // End of variables declaration//GEN-END:variables
}
