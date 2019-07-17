package org.greenrivers.synonym.impl;

import org.greenrivers.model.Term;
import org.greenrivers.synonym.SynonymManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DummySynonymManager implements SynonymManager {
    @Override
    public List<Term> expand(String term) {
        if("idea".equalsIgnoreCase(term)) {
            return Arrays.asList(new Term("design"), new Term("belief"));
        } else {
            return Collections.emptyList();
        }
    }
}
