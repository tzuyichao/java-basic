package com.baeldung.logisticregression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MnistClassifier {
    private static final Logger logger = LoggerFactory.getLogger(MnistClassifier.class);
    private static final String basePath = System.getProperty("java.io.tmpdir") + "mnist" + File.separator;
    private static final File modelPath = new File(basePath + "minst-model.zip");

    public static void main(String[] args) {
        logger.info("basePath: {}", basePath);
        logger.info("modelPath: {}", modelPath.toString());
    }
}
