package brute;

class VersionControl {
    private int badVersion;

    VersionControl(int badVersion) {
        this.badVersion = badVersion;
    }

    boolean isBadVersion(int version) {
        return version>=badVersion;
    }
}

/**
 * 278. First Bad Version
 * https://leetcode.com/problems/first-bad-version/
 *
 * Runtime: 16 ms, faster than 65.62% of Java online submissions for First Bad Version.
 * Memory Usage: 41.1 MB, less than 35.99% of Java online submissions for First Bad Version.
 */
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
public class FirstBadVersion extends VersionControl {
    // for test case
    public FirstBadVersion(int badVersion) {
        super(badVersion);
    }

    public int firstBadVersion(int n) {
        var i = 1;
        var j = n;
        while(i <= j) {
            int mid = i + (j-i>>1);
            if(isBadVersion(mid)) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
}
