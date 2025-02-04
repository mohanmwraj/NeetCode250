package mathAndGeometry;

public class multiplyStirngs {
    /*
        * Approach: Multiplication & Addition
        *
     */
    public class Solution {
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) return "0";

            if (num1.length() < num2.length()) {
                return multiply(num2, num1);
            }

            String res = "";
            int zero = 0;
            for (int i = num2.length() - 1; i >= 0; i--) {
                String cur = mul(num1, num2.charAt(i), zero);
                res = add(res, cur);
                zero++;
            }

            return res;
        }

        private String mul(String s, char d, int zero) {
            int i = s.length() - 1, carry = 0;
            int digit = d - '0';
            StringBuilder cur = new StringBuilder();

            while (i >= 0 || carry > 0) {
                int n = (i >= 0) ? s.charAt(i) - '0' : 0;
                int prod = n * digit + carry;
                cur.append(prod % 10);
                carry = prod / 10;
                i--;
            }

            return cur.reverse().toString() + "0".repeat(zero);
        }

        private String add(String num1, String num2) {
            int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
            StringBuilder res = new StringBuilder();

            while (i >= 0 || j >= 0 || carry > 0) {
                int n1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
                int n2 = (j >= 0) ? num2.charAt(j) - '0' : 0;
                int total = n1 + n2 + carry;
                res.append(total % 10);
                carry = total / 10;
                i--;
                j--;
            }

            return res.reverse().toString();
        }
    }
    /*
        Time Complexity: O(min(m,n) * (m + n))
        Space Complexity: O(m + n)
     */

    /*
        * Approach: Multiplication
        *
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int[] res = new int[num1.length() + num2.length()];
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        for (int i1 = 0; i1 < num1.length(); i1++) {
            for (int i2 = 0; i2 < num2.length(); i2++) {
                int digit = (num1.charAt(i1) - '0') * (num2.charAt(i2) - '0');
                res[i1 + i2] += digit;
                res[i1 + i2 + 1] += res[i1 + i2] / 10;
                res[i1 + i2] %= 10;
            }
        }

        StringBuilder result = new StringBuilder();
        int i = res.length - 1;
        while (i >= 0 && res[i] == 0) {
            i--;
        }
        while (i >= 0) {
            result.append(res[i--]);
        }
        return result.toString();
    }
    /*
        Time Complexity: O(m * n)
        Space Complexity: O(m + n)
     */
}
