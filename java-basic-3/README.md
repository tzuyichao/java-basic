# Annotation Processor Lab

針對`RetentionPolicy.SOURCE`想要產生java source code的方式測試這種annotation processor的hello world project。

分為三個module

* `annotation` 就很像我們引用mapstruct的library寫在implementation那邊的專案。
* `annotation-processor` 顧名思義就是針對annotation進行處理的程式，這裡用很一般的方式產生java source code。`JavaPoet`
* `client` 就是我們平常開發的應用程式使用mapstruct的專案
