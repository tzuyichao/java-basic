package core.operator;

import com.google.common.collect.ImmutableList;
import io.reactivex.rxjava3.core.Observable;

public class Ch3_40 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .collect(ImmutableList::builder, ImmutableList.Builder::add)
                .subscribe(s -> System.out.println("RECEIVED: " + s + ":" +s.build()));
    }
}
