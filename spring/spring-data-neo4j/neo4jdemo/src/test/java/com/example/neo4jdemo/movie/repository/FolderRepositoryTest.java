package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Document;
import com.example.neo4jdemo.movie.model.Folder;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Log
@Testcontainers
@DataNeo4jTest
@ImportAutoConfiguration(classes={com.example.neo4jdemo.movie.config.DatabaseConfig.class})
public class FolderRepositoryTest {
    private FolderRepository folderRepository;
    private DocumentRepository documentRepository;
    private SessionFactory sessionFactory;

    @Autowired
    public FolderRepositoryTest(FolderRepository folderRepository,
                                DocumentRepository documentRepository,
                                SessionFactory sessionFactory) {
        this.folderRepository = folderRepository;
        this.documentRepository = documentRepository;
        this.sessionFactory = sessionFactory;
    }

    @Test
    void test_connected_object() {
        Folder folder = new Folder();
        folder.setName("folder");
        Document a = new Document();
        a.setName("a");
        Document b = new Document();
        b.setName("b");

        folder.getDocuments().add(a);
        folder.getDocuments().add(b);
        a.setParent(folder);
        b.setParent(folder);

        folderRepository.save(folder);
        log.info("=========== After save folder");

        a.setName("A");
        b.setName("B");

        // 官方文件用session物件可以改到b，但是實測用這樣不行
        // Update: Document Model要有Folder這樣才連得起來
        documentRepository.save(a);
    }

    @Test
    void test_connected_object_with_session() {
        Session session = sessionFactory.openSession();
        Folder folder = new Folder();
        folder.setName("folder");
        Document a = new Document();
        a.setName("a");
        Document b = new Document();
        b.setName("b");

        folder.getDocuments().add(a);
        folder.getDocuments().add(b);
        a.setParent(folder);
        b.setParent(folder);

        session.save(folder);
        log.info("=========== After save folder");

        a.setName("A");
        b.setName("B");

        session.save(a);
    }
}
