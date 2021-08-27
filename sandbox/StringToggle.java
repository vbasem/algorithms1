package sandbox;

public class StringToggle {


    public static void main(String[] args) {
        String in = "abCdsdaASDASff123?15";
        char[] res = new char[in.length()];
        int i = 0;
        for (char c : in.toCharArray())  {
            if (c >= 'A' && c <= 'Z')  {
               res[i++] = (char) (c + 'a' - 'A');
            } else if (c >= 'a' && c<= 'z') {
                res[i++] = (char) (c + 'A' - 'a');
            } else {
                res[i++] = c;
            }
        }

        System.out.println(res);
        System.out.println(validate("abc_dada"));
        System.out.println(validate("abcA123123dada"));
    }

    public static boolean validate(String in) {
        for (char c : in.toCharArray())  {
            if (c >= 'A' && c <= 'Z')  {
                continue;
            }

            if (c >= 'a' && c <= 'z') {
                continue;
            }

            if (c >= '0' && c<= '9') {
                continue;
            }

            return false;
        }

        return true;
    }
}

