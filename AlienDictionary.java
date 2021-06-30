class Solution {
    public String alienOrder(String[] words) {
        HashMap<Character, HashSet<Character>> hm = new HashMap<>();
        int[] degree = new int[26];
        for(String word:words) {
            int i=0;
            while(i<word.length()) {
                char ch = word.charAt(i++);
                hm.put(ch, new HashSet<Character>());
            }
        }
        
        for(int i=1;i<words.length;i++) {
            String word1 = words[i-1];
            String word2 = words[i];
            int min1 = Math.min(word1.length(), word2.length());
            int j=0;
            for(j=0;j<min1;j++) {
                char ch1 = word1.charAt(j);
                char ch2 = word2.charAt(j);
                if(ch1 != ch2) {
                    if(!hm.get(ch1).contains(ch2)) {
                        hm.get(ch1).add(ch2);
                        degree[ch2 - 'a']++;
                    }
                    break;
                }
            }
            if(j == min1 && word1.length() > word2.length()) {
                return "";
            }
        }
        
        Queue<Character> q = new LinkedList<>();
        
        for(char chr:hm.keySet()) {
            if(degree[chr-'a'] == 0) {
                q.offer(chr);
            }
        }
        
        StringBuffer sb = new StringBuffer();
        
        while(!q.isEmpty()) {
          char ch = q.poll();
          sb.append(ch);  
          for(char chr:hm.get(ch)) {
              degree[chr - 'a']--;
              if(degree[chr - 'a'] == 0) {
                  q.offer(chr);
              }
          }  
        }
        
        String r = sb.toString();
        return r.length() == hm.size() ? r : "";
    }
}
