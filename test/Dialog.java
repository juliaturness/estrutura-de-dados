import org.javatuples.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Dialog {

    BufferedReader raw_inp;
    Scanner inp;
    PrintStream out;
    List<Pair<String,String>> result;

    Dialog(Process p) {
        out = get_output(p);
        inp = get_input(p);
        raw_inp = new BufferedReader (new InputStreamReader(p.getInputStream()));
        result = new ArrayList<>();
    }

    private static Scanner get_input(Process p) {
        InputStream _inp = p.getInputStream();
//        BufferedReader reader = new BufferedReader (new InputStreamReader(_inp));
        return new Scanner(System.in);
    }

    private static PrintStream get_output(Process p) {
        OutputStream _out = p.getOutputStream();
        return new PrintStream(_out);
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(_out));
//        return writer;
    }

    /*
    Read a string matching regex "expected". If not found, return what was read in value1 of Pair.
     */
    private Pair<String,String> read_expected(String expected) throws IOException, InterruptedException {
        String buffer = "";
        String rx = null;
        var pat = Pattern.compile(expected);
        boolean achou = true;
        int estado = 0; // 0=não achou ainda, 1=achou, 2=parou de corresponder

        while (achou) {
            char[] algo = {'0'};
            if (raw_inp.read(algo, 0, 1) != -1) {
                if (algo[0] == '\n') break;
                buffer += algo[0];
            }
//            System.out.printf("...%s: %c, %b\n", expected, algo[0], raw_inp.ready());
            var m = pat.matcher(buffer);

            achou = m.find();

            // this small FSM detects when buffer starts to match the pattern,
            // and when it stops to match
            switch (estado) {
                case 0:
                    if (achou) {
                        estado = 1;
                    }
                    break;
                case 1:
                    if (! achou) {
                        buffer = buffer.substring(0, buffer.length() - 1);
                        estado = 2;
                    }
                case 2:
                    break; // não deveria chegar aqui
            }
            // A trick to wait at least one char from the input stream
            for (int j=0; j < 20 && !raw_inp.ready(); j++) {
//                System.out.println("esperando ...");
                Thread.sleep(1);
            }
            achou = (achou || m.hitEnd()) && raw_inp.ready();
        }
        buffer = buffer.strip();
        // if pattern was not found at all ...
        if (estado == 0) {
            rx = buffer;
            buffer = null;
        }
        return new Pair<String,String>(buffer, rx);
    }

    boolean run(List<Pair<String,String>> dialog) throws IOException, InterruptedException {
        result.clear();
        for(var par: dialog) {
            String rx = null;
            var expected = par.getValue1();
            var sent = par.getValue0();
            if (sent != null) {
                out.println(sent);
                out.flush();
            }
            if (expected != null) {
                var par_rx = read_expected(expected);
//                System.out.println("rx=" + par_rx);
                if (par_rx.getValue1() == null) {
                    result.add(new Pair<>(sent, par_rx.getValue0()));
                } else {
                    rx = String.format("%s (%s)", par_rx.getValue1(), expected);
                    result.add(new Pair<>(sent, rx));
                    return false;
                }
            }
        }
        return true;
    }

    static void run_and_check(Class classe, String[][] conversa) throws IOException, InterruptedException {
        var p = JavaProcess.exec(classe, List.of("-Xmx200m"), null);
        assertTrue(p.isAlive());
        var task = JavaProcess.start_exec_timer(p, 5000);

        var dial = new Dialog(p);
        var pars = Dialog.make_pairs(conversa);

        if (! dial.run(pars)) {
            var result = dial.get_result();
            for(var par: result) {
                System.out.printf("%s -> %s\n", par.getValue0(), par.getValue1());
            }
            fail();
        }
    }

    List<Pair<String, String>> get_result() {
        return result;
    }

    static List<Pair<String,String>> make_pairs(String[][] pares) {
        return Arrays.stream(pares)
                      .map((String[] linha) -> new Pair<>(linha[0], linha[1]))
                      .toList();
    }
}
