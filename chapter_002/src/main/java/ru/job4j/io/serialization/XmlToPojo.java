package ru.job4j.io.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class XmlToPojo {

    public static void main(String[] args) throws JAXBException, IOException {
        final Account account = new Account(true, 23, "Kot",
                new Contact(55, "734552"),
                new String[]{"cbb43", "f34f3", "23d4", "2d23", "d3d45"});
        // Получаем контекст для доступа к АПИ
        final JAXBContext context = JAXBContext.newInstance(Account.class);
        // Создаем сериализатор
        final Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // Сериализуем
        final File file = new File("./account.xml");
        marshaller.marshal(account, file);
        Files.lines(file.toPath()).forEach(System.out::println);
        // Для десериализации нам нужно создать десериализатор
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final Account expected = (Account) unmarshaller.unmarshal(file);
        System.out.println(expected);
    }
}
