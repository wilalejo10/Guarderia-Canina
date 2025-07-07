package Controlador;
import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Modelo.Propietario;
import Modelo.Cuidador;
import Modelo.Mascota;
import Modelo.Servicio;
import Modelo.Factura;

public class ControladorFinal implements ActionListener {
    Formulario vista;
    Propietario modelo;

    public ControladorFinal() {
        vista = new Formulario();
        vista.setVisible(true);
        vista.getBotonregistrocliente().addActionListener(this);
        vista.getBotonactualizacion().addActionListener(this);
        vista.getBotonfactura().addActionListener(this);
        vista.getBTgenerarFactura().addActionListener(this);
        vista.getBotonbuscaractualizacion().addActionListener(this);
        vista.getBotonregistrocuidador().addActionListener(this);
        vista.getBotonregistromascota().addActionListener(this);
        vista.getBotonservicio().addActionListener(this);
        vista.getBtactualizarcuidador().addActionListener(this);
        vista.getBtBuscarCuidador().addActionListener(this);
        vista.getBTeliminar().addActionListener(this);
        vista.getBteliminarRegistroB().addActionListener(this);
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


if (e.getSource() == vista.getBotonbuscaractualizacion()) {
    String cedula = vista.getTxtactualizarcedula().getText().trim();

    if (cedula.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "Por favor ingrese una cédula para buscar.");
        return;
    }

    Propietario p = new Propietario();
    boolean encontrado = p.buscarPorCedula(cedula); 

    if (encontrado) {
        
        vista.getTxtactualizacionnombre().setText(p.getNombre());
        vista.getTxtactualizaciondireccion().setText(p.getDireccion());
        vista.getTxtactualizaciontelefono().setText(p.getTelefono());
        vista.getTxtactualizacioncorreo().setText(p.getCorreo());

    } else {
        JOptionPane.showMessageDialog(vista, "Propietario no encontrado.");
    }
}

 
if (e.getSource() == vista.getBotonactualizacion()) {
    try {
      
        String cedula = vista.getTxtactualizarcedula().getText().trim();
        String nombre = vista.getTxtactualizacionnombre().getText().trim();
        String direccion = vista.getTxtactualizaciondireccion().getText().trim();
        String telefono = vista.getTxtactualizaciontelefono().getText().trim();
        String correo = vista.getTxtactualizacioncorreo().getText().trim();

        Propietario p = new Propietario(nombre, cedula, telefono, direccion, correo);
        p.actualizarCliente(cedula); 

        JOptionPane.showMessageDialog(vista, "Datos actualizados correctamente.");

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al actualizar: " + ex.getMessage());
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
        cuidador.crearCuidador();

        JOptionPane.showMessageDialog(vista, "Cuidador registrado correctamente");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al registrar cuidador: " + ex.getMessage());
    }
}


if (e.getSource() == vista.getBtBuscarCuidador()) {
    String cedulaCI = vista.getTxtcedulacuidador().getText().trim();

    if (cedulaCI.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "Por favor ingrese una cédula para buscar.");
        return;
    }

    Cuidador cuidador = new Cuidador();
    boolean encontrado = cuidador.buscarPorCedulaCI(cedulaCI); 

    if (encontrado) {
        vista.getTxtnombrecuidador().setText(cuidador.getNombre());
        vista.getTelefonocui().setText(cuidador.getTelefono());
        vista.getTxtedadcuidador().setText(String.valueOf(cuidador.getEdad()));
        vista.getTxtturnocuidador().setText(cuidador.getTurno());
        vista.getTxtespecialidadcuidador().setText(cuidador.getEspecialidad());
        vista.getTxtcargocuidador().setText(cuidador.getCargo());
    } else {
        JOptionPane.showMessageDialog(vista, "Cuidador no encontrado.");
    }
}

 if (e.getSource() == vista.getBtactualizarcuidador()) {
    try {
        String cedula = vista.getTxtcedulacuidador().getText().trim();
        String nombre = vista.getTxtnombrecuidador().getText().trim();
        String telefono = vista.getTelefonocui().getText().trim();
        int edad = Integer.parseInt(vista.getTxtedadcuidador().getText().trim());
        String turno = vista.getTxtturnocuidador().getText().trim();
        String especialidad = vista.getTxtespecialidadcuidador().getText().trim();
        String cargo = vista.getTxtcargocuidador().getText().trim();
        Cuidador c = new Cuidador();
        c.setCedula(cedula);
        c.setNombre(nombre);
        c.setTelefono(telefono);
        c.setEdad(edad);
        c.setTurno(turno);
        c.setEspecialidad(especialidad);
        c.setCargo(cargo);
        c.actualizarCuidador(cedula);

        JOptionPane.showMessageDialog(vista, "Datos del cuidador actualizados correctamente.");

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al actualizar cuidador: " + ex.getMessage());
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

if (e.getSource() == vista.getBotonservicio()) {
    try {
        String cedulaProp = vista.getTxtcedulaservicio().getText();
        String nombreServicio = vista.getComboservicio().getSelectedItem().toString();

        Servicio servicio = Servicio.obtenerServicioPorNombre(nombreServicio);

        if (servicio == null) {
            JOptionPane.showMessageDialog(vista, "Servicio no encontrado.");
            return;
        }

        if (servicio.getDisponibles() <= 0) {
            JOptionPane.showMessageDialog(vista, "No hay cupos disponibles para este servicio.");
            return;
        }

        boolean registrado = servicio.registrarServicioSolicitado(cedulaProp, servicio.getIdServicio());

        if (registrado) {
            int nuevaDisponibilidad = servicio.getDisponibles() - 1;
            servicio.actualizarDisponibilidad(servicio.getIdServicio(), nuevaDisponibilidad);

            JOptionPane.showMessageDialog(vista, "Servicio registrado y aforo actualizado.");
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo registrar el servicio.");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al registrar servicio: " + ex.getMessage());
    }
}

if (e.getSource() == vista.getBTeliminar()) {
    try {
        String cedulaProp = vista.getTxtcedulaservicio().getText();
        String nombreServicio = vista.getComboservicio().getSelectedItem().toString();

        Servicio servicio = Servicio.obtenerServicioPorNombre(nombreServicio);
        if (servicio == null) {
            JOptionPane.showMessageDialog(vista, "Servicio no encontrado.");
            return;
        }

        boolean eliminado = servicio.eliminarServicioSolicitado(cedulaProp, servicio.getIdServicio());

        if (eliminado) {
            int nuevaDisponibilidad = servicio.getDisponibles() + 1;
            servicio.actualizarDisponibilidad(servicio.getIdServicio(), nuevaDisponibilidad);
            JOptionPane.showMessageDialog(vista, "Servicio eliminado y aforo actualizado.");
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo eliminar el servicio.");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al eliminar servicio: " + ex.getMessage());
    }
}
if (e.getSource() == vista.getBTgenerarFactura()) {
    try {
        String idFacturaStr = vista.getTxtconsulta().getText().trim();
        
        if (idFacturaStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor ingrese un ID de factura.");
            return;
        }

        int idFactura = Integer.parseInt(idFacturaStr);

        Factura factura = new Factura();
        factura.reporteFactura(idFactura);

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(vista, "ID de factura inválido. Debe ser un número.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al generar la factura: " + ex.getMessage());
        ex.printStackTrace();
    }
}

if (e.getSource() == vista.getBteliminarRegistroB()) {
    try {
        String cedula = vista.getTxteliminar().getText().trim();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor ingrese la cédula del cliente a eliminar.");
            return;
        }
        Propietario p = new Propietario();
        p.eliminarCliente(cedula);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al eliminar cliente: " + ex.getMessage());
    }
}

if (e.getSource() == vista.getBotonbuscarregistro()) {
    try {
        String cedula = vista.getTxteliminar().getText().trim();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor ingrese una cédula para buscar.");
            return;
        }

        String[] datos = new String[4];
        Propietario p = new Propietario();
        datos = p.buscarPropietario(cedula, datos);

        vista.getTxtnombre().setText(datos[0]);
        vista.getTxtdireccion().setText(datos[1]);
        vista.getTxttelefono().setText(datos[2]);
        vista.getTxtcorreo().setText(datos[3]);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al buscar: " + ex.getMessage());
    }

            
}
}
}

