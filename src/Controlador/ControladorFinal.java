/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Modelo.Propietario;
import Modelo.Cuidador;
import Modelo.Mascota;
import Modelo.Servicio;

public class ControladorFinal implements ActionListener {
    Formulario vista;

    public ControladorFinal() {
        vista = new Formulario();
        vista.setVisible(true);

     
        vista.getBotonregistrocliente().addActionListener(this);
        vista.getBotonactualizacion().addActionListener(this);
        vista.getBotonfactura().addActionListener(this);
        vista.getBotonbuscaractualizacion().addActionListener(this);
        vista.getBotonregistrocuidador().addActionListener(this);
        vista.getBotonregistromascota().addActionListener(this);
        vista.getBotonservicio().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getBotonregistrocliente()) {
            try {
                String nombre = vista.getTxtnombre().getText();
                String cedula = vista.getTxtcedularegistrocliente().getText();
                String correo = vista.getTxtcorreo().getText();
                String direccion = vista.getTxtdireccion().getText();
                String telefono = vista.getTxttelefono().getText();

                if (nombre.isEmpty() || cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Nombre y cédula son obligatorios");
                    return;
                }

                Propietario propietario = new Propietario(nombre, cedula, telefono, direccion, correo);
                propietario.crearPropietario();

                JOptionPane.showMessageDialog(vista, "Propietario registrado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al registrar: " + ex.getMessage());
            }
        }

 
        if (e.getSource() == vista.getBotonregistrocuidador()) {
            try {
                String cedula = vista.getTxtcedulacuidador().getText();
                String nombre = vista.getTxtnombrecuidador().getText();
                String telefono = vista.getTelefonocui().getText();
                int edad = Integer.parseInt(vista.getTxtedadcuidador().getText());
                String turno = vista.getTxtturnocuidador().getText();
                String especialidad = vista.getTxtespecialidadcuidador().getText();
                String cargo = vista.getTxtcargocuidador().getText();

                Cuidador cuidador = new Cuidador(nombre, cedula, telefono, edad, turno, especialidad, cargo);
           

                JOptionPane.showMessageDialog(vista, "Cuidador registrado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al registrar cuidador: " + ex.getMessage());
            }
        }

 
        if (e.getSource() == vista.getBotonregistromascota()) {
            try {
                int id = Integer.parseInt(vista.getTxtidentifiacionmascota().getText());
                String cedulaPropietario = vista.getTxtcedulamascota().getText();
                String nombreMascota = vista.getTxtnombremascota().getText();
                String raza = vista.getComboraza().getSelectedItem().toString();
                String edad = vista.getTxtedadmascota().getText();
                String peso = vista.getTxtpesomascota().getText();

                Mascota mascota = new Mascota(id, nombreMascota, raza, edad, peso, cedulaPropietario);
                mascota.crearMascota();

                JOptionPane.showMessageDialog(vista, "Mascota registrada correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al registrar mascota: " + ex.getMessage());
            }
        }

  
       

        if (e.getSource() == vista.getBotonactualizacion()) {
        
            JOptionPane.showMessageDialog(vista, "Función de actualización pendiente");
        }

        if (e.getSource() == vista.getBotonbuscaractualizacion()) {
    
            JOptionPane.showMessageDialog(vista, "Función de búsqueda pendiente");
        }

        if (e.getSource() == vista.getBotonfactura()) {
    
            JOptionPane.showMessageDialog(vista, "Función de facturación pendiente");
        }
    }
}
