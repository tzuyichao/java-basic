package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 322. Coin Change
 *
 * Time Limit Exceeded
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        List<Integer> sortedCoins = Arrays.stream(coins).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        List<int[]> record = new ArrayList<>();
        calculate(amount, sortedCoins, record);
        if(record.size() == 0) {
            return -1;
        }
        List<Integer> res = record.stream().map(r -> {
            int sum = 0;
            for(int i=0; i<r.length; i++) {
                sum+=r[i];
            }
            return sum;
        }).collect(Collectors.toList());
        //System.out.println(res);
        int min = Integer.MAX_VALUE;
        for(int r : res) {
            if(r < min) {
                min = r;
            }
        }
        return min;
    }

    private boolean check(int amount, List<Integer> coins, int[] state) {
        int sum = 0;
        for(int i=0; i<state.length; i++) {
            sum += state[i] * coins.get(i);
        }
        return sum == amount;
    }

    private void helper(int amount, List<Integer> coins, int position, int[] state, List<int[]> db) {
        int sum = 0;
        if(position == coins.size()-1) {
            for(int i=0; i<state.length; i++) {
                sum += coins.get(i) * state[i];
            }
            state[position] = (amount - sum)/coins.get(position);
            if(check(amount, coins, state)) {
                db.add(state);
            }
        } else {
            for(int i=0; i<state.length; i++) {
                sum += coins.get(i) * state[i];
            }
            int count = (amount - sum)/coins.get(position);
            for(int i=0; i<=count; i++) {
                int[] newState = new int[state.length];
                System.arraycopy(state, 0, newState, 0, state.length);
                newState[position] = i;
                helper(amount, coins, position + 1, newState, db);
            }
        }
    }

    private void calculate(int amount, List<Integer> coins, List<int[]> db) {
        helper(amount, coins, 0, new int[coins.size()], db);
    }
}
