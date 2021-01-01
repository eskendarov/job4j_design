package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class FileProperties {

    final private File file;

    public FileProperties(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileProperties)) {
            return false;
        }
        final FileProperties that = (FileProperties) o;
        return that.file.length() == that.file.length()
                && this.file.getName().equals(that.file.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(file.length(), file.getName());
    }
}
