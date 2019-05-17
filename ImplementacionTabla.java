package ImplementacionTabla;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;

public class ImplementacionTabla extends JFrame implements ActionListener{
	private JFrame v;
	private JTable jt;
	private DefaultTableModel model;
	private JButton botonAgregar;
	private JButton botonEditar;
	private JButton botonEliminar;
	private JTextField caja;
	private JTextField caja2;
	private JTextField caja3;
	private JLabel mensaje;
	private int contador=1;
	String Data = null,data2;
	
	public ImplementacionTabla() {
		v = new JFrame("Table");
		crearComponentes();
		String data[][] = {
				
		};
		
		String label[] = {"ID","NAME","PRICE"};
		
		model = new DefaultTableModel(data,label);
		jt = new JTable(model); 
		
		jt.setCellSelectionEnabled(true);
		
		ListSelectionModel select = jt.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			
			int[] row = jt.getSelectedRows();
			int[] columns = jt.getSelectedColumns();
			
			for(int i=0; i< row.length; i++) 
					Data = (String) jt.getValueAt(row[i], 0);
			
			caja.setText((String)jt.getValueAt(Integer.valueOf(Data)-1, 1));
			caja2.setText((String)jt.getValueAt(Integer.valueOf(Data)-1, 2));
			caja3.setText(Data);
		}
		
		});
	
		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(420, 15, 310, 140);
		v.add(sp);
		v.setSize(800,300);
		v .setLocationRelativeTo(null);
		v.setLayout(null);
		v.setVisible(true);	
		
	}
	
	public void addRow(String str1, String str2, String str3) {
		model.addRow(new Object[]{str1,str2,str3});
		model.insertRow(4, new Object[] {"1","Tacos","15"});
	}
	
	private void crearComponentes() {
		mensaje = new JLabel();
		mensaje.setText("NAME:");
		mensaje.setBounds(50, 15, 200, 30);
		v.add(mensaje);
		
		mensaje = new JLabel();
		mensaje.setText("PRICE:");
		mensaje.setBounds(50, 50, 200, 30);
		v.add(mensaje);
		
		mensaje = new JLabel();
		mensaje.setText("ID:");
		mensaje.setBounds(50, 90, 40, 25);
		v.add(mensaje);
		
		caja = new JTextField();
		caja.setBounds(110, 15, 150, 25);
		caja.addActionListener(this);
		v.add(caja);
		
		caja2 = new JTextField();
		caja2.setBounds(110, 50, 150, 25);
		caja.addActionListener(this);
		v.add(caja2);
		
		caja3 = new JTextField();
		caja3.setBounds(110, 90, 40, 25);
		v.add(caja3);
		
		botonAgregar = new JButton();
		botonAgregar.setText("AGREGAR");
		botonAgregar.setBounds(280, 15, 100,25 );
		
		botonAgregar.setEnabled(true);
		botonAgregar.addActionListener(this);
		v.add(botonAgregar);
		
		botonEditar = new JButton();
		botonEditar.setText("EDITAR");
		botonEditar.setBounds(280, 50, 100,25 );
		
		botonEditar.addActionListener(this);
		botonEditar.setVisible(false);
		v.add(botonEditar);
		
		botonEliminar = new JButton();
		botonEliminar.setText("ELIMINAR");
		botonEliminar.setBounds(280, 90, 100,25 );
		
		botonEliminar.setEnabled(true);
		botonEliminar.addActionListener(this);
		botonEliminar.setVisible(false);
		v.add(botonEliminar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "AGREGAR":
			String cont2 = String.valueOf(contador);
			model.addRow(new Object[] {cont2,caja.getText(),caja2.getText()});  
			contador++;
		break;
		case "ELIMINAR":
			try {
			if(model.getRowCount()>0 ) { 
				if(caja3.getText()==null) { 
				int eliminar = Integer.parseInt(Data);
				model.removeRow(eliminar-1);
				} 
				else {
					String aux = caja3.getText();
					int eliminar = Integer.parseInt(aux);  
					model.removeRow(eliminar-1);
				}
			}
			else 
				caja3.setText("Error");
			if(model.getRowCount()==0) {
				contador=1 ;
			}				
			}
			catch(Exception exception){
				botonEliminar.setVisible(false); 
				JOptionPane.showMessageDialog(this,"Error por ID");
			}
		break;
		case "EDITAR":
			model.setValueAt(caja.getText(), (Integer.valueOf(caja3.getText())-1), 1);
			model.setValueAt(caja2.getText(), (Integer.valueOf(caja3.getText())-1), 2);
		break;
		default:
		} 
		if(contador>=1) {
			botonEditar.setVisible(true);
			botonEliminar.setVisible(true);
		}
		if(contador==1) {
			botonEditar.setVisible(false);
			botonEliminar.setVisible(false);
		}
	}
	 
	public static void main(String[] args) {
		ImplementacionTabla table = new ImplementacionTabla();
	}
}

