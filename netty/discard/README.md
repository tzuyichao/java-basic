## echo server

    echo -ne "test\n" | nc localhost 8080

### discard0

    java -jar -Dssl=true .\build\libs\discard-1.0-SNAPSHOT.jar

### 雜記   
#### AbstractBootstrap裡concurrent collection使用的例子

Netty 4.1原始碼中使用Concurrent的Collection的例子，以io.netty.bootstrap.AbstractBootstrap class來看

    private final Map<ChannelOption<?>, Object> options = new ConcurrentHashMap<ChannelOption<?>, Object>();

使用案例

        /**
         * Allow to specify a {@link ChannelOption} which is used for the {@link Channel} instances once they got
         * created. Use a value of {@code null} to remove a previous set {@link ChannelOption}.
         */
        public <T> B option(ChannelOption<T> option, T value) {
            ObjectUtil.checkNotNull(option, "option");
            if (value == null) {
                options.remove(option);
            } else {
                options.put(option, value);
            }
            return self();
        }
 
跟過去版本使用LinkedHashMap搭配synchronized的寫法可以搭配食用。   