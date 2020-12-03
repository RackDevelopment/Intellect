package github.rackdevelopment.logger;

import com.sun.istack.internal.NotNull;
import github.rackdevelopment.Intellect;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.*;

public class Logger {

    private final Intellect intellect;
    private File errorFile, warningsFile;

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

    public void error(@NotNull Throwable throwable) {
        System.out.println("[Intellect] [ERROR] Error occurred!");
        System.out.println(ExceptionUtils.getStackTrace(throwable));
        System.out.println("[Intellect] [INFO] Error saved to errors.txt");
        errorFile = new File(intellect.getDataFolder(), "errors.txt");
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

    public void warn(String reason) {
        System.out.println("[Intellect] [WARN] " + reason);
        System.out.println("[Intellect] [INFO] Warning saved to warnings.txt");
        warningsFile = new File(intellect.getDataFolder(), "warnings.txt");
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
