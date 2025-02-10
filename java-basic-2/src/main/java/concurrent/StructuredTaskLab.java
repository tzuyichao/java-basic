package concurrent;

import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

public class StructuredTaskLab {
    public static void main(String[] args) {
        StructuredTaskLab lab = new StructuredTaskLab();
        Result result = lab.executeTask();
        System.out.println(result.getCurrent());
        System.out.println(result.getForecast());
        System.out.println(result.getHistorical());
    }

    public Result executeTask() {
        try(var scope = new StructuredTaskScope<String>()) {
            Supplier<String> currentSubTask = scope.fork(this::getCurrent);
            Supplier<String> forecastSubTask = scope.fork(this::getForecast);
            Supplier<String> historicalSubTask = scope.fork(this::getHistorical);
            scope.join();
            return new Result(currentSubTask.get(), forecastSubTask.get(), historicalSubTask.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrent() {
        return "Current";
    }

    public String getForecast() {
        return "Forecast";
    }

    public String getHistorical() {
        return "Historical";
    }

    public static class Result {
        private final String current;
        private final String forecast;
        private final String historical;

        public Result(String current, String forecast, String historical) {
            this.current = current;
            this.forecast = forecast;
            this.historical = historical;
        }

        public String getCurrent() {
            return current;
        }

        public String getForecast() {
            return forecast;
        }

        public String getHistorical() {
            return historical;
        }
    }

}
