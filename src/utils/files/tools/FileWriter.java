package utils.files.tools;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter implements iWriteFile
{
    private File file;

    public FileWriter(String path)
    {
        this.file = new File(path);
    }


    @Override
    public boolean write(String text) {
        try {
            writeFile(text,file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void writeFile(String text, File file) throws IOException {
        final java.io.FileWriter fileWriter = new java.io.FileWriter(file);
        final PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(text);
        printWriter.close();
        fileWriter.close();
    }
}
