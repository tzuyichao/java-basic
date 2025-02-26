import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

/**
 * 用JetBrains執行會無法使用System terminal。要在Terminal中執行才行。
 */
public class TerminalLab {
    public static void main(String[] args) {
        try(Terminal terminal = TerminalBuilder.builder()
                .dumb(false)
                .jansi(true)
                .system(true)
                .streams(System.in, System.out)
                .build()) {
            System.out.println(terminal.getName() + ": " + terminal.getType());
            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();
            String line;
            while(true) {
                line = reader.readLine("> ");
                if("exit".equalsIgnoreCase(line)) {
                    break;
                }
                System.out.println("Echo: " + line);
            }
            System.out.println("Goodbye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
