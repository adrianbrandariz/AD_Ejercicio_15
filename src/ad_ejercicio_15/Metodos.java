package ad_ejercicio_15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author abrandarizdominguez
 */
public class Metodos {

    XMLOutputFactory xof = null;
    XMLStreamWriter xsw = null;

    public void crearFicheroXML(String nombreFichero) {
        try {
            xof = XMLOutputFactory.newInstance();
            xsw = xof.createXMLStreamWriter(new FileWriter(nombreFichero));
            // Escribe la declaracion XML con la versión especificada:
            xsw.writeStartDocument("1.0");
            // Se crea el elemento padre:
            xsw.writeStartElement("autores");
            // Se crea el primer elemento:
            xsw.writeStartElement("autor");
            xsw.writeAttribute("codigo", "a1");
            xsw.writeStartElement("nome");
            xsw.writeCharacters("Alexandre Dumas");
            xsw.writeEndElement();
            xsw.writeStartElement("titulo");
            xsw.writeCharacters("El conde de montecristo");
            xsw.writeEndElement();
            xsw.writeStartElement("titulo");
            xsw.writeCharacters("Los miserables");
            xsw.writeEndElement();
            xsw.writeEndElement();
            // Se crea el segundo elemento:
            xsw.writeStartElement("autor");
            xsw.writeAttribute("codigo", "a2");
            xsw.writeStartElement("nome");
            xsw.writeCharacters("Dostoyevski");
            xsw.writeEndElement();
            xsw.writeStartElement("titulo");
            xsw.writeCharacters("El idiota");
            xsw.writeEndElement();
            xsw.writeStartElement("titulo");
            xsw.writeCharacters("Noches blancas");
            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.flush();
            xsw.close();
        } catch (IOException | XMLStreamException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void leerXML() {
        try {
            // StreamReader para el archivo recogido por un FileInputStream:
            XMLStreamReader xsr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream("autores.xml"));
            // Bucle que abre el flujo de datos:
            while (xsr.hasNext()) {
                xsr.next();
                // Si el elemento es un inicio de elemento:
                if (xsr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    // Damos los valores posibles del elemento a estas variables:
                    String elemento = xsr.getAttributeLocalName(0);
                    String atributo = xsr.getAttributeValue(0);
                    // Si el elemento tiene nombre y atributo imprime esta cadena:
                    if (elemento != null && atributo != null) {
                        System.out.println("<" + xsr.getLocalName() + " " + elemento + "=" + '"' + atributo + '"' + ">");
                    } else {
                        System.out.println("<" + xsr.getLocalName() + ">");
                    }
                    // Si el elemento seleccionado es un contenido:
                } else if (xsr.getEventType() == XMLStreamConstants.CHARACTERS) {
                    System.out.println(xsr.getText());
                    // Si es el final del elemento:
                } else if ((xsr.getEventType() == XMLStreamConstants.END_ELEMENT)) {
                    System.out.println("<" + xsr.getLocalName() + ">");
                }
            }
            // Se cierra el flujo de datos:
            xsr.close();
        } catch (XMLStreamException | FileNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

}
