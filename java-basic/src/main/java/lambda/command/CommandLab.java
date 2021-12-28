package lambda.command;

public class CommandLab {
    public static void main(String[] args) {
        var hd = new HardDisk();
        var sequence = new Sequence();
        sequence.recordSequence(hd::copy);
        sequence.recordSequence(hd::delete);
        sequence.recordSequence(hd::move);
        sequence.recordSequence(hd::delete);
        sequence.runSequence();
    }
}
