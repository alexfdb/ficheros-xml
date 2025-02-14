package com.alexfdb.crudset;

import java.util.Set;

import com.alexfdb.basicxmloperations.OperationsXML;
import com.alexfdb.model.Empleado;
/**
 * @author alexfdb
 * @version 1.0.0
 */
public class CrudSet extends OperationsXML {

    public Set<Empleado> empleados = readXML();

    /**
     * Actualiza el xml.
     * @return retorna true si el xml fue actualizado.
     */
    public boolean updateXml() {
        if(empleados ==  null) return false;
        return writeXML(empleados);
    }

    /**
     * Muestra la lista de empleados.
     * @return retorna la lista de empleados.
     */
    public Set<Empleado> readEmpleados() {
        if(empleados ==  null) return null;
        return empleados;
    }
    
    /**
     * Agrega un nuevo empleado a la lista.
     * @param empleado empleado a agregar a la lista.
     * @return retorna true si el empleado fue agregado.
     */
    public boolean create(Empleado empleado) {
        if(empleado == null) return false;
        return empleados.add(empleado);
    }

    /**
     * Muestra un empleado si este se encuentra en la lista.
     * @param empleado empleado a buscar en la lista.
     * @return retorna el empleado si este se encuentra en la lista.
     */
    public Empleado read(Empleado empleado) {
        if(empleado == null) return null;
        for (Empleado e : empleados) {
            if(e.getId() == empleado.getId()) {
                return e;
            }
        }
        return null;
    }

    /**
     * Actualiza un empleado si este se encuentra en la lista.
     * @param empleado empleado a actualizar en la lista.
     * @return retorna true si el empleado fue actualizado.
     */
    public boolean update(Empleado empleado) {
        if(empleado == null) return false;
        if(empleados.removeIf(e -> e.getId() == empleado.getId())) {
            return empleados.add(empleado);
        }
        return false;
    }

    /**
     * Elimina un empleado de la lista.
     * @param empleado empleado a eliminar de la lista.
     * @return retorna true si el empleado fue eliminado.
     */
    public boolean delete(Empleado empleado) {
        if(empleado == null) return false;
        return empleados.removeIf(e -> e.getId() == empleado.getId());
    }

}