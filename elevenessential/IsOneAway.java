package elevenessential;

public class IsOneAway {

    public static void main(String[] args) {
        IsOneAway solver = new IsOneAway();
        // String sla = "abcde";
        // String slb = "abfde";

        // String sla = "xyz";
        // String slb = "xyaz";

        // String sla = "abcde";
        // String slb = "abde";

        // String sla = "";
        // String slb = "";


        String sla = "abcdef";
        String slb = "abcde";


        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("abcde", "abcd"));  // should return true);
        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("abde", "abcde"));  // should return true);
        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("a", "a"));  // should return true);
        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("abcdef", "abqdef"));  // should return true);
        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("abcdef", "abccef"));  // should return true);
        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("abcdef", "abcde"));  // should return true);
        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("aaa", "abc"));  // should return false);
        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("abcde", "abc"));  // should return false);
        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("abc", "abcde"));  // should return false);
        System.out.println("solver.isOneAway(sla, slb) = " +         solver.isOneAway("abc", "bcc"));  // should return false);

    }

    public boolean isOneAway(String sla, String slb) {
        int corrections = 0;
        if (sla.length() == slb.length()) {
            for (int i = 0; i < sla.length(); i++) {
                if (sla.charAt(i) != slb.charAt(i)) {
                    if (corrections++ == 0) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else {
            String longer = sla.length() > slb.length() ? sla : slb;
            String shorter = sla.length() > slb.length() ? slb : sla;
            if (longer.length() - shorter.length() > 1) {
                return false;
            }

            for (int i = 0; i < longer.length(); i++) {
                if (i - corrections == shorter.length())  {
                    return true;
                }
                if (longer.charAt(i) != shorter.charAt(i - corrections)) {
                    if (corrections++ == 0) {
                        continue;
                    } else {

                        return false;
                    }
                }
            }
        }

        return true;

    }

}
