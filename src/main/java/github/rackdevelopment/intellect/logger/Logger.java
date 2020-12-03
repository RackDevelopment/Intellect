package github.rackdevelopment.intellect.logger;

import com.sun.istack.internal.NotNull;
import github.rackdevelopment.intellect.Intellect;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.*;

/**
 * <h1>Intellect Logger</h1>
 * The Intellect Logger is a spin-off of the default Spigot one, to provide some minor adjustments to fit out needs.
 *
 * @author RackDevelopment
 * @version 1.0.2
 */
public class Logger {

    private final Intellect intellect;
    private final File errorFile, warningsFile;

    /**
     * Constructor for dependency injection and to check if the files are created or not.
     *
     * @param intellect the dependency injection way of getting the primary Class.
     */
    public Logger(Intellect intellect) {
        this.intellect = intellect;
        errorFile = new File(intellect.getDataFolder(), "errors.txt");
        warningsFile = new File(intellect.getDataFolder(), "warnings.txt");
        if (errorFile.exists()) {
            System.out.println("[Intellect] [WARN] The errors.txt file exists! Please check the error and delete when it is solved.");
        }
        if (warningsFile.exists()) {
            System.out.println("[Intellect] [WARN] The warnings.txt file exists! Please check the warnings and delete when it is solved.");
        }
    }

    /**
     * Generate an error that was into the system console and into the errors file
     *
     * @param throwable any form of exception
     */
    public void error(@NotNull Throwable throwable) {
        System.out.println("[Intellect] [ERROR] Error occurred!");
        System.out.println(ExceptionUtils.getStackTrace(throwable));
        System.out.println("[Intellect] [INFO] Error saved to errors.txt");
        if (!errorFile.exists()) {
            errorFile.getParentFile().mkdirs();
            intellect.saveResource("errors.txt", false);
        }
        try {
            Writer output = new BufferedWriter(new FileWriter(errorFile, true));
            output.append("[ERROR] Created at " + System.currentTimeMillis() + ", stacktrace: " + ExceptionUtils.getStackTrace(throwable));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate a warning that is sent into the system console and saved to the warnings file
     *
     * @param reason a typed-out warning
     */
    public void warn(String reason) {
        System.out.println("[Intellect] [WARN] " + reason);
        System.out.println("[Intellect] [INFO] Warning saved to warnings.txt");
        if (!errorFile.exists()) {
            warningsFile.getParentFile().mkdirs();
            intellect.saveResource("warnings.txt", false);
        }
        try {
            Writer output = new BufferedWriter(new FileWriter(warningsFile, true));
            output.append("[WARN] Created at " + System.currentTimeMillis() + ", reason: " + reason);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
