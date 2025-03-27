import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class JavaProcess {

    private JavaProcess() {
    }

    public static Thread start_exec_timer(Process p, long timeout_ms) {
        var task = new Thread(() -> {
            try {
                Thread.sleep(timeout_ms);
                p.destroy();
            } catch (InterruptedException e) {

            }
        });
        task.start();
        return task;
    }

    public static Process exec(Class clazz, List<String> jvmArgs, List<String> args) throws IOException,
            InterruptedException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = clazz.getName();

        List<String> command = new ArrayList<>();
        command.add(javaBin);
        if (jvmArgs != null) {
            command.addAll(jvmArgs);
        }
        command.add("-cp");
        command.add(classpath);
        command.add(className);
        if (args != null) {
            command.addAll(args);
        }

        ProcessBuilder builder = new ProcessBuilder(command);
//        builder.redirectInput(ProcessBuilder.Redirect.PIPE);
//        builder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        Process process = builder.start();
        return process;
    }
}
