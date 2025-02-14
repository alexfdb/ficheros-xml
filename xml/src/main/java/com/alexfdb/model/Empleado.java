package com.alexfdb.model;
import java.util.Objects;
/**
 * @author alexfdb
 * @version 1.0.0
 */
public class Empleado {
    private int id;
    private String nombre;
    private double salario;

    /**
     * Constructor por defecto.
     */
    public Empleado() {
    }

    /**
     * Constructor general.
     * @param id id del empleado.
     * @param nombre nombre del empleado.
     * @param salario salario del empleado.
     */
    public Empleado(int id, String nombre, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Empleado)) {
            return false;
        }
        Empleado empleado = (Empleado) o;
        return id == empleado.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, salario);
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + salario;
    }

}
