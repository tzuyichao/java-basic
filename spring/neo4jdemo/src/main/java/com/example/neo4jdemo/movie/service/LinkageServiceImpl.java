package com.example.neo4jdemo.movie.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

@Service
public class LinkageServiceImpl implements LinkageService {
    @Value("${export.dir}")
    private String exportDir;

    private SessionFactory sessionFactory;

    public LinkageServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void export(String cypher, Map<String, ?> parameters, String filename) {
        Session session = sessionFactory.openSession();
        try (FileOutputStream excelFile = new FileOutputStream(new File(exportDir, filename))) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("export");

            Result result = session.query(cypher, parameters);
            result.forEach(r -> {
                int rowNum = sheet.getLastRowNum()+1;
                Row row = sheet.createRow(rowNum);
                if(rowNum == 0) {
                    int i = 0;
                    for(String key : r.keySet()) {
                        Cell cell = row.createCell(i);
                        cell.setCellValue(key);
                        i++;
                    }
                } else {
                    int i = 0;
                    for (String key : r.keySet()) {
                        Cell cell = row.createCell(i);
                        cell.setCellValue(r.get(key).toString());
                        i++;
                    }
                }
            });
            workbook.write(excelFile);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.clear();
        }
    }
}
