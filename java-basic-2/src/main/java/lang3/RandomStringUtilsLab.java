package lang3;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringUtilsLab {
    public static void main(String[] args) {
        System.out.println("======= RandomStringUtils.secure().random() =======");
        System.out.println(RandomStringUtils.secure().next(8, true, true));
        System.out.println(RandomStringUtils.secure().next(8, true, true));

        System.out.println("======= RandomStringUtils.secureStrong().random() =======");
        System.out.println(RandomStringUtils.secureStrong().next(8, true, true));
        System.out.println(RandomStringUtils.secureStrong().next(8, true, true));
    }
}
