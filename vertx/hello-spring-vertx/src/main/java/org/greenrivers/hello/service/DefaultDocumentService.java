package org.greenrivers.hello.service;

import org.greenrivers.hello.model.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultDocumentService implements DocumentService {
    @Override
    public List<Document> getAllDocuments() {
        List<Document> docs = new ArrayList<>();
        docs.add(new Document(1, "test"));
        docs.add(new Document(2, "bla"));
        return docs;
    }
}
