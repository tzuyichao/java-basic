package org.greenrivers.synonym;

import org.greenrivers.model.Term;

import java.util.List;

public interface SynonymManager {
    List<Term> expand(String term);
}
