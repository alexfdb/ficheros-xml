package com.alexfdb;

import com.alexfdb.crudset.CrudSet;
import com.alexfdb.model.Empleado;
/**
 * @author alexfdb
 * @version 1.0.0
 */
public class Main {
    public static void main(String[] args) {

        CrudSet crudSet = new CrudSet();
         
        Empleado empleado = new Empleado(1, "Alex", 1000);
        Empleado empleado2 = new Empleado(2, "Barbara", 1000);
        Empleado empleadoActualizado = new Empleado(1, "Alex", 2000);

        System.out.println("----------Agregando empleados----------");
        System.out.println(crudSet.create(empleado));
        System.out.println(crudSet.create(empleado2));

        System.out.println("----------Leyendo lista----------");
        System.out.println(crudSet.readEmpleados());

        System.out.println("----------Leyendo un empleado----------");
        System.out.println(crudSet.read(empleado));

        System.out.println("----------Actualzando un empleado----------");
        System.out.println(crudSet.update(empleadoActualizado));

        System.out.println("----------Eliminando un empleado----------");
        System.out.println(crudSet.delete(empleado2));

        System.out.println("----------Actualizando fichero----------");
        System.out.println(crudSet.updateXml());
        
    }
}