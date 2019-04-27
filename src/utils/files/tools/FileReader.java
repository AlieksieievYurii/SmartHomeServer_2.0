package utils.files.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader implements iReadFile {
    private File file;

    public FileReader(String path) {
        this.file = new File(path);

        if(!file.isFile())
            createNewFile(path);
    }

    private static void createNewFile(String path) {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write("[]");
    }

    @Override
    public String readFile() throws IOException {
        final BufferedReader bufferedReader =
                new BufferedReader(new java.io.FileReader(file));

        final StringBuilder textFromFile = new StringBuilder();

        String str;
        while ((str = bufferedReader.readLine()) != null)
            textFromFile.append(str);

        bufferedReader.close();

        return textFromFile.toString();

    }
}
