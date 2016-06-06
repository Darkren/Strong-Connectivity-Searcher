import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Darkrengarius on 20.04.2016.
 */

/**
 * Creates a filter which allows to choose only .txt files
 */
class InputFileFilter extends FileFilter {
    /**
     * File extension
     */
    private String extension;
    /**
     * Description show in the chooser (".txt")
     */
    private String description;

    /**
     * Initializes class fields
     *
     * @param extension File extension
     * @param description Description
     */
    InputFileFilter(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }

    /**
     * Gets the description
     *
     * @return Description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the file should be shown in the file choosing dialog
     *
     * @param file File descriptor
     * @return True if the file is a directory or ends with ".txt", false -
     * otherwise
     */
    @Override
    public boolean accept(File file) {
        if(file != null) {
            if(file.isDirectory()) {
                return true;
            }
            return file.toString().endsWith(extension);
        }
        return false;
    }
}
