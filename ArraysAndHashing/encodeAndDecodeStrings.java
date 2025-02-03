package ArraysAndHashing;

import java.util.ArrayList;
import java.util.List;

/*
    Design an algorithm to encode a list of strings to a single string.
    The encoded string is then decoded back to the original list of strings.

    Input: ["neet","code","love","you"]
    Output:["neet","code","love","you"]
 */
public class encodeAndDecodeStrings {
    /*
        * Approach: Encoding and Decoding
        *
        * No state variable allowed.
        * We need to know, how many characters will contribute to first string while decoding.
        * So we append, length of the first string to result, delimited by , and continue to append only length.
        * then append strings delimited by #.
        *
        * While decoding
        *   - create a string of size until character encountered a comma.
        *   - using the space append the characters after the #.
        *
     */
    public String encode_1(List<String> strs){
        if(strs.isEmpty()) return "";

        StringBuilder res = new StringBuilder();
        List<Integer> sizes = new ArrayList<>();
        for (String str: strs){
            sizes.add(str.length());
        }
        for(int size: sizes){
            res.append(size).append(',');
        }
        res.append('#');
        for(String str: strs){
            res.append(str);
        }

        return res.toString();
    }

    public List<String> decode_1(String str){
        if(str.isEmpty()) return new ArrayList<>();

        List<String> res = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        int i = 0;

        while(str.charAt(i) != '#'){
            StringBuilder curr = new StringBuilder();
            while(str.charAt(i) != ','){
                curr.append(str.charAt(i));
                i++;
            }
            sizes.add(Integer.parseInt(curr.toString()));
        }
        i++;
        for(int size: sizes){
            res.add(str.substring(i, i + size));
            i += size;
        }

        return res;
    }
    /*
        Time Complexity: O(m) for encode() and decode()
        Space Complexity: O(n) for encode() and decode()

        where m is the sum of lengths of all the strings and n is the number of strings.
     */

    /*
        * Approach: Encoding & Decoding Optimal
        *
     */
    public String encode_2(List<String> strs){
        StringBuilder res = new StringBuilder();

        for (String str: strs){
            res.append(str.length()).append('#').append(str);
        }

        return res.toString();
    }

    public List<String> decode_2(String str){
        List<String> result = new ArrayList<>();
        int i = 0;

        while(i < str.length()){
            int j = i;
            while(str.charAt(j) != '#'){
                j++;
            }

            int length = Integer.parseInt(str.substring(i, j));
            i = j + 1;
            j = i + length;
            result.add(str.substring(i, j));
            i = j;
        }

        return result;
    }
    /*
        Time Complexity: O(m) for encode() and decode()
        Space Complexity: O(1) for encode() and decode()

        where m is the sum of lengths of all the strings and n is the number of strings.
     */
}
