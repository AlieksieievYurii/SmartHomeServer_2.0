package utils.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader implements iReadFile {
    private File file;

    public FileReader(String path) {
        this.file = new File(path);
    }

    @Override
    public String readFile() throws IOException {
        final BufferedReader bufferedReader =
                new BufferedReader(new java.io.FileReader(file));

        final StringBuilder textFromFile = new StringBuilder();

        String str;
        while ((str = bufferedReader.readLine()) != null)
            textFromFile.append(str);

        return textFromFile.toString();

    }
}
