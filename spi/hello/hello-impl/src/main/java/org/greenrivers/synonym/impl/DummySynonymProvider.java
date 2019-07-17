package org.greenrivers.synonym.impl;

import org.greenrivers.synonym.SynonymManager;
import org.greenrivers.synonym.SynonymProvider;

public class DummySynonymProvider implements SynonymProvider {
    @Override
    public SynonymManager create() {
        return new DummySynonymManager();
    }
}
