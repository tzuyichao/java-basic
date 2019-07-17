package org.greenrivers.segmenter;

import org.greenrivers.model.Term;

import java.util.List;

public interface Segmenter {
    List<Term> segment(String rawText);
}
