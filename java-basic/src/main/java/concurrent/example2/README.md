# Example 2: File Search

## Result on My Computer

- Intel(R) Core(TM) i7-10700 CPU @ 2.90GHz   2.90 GHz
- Windows 10 Professional
- 32G RAM

<pre>
Benchmark                                   Mode  Cnt  Score   Error  Units
MyBenchmark.testParallelFileSearchNVM       avgt    5  0.728 �� 0.190   s/op
MyBenchmark.testParallelFileSearchNotExist  avgt    5  1.245 �� 0.074   s/op
MyBenchmark.testSerialFileSearchNVM         avgt    5  1.736 �� 0.044   s/op
MyBenchmark.testSerialFileSearchNotExist    avgt    5  3.134 �� 0.243   s/op
</pre>

<pre>
Benchmark
MyBenchmark.testParallelFileSearchHosts     avgt    5   6.510 �� 4.678   s/op
MyBenchmark.testParallelFileSearchNotExist  avgt    5  25.706 �� 1.459   s/op
MyBenchmark.testSerialFileSearchHosts       avgt    5  10.675 �� 0.416   s/op
MyBenchmark.testSerialFileSearchNotExist    avgt    5  45.891 �� 2.095   s/op
</pre>