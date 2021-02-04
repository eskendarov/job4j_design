package ru.job4j.io.serialization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.CoreMatchers.is;

public class XmlToPojoTest {

    private File file;
    private Account account;
    private Unmarshaller unmarshal;

    @Before
    public void init() throws JAXBException, IOException {
        file = Files.createTempFile(null, null).toFile();
        account = new Account(true, 23, "Kot",
                new Contact(55, "734552"),
                new String[]{"cbb43", "f34f3", "23d4", "2d23", "d3d45"});
        Marshaller marshal = JAXBContext.newInstance(Account.class).createMarshaller();
        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshal.marshal(account, file);

    }

    @Test
    public void whenAccountEquals() throws JAXBException {
        unmarshal = JAXBContext.newInstance(Account.class).createUnmarshaller();
        final Account expected = (Account) unmarshal.unmarshal(file);
        Assert.assertThat(expected, is(account));
    }

    @Test(expected = UnmarshalException.class)
    public void whenAccountNotEquals() throws JAXBException {
        unmarshal = JAXBContext.newInstance(Object.class).createUnmarshaller();
        final Account expected = (Account) unmarshal.unmarshal(file);
        Assert.assertThat(expected, is(account));
    }
}
