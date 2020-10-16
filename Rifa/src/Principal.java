
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Principal extends javax.swing.JFrame {
    boolean fill=true,c=false,nb=false,tb=false,ta=false,tc=false;
    conexion ne=new conexion();
    Connection con=ne.Conectar();
    
    int cedula,c2,c3,c4,c5,c6,c7,c8,c9,ccc,cccc;
    
    void Chequeo(){
        ccc=0;
        if(cuadronombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Completar el cuado 'Nombre'");
        }
        else{
            ccc++;
        }
        if(cuadroapellido.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Complete el campo de 'Apellidos'");
        }
        else{
            ccc++;
        }
        if(cuadrocedula2.getText().isEmpty()||cuadrocedula3.getText().isEmpty()||cuadrocedula4.getText().isEmpty()||cuadrocedula5.getText().isEmpty()||cuadrocedula6.getText().isEmpty()||cuadrocedula7.getText().isEmpty()||cuadrocedula8.getText().isEmpty()||cuadrocedula9.getText().isEmpty()||cuadrocedula2.getText().isEmpty()||cuadrocedula2.getText().isEmpty()||cuadrocedula2.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Complete los campos de 'Cedula'");
        }
        else{
            ccc++;
        }
        if(cuadrotelefono.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Complete el campo de 'telefono'");
        }
        else{
            ccc++;
        }
    }
    
    void Cedula(){
        int suma,resultado;
        suma=Integer.parseInt(cuadrocedula2.getText())*8+Integer.parseInt(cuadrocedula3.getText())*1+Integer.parseInt(cuadrocedula4.getText())*2+Integer.parseInt(cuadrocedula5.getText())*3+Integer.parseInt(cuadrocedula6.getText())*4+Integer.parseInt(cuadrocedula7.getText())*7+Integer.parseInt(cuadrocedula8.getText())*6;
        resultado=suma%10;
        
        if(resultado==Integer.parseInt(cuadrocedula9.getText())){
                ccc++;
        }
        else{
            JOptionPane.showMessageDialog(null, "LA CEDULA ES INCORRECTA");
        }
    }
    
    void Cargar(){
        
        if(ccc==5){
        c2=Integer.parseInt(cuadrocedula2.getText())*10000000;
        c3=Integer.parseInt(cuadrocedula3.getText())*1000000;
        c4=Integer.parseInt(cuadrocedula4.getText())*100000;
        c5=Integer.parseInt(cuadrocedula5.getText())*10000;
        c6=Integer.parseInt(cuadrocedula6.getText())*1000;
        c7=Integer.parseInt(cuadrocedula7.getText())*100;
        c8=Integer.parseInt(cuadrocedula8.getText())*10;
        c9=Integer.parseInt(cuadrocedula9.getText())*1;
        cedula=c2+c3+c4+c5+c6+c7+c8+c9;
        String sql="insert into concursantes(nombre,apellido,cedula,telefono) values(?,?,?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,cuadronombre.getText());
            pst.setString(2,cuadroapellido.getText());
            pst.setString(3,Integer.toString(cedula));
            pst.setString(4,cuadrotelefono.getText());
            int vus=pst.executeUpdate();
            if (vus>0){
                JOptionPane.showMessageDialog(null, "USUARIO INGRESADO CORRECTAMENTE");
                cuadronombre.setText("");
                cuadroapellido.setText("");
                cuadrotelefono.setText("");
                cuadrocedula2.setText("");
                cuadrocedula3.setText("");
                cuadrocedula4.setText("");
                cuadrocedula5.setText("");
                cuadrocedula6.setText("");
                cuadrocedula7.setText("");
                cuadrocedula8.setText("");
                cuadrocedula9.setText("");
            }
            
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "ERROR AL INGRESAR EL USUARIO, O \nEL USUARIO YA ESTA REGISTRADO");
        }
    }
        
    }
    public void cargarp(){
        try{
            DefaultTableModel modelo2=new DefaultTableModel();
            tpg1.setModel(modelo2);
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=con.prepareStatement("select * from premios");
            rs=ps.executeQuery();//trae datos de consulta
            ResultSetMetaData rsMd=rs.getMetaData();//pasandole el resultado de la consulta
            int cantidad=rsMd.getColumnCount();//cantidad de columas
            modelo2.addColumn("Nombre");
            modelo2.addColumn("Descripcion");
            while(rs.next()){
                Object[] filas=new Object[cantidad];
                for(int i=0;i<cantidad;i++){
                    filas[i]=rs.getObject(i+1);
                }
                modelo2.addRow(filas);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ex);
        }
    }

    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);//hacer que cuando se abra quede en el medio de la pantalla
        //this.setResizable(false);//para que no se pueda cambiar el tamaño
        this.setSize(1366,768);//para asignar tamaño al abrir el programa
        try{
            DefaultTableModel modelo=new DefaultTableModel();
            tpg.setModel(modelo);
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=con.prepareStatement("select nombre,apellido from concursantes");
            rs=ps.executeQuery();//trae datos de consulta
            ResultSetMetaData rsMd=rs.getMetaData();//pasandole el resultado de la consulta
            int cantidad=rsMd.getColumnCount();//cantidad de columas
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellidos");
            while(rs.next()){
                Object[] filas=new Object[cantidad];
                for(int i=0;i<cantidad;i++){
                    filas[i]=rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ex);
        }
        
        
        try{
            DefaultTableModel modelo2=new DefaultTableModel();
            tpg1.setModel(modelo2);
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=con.prepareStatement("select * from premios");
            rs=ps.executeQuery();//trae datos de consulta
            ResultSetMetaData rsMd=rs.getMetaData();//pasandole el resultado de la consulta
            int cantidad=rsMd.getColumnCount();//cantidad de columas
            modelo2.addColumn("Nombre");
            modelo2.addColumn("Descripción");
            while(rs.next()){
                Object[] filas=new Object[cantidad];
                for(int i=0;i<cantidad;i++){
                    filas[i]=rs.getObject(i+1);
                }
                modelo2.addRow(filas);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ex);
        }
        
    }
    public Image getIconImage(){
        Image retValue= Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/descarga.png"));
        return retValue;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jOptionPane1 = new javax.swing.JOptionPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cuadrocedula3 = new javax.swing.JTextField();
        cuadrocedula4 = new javax.swing.JTextField();
        cuadrocedula5 = new javax.swing.JTextField();
        cuadrocedula6 = new javax.swing.JTextField();
        cuadrocedula7 = new javax.swing.JTextField();
        cuadrocedula8 = new javax.swing.JTextField();
        cuadrocedula9 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cuadronombre = new javax.swing.JTextField();
        cuadroapellido = new javax.swing.JTextField();
        cuadrotelefono = new javax.swing.JTextField();
        cuadrocedula2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpg = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpg1 = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton5 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setBackground(new java.awt.Color(51, 51, 255));
        jButton10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton10.setText("Premios");
        jButton10.setToolTipText("");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 160, -1));

        jButton11.setBackground(new java.awt.Color(51, 51, 255));
        jButton11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton11.setText("¡Participar!");
        jButton11.setToolTipText("");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, 160, 50));

        jButton12.setBackground(new java.awt.Color(51, 51, 255));
        jButton12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton12.setText("Concursantes");
        jButton12.setToolTipText("");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 160, -1));

        jTabbedPane1.addTab("Inicio", jPanel3);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cuadrocedula3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadrocedula3KeyTyped(evt);
            }
        });
        jPanel2.add(cuadrocedula3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, 25, -1));

        cuadrocedula4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadrocedula4KeyTyped(evt);
            }
        });
        jPanel2.add(cuadrocedula4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, 25, -1));

        cuadrocedula5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadrocedula5KeyTyped(evt);
            }
        });
        jPanel2.add(cuadrocedula5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 25, -1));

        cuadrocedula6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadrocedula6KeyTyped(evt);
            }
        });
        jPanel2.add(cuadrocedula6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 200, 25, -1));

        cuadrocedula7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadrocedula7KeyTyped(evt);
            }
        });
        jPanel2.add(cuadrocedula7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, 25, -1));

        cuadrocedula8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadrocedula8KeyTyped(evt);
            }
        });
        jPanel2.add(cuadrocedula8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 200, 25, -1));

        cuadrocedula9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadrocedula9KeyTyped(evt);
            }
        });
        jPanel2.add(cuadrocedula9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 25, -1));

        jLabel4.setText("-");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 200, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Apellido");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, -1, -1));
        jPanel2.add(cuadronombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 110, -1));

        cuadroapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadroapellidoActionPerformed(evt);
            }
        });
        jPanel2.add(cuadroapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 110, -1));

        cuadrotelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadrotelefonoKeyTyped(evt);
            }
        });
        jPanel2.add(cuadrotelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 110, -1));

        cuadrocedula2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cuadrocedula2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadrocedula2KeyTyped(evt);
            }
        });
        jPanel2.add(cuadrocedula2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 25, -1));

        jButton4.setText("<< Volver");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(2162, 0, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Telefono");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Cedula");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, -1, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Nombre");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, -1));

        jButton13.setBackground(new java.awt.Color(51, 51, 255));
        jButton13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton13.setText("Continuar");
        jButton13.setToolTipText("");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 160, -1));

        jButton14.setBackground(new java.awt.Color(51, 51, 255));
        jButton14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton14.setText("<< Regresar");
        jButton14.setToolTipText("");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 160, -1));

        jTabbedPane1.addTab("Inscribirse", jPanel2);

        jButton7.setText("<< Volver");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        tpg.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tpg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "CI", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tpg);

        jButton15.setBackground(new java.awt.Color(51, 51, 255));
        jButton15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton15.setText("<< Regresar");
        jButton15.setToolTipText("");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(1126, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(865, 865, 865)
                .addComponent(jButton7)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(522, 522, 522)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 436, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inscriptos", jPanel1);

        tpg1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Descripcion", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tpg1);

        jButton16.setBackground(new java.awt.Color(51, 51, 255));
        jButton16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton16.setText("<< Regresar");
        jButton16.setToolTipText("");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1157, 1157, 1157)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(926, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(537, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Premios", jPanel4);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel5.setText("Para acceder al panel de admin es necesario la contraseña de administrador");

        jButton5.setText("Continuar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(51, 51, 255));
        jButton17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton17.setText("<< Regresar");
        jButton17.setToolTipText("");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1131, 1131, 1131)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(462, 462, 462)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jButton5)))))
                .addContainerGap(952, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButton17)
                .addGap(137, 137, 137)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addContainerGap(511, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuracion", jPanel5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cuadroapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadroapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadroapellidoActionPerformed

    private void cuadrocedula2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrocedula2KeyTyped
        cuadrocedula2.setText("");
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        cuadrocedula3.requestFocus();
    }//GEN-LAST:event_cuadrocedula2KeyTyped

    private void cuadrocedula3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrocedula3KeyTyped
        cuadrocedula3.setText("");
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        cuadrocedula4.requestFocus();
    }//GEN-LAST:event_cuadrocedula3KeyTyped

    private void cuadrocedula4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrocedula4KeyTyped
        cuadrocedula4.setText("");
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        cuadrocedula5.requestFocus();
    }//GEN-LAST:event_cuadrocedula4KeyTyped

    private void cuadrocedula5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrocedula5KeyTyped
        cuadrocedula5.setText("");
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        cuadrocedula6.requestFocus();
    }//GEN-LAST:event_cuadrocedula5KeyTyped

    private void cuadrocedula6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrocedula6KeyTyped
        cuadrocedula6.setText("");
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        cuadrocedula7.requestFocus();
    }//GEN-LAST:event_cuadrocedula6KeyTyped

    private void cuadrocedula7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrocedula7KeyTyped
        cuadrocedula7.setText("");
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        cuadrocedula8.requestFocus();
    }//GEN-LAST:event_cuadrocedula7KeyTyped

    private void cuadrocedula8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrocedula8KeyTyped
        cuadrocedula8.setText("");
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        cuadrocedula9.requestFocus();
    }//GEN-LAST:event_cuadrocedula8KeyTyped

    private void cuadrocedula9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrocedula9KeyTyped
        cuadrocedula9.setText("");
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_cuadrocedula9KeyTyped

    private void cuadrocedula2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrocedula2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadrocedula2KeyPressed

    private void cuadrotelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadrotelefonoKeyTyped
    if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_cuadrotelefonoKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if("adminadmin".equals(jPasswordField1.getText())){
            apartadoa f = new apartadoa();
            f.setVisible (true);

        }
        else{
            JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
        }
        jPasswordField1.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        jTabbedPane1.setSelectedIndex(3);
        cargarp();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if(!"adminadmin".equals(cuadronombre.getText())){
        Chequeo();
        Cedula();
        Cargar();
        try{
            DefaultTableModel modelo=new DefaultTableModel();
            tpg.setModel(modelo);
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=con.prepareStatement("select * from concursantes");
            rs=ps.executeQuery();//trae datos de consulta
            ResultSetMetaData rsMd=rs.getMetaData();//pasandole el resultado de la consulta
            int cantidad=rsMd.getColumnCount();//cantidad de columas
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellidos");
            while(rs.next()){
                Object[] filas=new Object[cantidad];
                for(int i=0;i<cantidad;i++){
                    filas[i]=rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ex);
        }
        }
        else{
            new Sortear(this, true).setVisible(true);
             
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton17ActionPerformed

    /**
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cuadroapellido;
    private javax.swing.JTextField cuadrocedula2;
    private javax.swing.JTextField cuadrocedula3;
    private javax.swing.JTextField cuadrocedula4;
    private javax.swing.JTextField cuadrocedula5;
    private javax.swing.JTextField cuadrocedula6;
    private javax.swing.JTextField cuadrocedula7;
    private javax.swing.JTextField cuadrocedula8;
    private javax.swing.JTextField cuadrocedula9;
    private javax.swing.JTextField cuadronombre;
    private javax.swing.JTextField cuadrotelefono;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tpg;
    private javax.swing.JTable tpg1;
    // End of variables declaration//GEN-END:variables
}
