package vallegrande.edu.pe.sistematransporte.view;

import vallegrande.edu.pe.sistematransporte.controller.VehiculoController;
import vallegrande.edu.pe.sistematransporte.model.Vehiculo;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class Vista extends JFrame {

    JComboBox<String> cbTipo;
    JTextField txtMarca, txtModelo, txtBuscar;
    JTable tabla;
    DefaultTableModel modelo;
    JLabel lblTotal;

    VehiculoController c = new VehiculoController();

    public Vista() {

        setTitle("Sistema de Transporte");
        setSize(660,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230,235,245));
        add(panel);

        Font f = new Font("Segoe UI", Font.PLAIN, 13);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);

        // TARJETA
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(20,20,610,150);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        panel.add(card);

        JLabel titulo = new JLabel("Registro de Vehículos");
        titulo.setBounds(10,5,250,25);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(titulo);

        // 🔽 SIN LABEL DE TIPO (solo combo)
        cbTipo = new JComboBox<>(new String[]{"Bus","Taxi","Motocicleta"});
        cbTipo.setBounds(20,40,150,30);
        card.add(cbTipo);

        // LABELS
        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setBounds(180,20,100,15);
        lblMarca.setFont(labelFont);
        card.add(lblMarca);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(340,20,100,15);
        lblModelo.setFont(labelFont);
        card.add(lblModelo);

        JLabel lblBuscar = new JLabel("Buscar por marca");
        lblBuscar.setBounds(20,75,150,15);
        lblBuscar.setFont(labelFont);
        card.add(lblBuscar);

        // CAMPOS
        txtMarca = new JTextField();
        txtMarca.setBounds(180,40,150,30);
        card.add(txtMarca);

        txtModelo = new JTextField();
        txtModelo.setBounds(340,40,150,30);
        card.add(txtModelo);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(20,95,200,30);
        card.add(txtBuscar);

        // BOTONES
        JButton btnAdd = new JButton("➕ Agregar");
        btnAdd.setBounds(500,40,100,30);

        JButton btnEdit = new JButton("✏️ Editar");
        btnEdit.setBounds(500,75,100,30);

        JButton btnDelete = new JButton("🗑️ Eliminar");
        btnDelete.setBounds(500,110,100,30);

        JButton btnBuscar = new JButton("🔍 Buscar");
        btnBuscar.setBounds(230,95,100,30);

        JButton btnMostrar = new JButton("📋 Mostrar");
        btnMostrar.setBounds(340,95,100,30);

        // COLORES
        btnAdd.setBackground(new Color(39,174,96));
        btnEdit.setBackground(new Color(41,128,185));
        btnDelete.setBackground(new Color(192,57,43));
        btnBuscar.setBackground(new Color(142,68,173));
        btnMostrar.setBackground(new Color(127,140,141));

        btnAdd.setForeground(Color.WHITE);
        btnEdit.setForeground(Color.WHITE);
        btnDelete.setForeground(Color.WHITE);
        btnBuscar.setForeground(Color.WHITE);
        btnMostrar.setForeground(Color.WHITE);

        card.add(btnAdd);
        card.add(btnEdit);
        card.add(btnDelete);
        card.add(btnBuscar);
        card.add(btnMostrar);

        // TOTAL
        lblTotal = new JLabel("Total: 0");
        lblTotal.setBounds(520,175,120,20);
        panel.add(lblTotal);

        // TABLA
        modelo = new DefaultTableModel(new String[]{"Tipo","Marca","Modelo"},0);
        tabla = new JTable(modelo);

        tabla.setRowHeight(28);
        tabla.setFont(f);
        tabla.setSelectionBackground(new Color(52,152,219));
        tabla.setSelectionForeground(Color.WHITE);

        // FILAS INTERCALADAS + CENTRADO
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int col){

                Component c = super.getTableCellRendererComponent(
                        table,value,isSelected,hasFocus,row,col);

                if(!isSelected){
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245,245,245));
                }

                setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        });

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(44,62,80));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane sp = new JScrollPane(tabla);
        sp.setBounds(20,200,610,200);
        panel.add(sp);

        // EVENTOS

        btnAdd.addActionListener(e -> {
            if(txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Complete datos");
                return;
            }

            c.agregar(new Vehiculo(
                    cbTipo.getSelectedItem().toString(),
                    txtMarca.getText(),
                    txtModelo.getText()
            ));

            actualizar(c.getLista());

            txtMarca.setText("");
            txtModelo.setText("");
        });

        btnDelete.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if(fila == -1){
                JOptionPane.showMessageDialog(null,"Seleccione fila");
                return;
            }

            if(JOptionPane.showConfirmDialog(null,"¿Eliminar?")==0){
                c.eliminar(fila);
                actualizar(c.getLista());
            }
        });

        btnEdit.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if(fila == -1){
                JOptionPane.showMessageDialog(null,"Seleccione fila");
                return;
            }

            c.editar(fila, txtMarca.getText(), txtModelo.getText());
            actualizar(c.getLista());
        });

        btnBuscar.addActionListener(e -> {
            actualizar(c.buscar(txtBuscar.getText()));
        });

        btnMostrar.addActionListener(e -> {
            actualizar(c.getLista());
        });

        setVisible(true);
    }

    void actualizar(ArrayList<Vehiculo> lista){
        modelo.setRowCount(0);

        for(Vehiculo v: lista){
            modelo.addRow(new Object[]{
                    v.getTipo(),
                    v.getMarca(),
                    v.getModelo()
            });
        }

        lblTotal.setText("Total: " + lista.size());
    }
}