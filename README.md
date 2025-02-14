# Trabajado con ficheros xml

## A continuacion se muestra la salida del main

```code

----------Agregando empleados----------
true
true
----------Leyendo lista----------
[1,Alex,1000.0, 2,Barbara,1000.0]
----------Leyendo un empleado----------
1,Alex,1000.0
----------Actualzando un empleado----------
true
----------Eliminando un empleado----------
true
----------Actualizando fichero----------
true

```

## A continuacion se muestran las clases

### OperationsXML

```java

public class OperationsXML {

    static String path = "xml/src/main/resources/empleados.xml";

        public Set<Empleado> readXML() {
        Set<Empleado> empleados = new HashSet<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(path));

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("empleado");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    double salario = Double.parseDouble(element.getElementsByTagName("salario").item(0).getTextContent());

                    empleados.add(new Empleado(id, nombre, salario));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public boolean writeXML(Set<Empleado> data) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("empleados");
            document.appendChild(root);

            for (Empleado empleado : data) {
                Element empleadoElement = document.createElement("empleado");

                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(String.valueOf(empleado.getId())));
                empleadoElement.appendChild(id);

                Element nombre = document.createElement("nombre");
                nombre.appendChild(document.createTextNode(empleado.getNombre()));
                empleadoElement.appendChild(nombre);

                Element salario = document.createElement("salario");
                salario.appendChild(document.createTextNode(String.valueOf(empleado.getSalario())));
                empleadoElement.appendChild(salario);

                root.appendChild(empleadoElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));

            transformer.transform(source, result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

```

### CrudSet

```java

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

```