package com.baeldung.logisticregression;

import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.split.FileSplit;
import org.datavec.image.loader.NativeImageLoader;
import org.datavec.image.recordreader.ImageRecordReader;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.schedule.MapSchedule;
import org.nd4j.linalg.schedule.ScheduleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MnistClassifier {
    private static final Logger logger = LoggerFactory.getLogger(MnistClassifier.class);
    private static final String basePath = System.getProperty("java.io.tmpdir") + "mnist" + File.separator;
    private static final File modelPath = new File(basePath + "minst-model.zip");
    private static final String dataUrl = "http://github.com/myleott/mnist_png/raw/master/mnist_png.tar.gz";

    public static void main(String[] args) throws Exception {
        logger.info("basePath: {}", basePath);
        logger.info("modelPath: {}", modelPath.toString());

        int height = 28;
        int width = 28;

        int channels = 1;

        int outputClasses = 10;

        int batchSize = 54;

        int epochs = 1;

        int seed = 1234;
        Random ranNumGen = SecureRandom.getInstanceStrong();
        ranNumGen.setSeed(seed);

        final String path = basePath + "mnist_png" + File.separator;
        if(!new File(path).exists()) {
            logger.info("Download data {}", dataUrl);
            String localFilePath = basePath + "mnist_png.tar.gz";
            File file = new File(localFilePath);
            if(!file.exists()) {
                file.getParentFile().mkdirs();
                Utils.downloadAndSave(dataUrl, file);
                Utils.extractTarArchive(file, basePath);
            }
        } else {
            logger.info("Using the local data from folder {}", path);
        }

        logger.info("Vectoring the data from folder {}", path);
        File trainData = new File(path + "training");
        FileSplit trainSplit = new FileSplit(trainData, NativeImageLoader.ALLOWED_FORMATS, ranNumGen);
        ParentPathLabelGenerator labelMaker = new ParentPathLabelGenerator();
        ImageRecordReader trainRR = new ImageRecordReader(height, width, channels, labelMaker);
        trainRR.initialize(trainSplit);
        DataSetIterator train = new RecordReaderDataSetIterator(trainRR, batchSize, 1, outputClasses);

        // pixel values from 0-255 to 0-1 (min-max scaling)
        DataNormalization imageScaler = new ImagePreProcessingScaler();
        imageScaler.fit(train);
        train.setPreProcessor(imageScaler);

        // vectorization of test data
        File testData = new File(path + "testing");
        FileSplit testSplit = new FileSplit(testData, NativeImageLoader.ALLOWED_FORMATS, ranNumGen);
        ImageRecordReader testRR = new ImageRecordReader(height, width, channels, labelMaker);
        testRR.initialize(testSplit);
        DataSetIterator test = new RecordReaderDataSetIterator(testRR, batchSize, 1, outputClasses);
        // same normalization for better results
        test.setPreProcessor(imageScaler);

        logger.info("Network configuration and training...");
        // reduce the learning rate as the number of training epochs increases
        // iteration #, learning rate
        Map<Integer, Double> learningRateSchedule = new HashMap<>();
        learningRateSchedule.put(0, 0.06);
        learningRateSchedule.put(200, 0.05);
        learningRateSchedule.put(600, 0.028);
        learningRateSchedule.put(800, 0.0060);
        learningRateSchedule.put(1000, 0.001);

        final ConvolutionLayer layer1 = new ConvolutionLayer.Builder(5, 5).nIn(channels)
                .stride(1, 1)
                .nOut(20)
                .activation(Activation.IDENTITY)
                .build();
        final SubsamplingLayer layer2 = new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX).kernelSize(2, 2)
                .stride(2, 2)
                .build();
        // nIn need not specified in later layers
        final ConvolutionLayer layer3 = new ConvolutionLayer.Builder(5, 5).stride(1, 1)
                .nOut(50)
                .activation(Activation.IDENTITY)
                .build();
        final DenseLayer layer4 = new DenseLayer.Builder().activation(Activation.RELU)
                .nOut(500)
                .build();
        final OutputLayer layer5 = new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD).nOut(outputClasses)
                .activation(Activation.SOFTMAX)
                .build();
        final MultiLayerConfiguration config = new NeuralNetConfiguration.Builder().seed(seed)
                .l2(0.0005) // ridge regression value
                .updater(new Nesterovs(new MapSchedule(ScheduleType.ITERATION, learningRateSchedule)))
                .weightInit(WeightInit.XAVIER)
                .list()
                .layer(layer1)
                .layer(layer2)
                .layer(layer3)
                .layer(layer2)
                .layer(layer4)
                .layer(layer5)
                .setInputType(InputType.convolutionalFlat(height, width, channels))
                .build();

        final MultiLayerNetwork model = new MultiLayerNetwork(config);
        model.init();
        model.setListeners(new ScoreIterationListener(100));
        logger.info("Total num of params: {}", model.numParams());

        // evaluation while training (the score should go down)
        for (int i = 0; i < epochs; i++) {
            model.fit(train);
            logger.info("Completed epoch {}", i);
            train.reset();
            test.reset();
        }
        Evaluation eval = model.evaluate(test);
        logger.info(eval.stats());

        ModelSerializer.writeModel(model, modelPath, true);
        logger.info("The MINIST model has been saved in {}", modelPath.getPath());
    }
}
