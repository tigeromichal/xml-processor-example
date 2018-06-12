package pl.lodz.p.pkck.xmlprocessorexample.converter;

import org.apache.fop.apps.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.xmlprocessorexample.exception.FopPdfConversionException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class FopPdfConverter {

    Logger log = LoggerFactory.getLogger(FopPdfConverter.class);

    public void write(String xmlPath, String xsltPath, String outputPdfPath) throws FopPdfConversionException {
        try (OutputStream out = new java.io.FileOutputStream("./" + outputPdfPath)) {
            File xsltFile = new File("./" + xsltPath);
            StreamSource xmlSource = new StreamSource(new File("./" + xmlPath));
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new FopPdfConversionException(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FopPdfConversionException(e.getMessage(), e);
        } catch (TransformerConfigurationException e) {
            log.error(e.getMessage(), e);
            throw new FopPdfConversionException(e.getMessage(), e);
        } catch (TransformerException e) {
            log.error(e.getMessage(), e);
            throw new FopPdfConversionException(e.getMessage(), e);
        } catch (FOPException e) {
            log.error(e.getMessage(), e);
            throw new FopPdfConversionException(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new FopPdfConversionException(e.getMessage(), e);
        }
    }

}
