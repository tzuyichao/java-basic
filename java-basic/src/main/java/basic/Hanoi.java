package basic;

import java.util.Stack;

public class Hanoi {
    private final int numDiscs;
    private final Stack<Integer> towerA = new Stack<>();
    private final Stack<Integer> towerB = new Stack<>();
    private final Stack<Integer> towerC = new Stack<>();

    public Hanoi(int discs) {
        numDiscs = discs;
        for(int i=1; i<=numDiscs; i++) {
            towerA.push(i);
        }
    }

    private void move(Stack<Integer> begin, Stack<Integer> end, Stack<Integer> temp, int n) {
        if(n==1) {
            end.push(begin.pop());
        } else {
            move(begin, temp, end, n-1);
            move(begin, end, temp, 1);
            move(temp, end, begin, n-1);
        }
    }

    public void solve() {
        move(towerA, towerC, towerB, numDiscs);
    }

    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi(4);
        hanoi.solve();
        System.out.println(hanoi.towerA);
        System.out.println(hanoi.towerB);
        System.out.println(hanoi.towerC);
    }
}
