package ru.job4j.io.serialization.xml;

import ru.job4j.io.serialization.Account;
import ru.job4j.io.serialization.Contact;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class XmlToPojo {

    public static void main(String[] args) throws JAXBException, IOException {
        final Account account = new Account(true, 23, "Kot",
                new Contact(55, "734552"),
                new String[]{"cbb43", "f34f3", "23d4", "2d23", "d3d45"});
        final JAXBContext context = JAXBContext.newInstance(Account.class);
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final File file = new File("./account.xml");
        marshaller.marshal(account, file);
        Files.lines(file.toPath()).forEach(System.out::println);
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final Account expected = (Account) unmarshaller.unmarshal(file);
        System.out.println(expected);
    }
}
