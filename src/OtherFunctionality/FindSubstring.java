package OtherFunctionality;

public class FindSubstring { //find if there is concrete substring in string

    static public boolean findExact(String str1, String str2) {
        String longestSubstring = "";
        String substring;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        //switch strings if...
        if (str1.length() > str2.length()) {
            String tmp = str1;
            str1 = str2;
            str2 = tmp;
            tmp = null;
        }

        //counter
        int longest = 0;

        for (int i=0; i < str1.length(); i++) {
            for (int j=i+1; j <= str1.length(); j++) {
                substring = str1.substring(i, j);
                if (str2.contains(substring) && substring.length() > longest) {
                    longest = substring.length();
                    longestSubstring = substring;
                }
            }
        }

        if (str1.equals(longestSubstring) && str2.contains(longestSubstring)) {
            return true;
        }

        return false;
    }

}