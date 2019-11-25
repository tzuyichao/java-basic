package util;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapLab {
    public static void main(String[] args) {
        Map<LicenseType, String> licenseMap = new IdentityHashMap<>();
    }

    class LicenseType {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
