package com.alexfdb.basicxmloperations;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alexfdb.model.Empleado;
/**
 * @author alexfdb
 * @version 1.0.0
 */
public class OperationsXML {

    static String path = "src\\main\\resources\\empleados.xml";

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