package components.pin;

import interfaces.iReaderRegisteredPins;
import main.Manifest;
import utils.files.tools.FileReader;

import javax.servlet.ServletContext;
import java.io.IOException;

public class FileReaderRegisteredPins implements iReaderRegisteredPins
{

    private ServletContext context;

    public FileReaderRegisteredPins(ServletContext context) {
        this.context = context;
    }

    @Override
    public String getRegisteredPinsAsString()
    {
        final FileReader fileReader = new FileReader(context.getRealPath(Manifest.FILE_REGISTERED_PINS));

        try {
            return fileReader.readFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
