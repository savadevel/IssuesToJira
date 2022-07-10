package ru.savadevel.issuestojira.util.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xml.sax.SAXException;
import ru.savadevel.issuestojira.to.FormTo;
import ru.savadevel.issuestojira.to.ProjectTo;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

@Component
public class FormToValidation implements Validator {

    private static final Logger log = LoggerFactory.getLogger(FormToValidation.class);

    @Value("${file.xml.schema.validation}")
    private String fileXmlSchemaValidation;

    @Override
    public boolean supports(Class<?> clazz) {
        return FormTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormTo formTo = (FormTo) target;

        try {
            JAXBContext context = JAXBContext.newInstance(ProjectTo.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema projectToSchema = sf.newSchema(new File(fileXmlSchemaValidation));
            unmarshaller.setSchema(projectToSchema);
            unmarshaller.unmarshal(formTo.getFile().getInputStream());
        } catch (JAXBException | IOException | SAXException e) {
            log.error("Error validation of XML file {}", e.getCause().getMessage());
            errors.rejectValue("file", "", e.getCause().getMessage());
        }
    }
}
