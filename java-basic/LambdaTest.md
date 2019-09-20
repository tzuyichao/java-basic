## Decompile LambdaTest.class

    Classfile /D:/lab/java-basic/java-basic/build/classes/java/main/lambda/LambdaTest.class
      Last modified 2019年9月20日; size 1286 bytes
      MD5 checksum 79a5e8cccf6dc6eb923ef986fcfe3d15
      Compiled from "LambdaTest.java"
    public class lambda.LambdaTest
      minor version: 0
      major version: 56
      flags: (0x0021) ACC_PUBLIC, ACC_SUPER
      this_class: #7                          // lambda/LambdaTest
      super_class: #8                         // java/lang/Object
      interfaces: 0, fields: 0, methods: 3, attributes: 3
    Constant pool:
       #1 = Methodref          #8.#25         // java/lang/Object."<init>":()V
       #2 = InvokeDynamic      #0:#30         // #0:run:([Ljava/lang/String;)Ljava/lang/Runnable;
       #3 = InterfaceMethodref #31.#32        // java/lang/Runnable.run:()V
       #4 = Fieldref           #33.#34        // java/lang/System.out:Ljava/io/PrintStream;
       #5 = Methodref          #35.#36        // java/util/Arrays.toString:([Ljava/lang/Object;)Ljava/lang/String;
       #6 = Methodref          #37.#38        // java/io/PrintStream.println:(Ljava/lang/String;)V
       #7 = Class              #39            // lambda/LambdaTest
       #8 = Class              #40            // java/lang/Object
       #9 = Utf8               <init>
      #10 = Utf8               ()V
      #11 = Utf8               Code
      #12 = Utf8               LineNumberTable
      #13 = Utf8               LocalVariableTable
      #14 = Utf8               this
      #15 = Utf8               Llambda/LambdaTest;
      #16 = Utf8               main
      #17 = Utf8               ([Ljava/lang/String;)V
      #18 = Utf8               args
      #19 = Utf8               [Ljava/lang/String;
      #20 = Utf8               r
      #21 = Utf8               Ljava/lang/Runnable;
      #22 = Utf8               lambda$main$0
      #23 = Utf8               SourceFile
      #24 = Utf8               LambdaTest.java
      #25 = NameAndType        #9:#10         // "<init>":()V
      #26 = Utf8               BootstrapMethods
      #27 = MethodHandle       6:#41          // REF_invokeStatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
      #28 = MethodType         #10            //  ()V
      #29 = MethodHandle       6:#42          // REF_invokeStatic lambda/LambdaTest.lambda$main$0:([Ljava/lang/String;)V
      #30 = NameAndType        #43:#44        // run:([Ljava/lang/String;)Ljava/lang/Runnable;
      #31 = Class              #45            // java/lang/Runnable
      #32 = NameAndType        #43:#10        // run:()V
      #33 = Class              #46            // java/lang/System
      #34 = NameAndType        #47:#48        // out:Ljava/io/PrintStream;
      #35 = Class              #49            // java/util/Arrays
      #36 = NameAndType        #50:#51        // toString:([Ljava/lang/Object;)Ljava/lang/String;
      #37 = Class              #52            // java/io/PrintStream
      #38 = NameAndType        #53:#54        // println:(Ljava/lang/String;)V
      #39 = Utf8               lambda/LambdaTest
      #40 = Utf8               java/lang/Object
      #41 = Methodref          #55.#56        // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
      #42 = Methodref          #7.#57         // lambda/LambdaTest.lambda$main$0:([Ljava/lang/String;)V
      #43 = Utf8               run
      #44 = Utf8               ([Ljava/lang/String;)Ljava/lang/Runnable;
      #45 = Utf8               java/lang/Runnable
      #46 = Utf8               java/lang/System
      #47 = Utf8               out
      #48 = Utf8               Ljava/io/PrintStream;
      #49 = Utf8               java/util/Arrays
      #50 = Utf8               toString
      #51 = Utf8               ([Ljava/lang/Object;)Ljava/lang/String;
      #52 = Utf8               java/io/PrintStream
      #53 = Utf8               println
      #54 = Utf8               (Ljava/lang/String;)V
      #55 = Class              #58            // java/lang/invoke/LambdaMetafactory
      #56 = NameAndType        #59:#63        // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
      #57 = NameAndType        #22:#17        // lambda$main$0:([Ljava/lang/String;)V
      #58 = Utf8               java/lang/invoke/LambdaMetafactory
      #59 = Utf8               metafactory
      #60 = Class              #65            // java/lang/invoke/MethodHandles$Lookup
      #61 = Utf8               Lookup
      #62 = Utf8               InnerClasses
      #63 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
      #64 = Class              #66            // java/lang/invoke/MethodHandles
      #65 = Utf8               java/lang/invoke/MethodHandles$Lookup
      #66 = Utf8               java/lang/invoke/MethodHandles
    {
      public lambda.LambdaTest();
        descriptor: ()V
        flags: (0x0001) ACC_PUBLIC
        Code:
          stack=1, locals=1, args_size=1
             0: aload_0
             1: invokespecial #1                  // Method java/lang/Object."<init>":()V
             4: return
          LineNumberTable:
            line 5: 0
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0       5     0  this   Llambda/LambdaTest;
    
      public static void main(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: (0x0009) ACC_PUBLIC, ACC_STATIC
        Code:
          stack=1, locals=2, args_size=1
             0: aload_0
             1: invokedynamic #2,  0              // InvokeDynamic #0:run:([Ljava/lang/String;)Ljava/lang/Runnable;
             6: astore_1
             7: aload_1
             8: invokeinterface #3,  1            // InterfaceMethod java/lang/Runnable.run:()V
            13: return
          LineNumberTable:
            line 7: 0
            line 8: 7
            line 9: 13
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      14     0  args   [Ljava/lang/String;
                7       7     1     r   Ljava/lang/Runnable;
    
      private static void lambda$main$0(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: (0x100a) ACC_PRIVATE, ACC_STATIC, ACC_SYNTHETIC
        Code:
          stack=2, locals=1, args_size=1
             0: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
             3: aload_0
             4: invokestatic  #5                  // Method java/util/Arrays.toString:([Ljava/lang/Object;)Ljava/lang/String;
             7: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            10: return
          LineNumberTable:
            line 7: 0
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      11     0  args   [Ljava/lang/String;
    }
    SourceFile: "LambdaTest.java"
    InnerClasses:
      public static final #61= #60 of #64;    // Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
    BootstrapMethods:
      0: #27 REF_invokeStatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
        Method arguments:
          #28 ()V
          #29 REF_invokeStatic lambda/LambdaTest.lambda$main$0:([Ljava/lang/String;)V
          #28 ()V