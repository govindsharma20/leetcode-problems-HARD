class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.isEmpty() || words == null || words.length == 0 || words[0].isEmpty()) {
            return result;
        }

        int wordLength = words[0].length();
        int numWords = words.length;
        int totalLength = wordLength * numWords;

        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        for (int startOffset = 0; startOffset < wordLength; startOffset++) {
            Map<String, Integer> currentWindowWordCounts = new HashMap<>();
            int wordsFoundInWindow = 0;
            int left = startOffset;

            for (int right = startOffset; right + wordLength <= s.length(); right += wordLength) {
                String currentWord = s.substring(right, right + wordLength);

                if (!wordCounts.containsKey(currentWord)) {
                    currentWindowWordCounts.clear();
                    wordsFoundInWindow = 0;
                    left = right + wordLength;
                } else {
                    currentWindowWordCounts.put(currentWord, currentWindowWordCounts.getOrDefault(currentWord, 0) + 1);
                    wordsFoundInWindow++;

                    while (currentWindowWordCounts.get(currentWord) > wordCounts.get(currentWord)) {
                        String wordToRemove = s.substring(left, left + wordLength);
                        currentWindowWordCounts.put(wordToRemove, currentWindowWordCounts.get(wordToRemove) - 1);
                        wordsFoundInWindow--;
                        left += wordLength;
                    }

                    if (wordsFoundInWindow == numWords) {
                        result.add(left);

                        String wordToRemove = s.substring(left, left + wordLength);
                        currentWindowWordCounts.put(wordToRemove, currentWindowWordCounts.get(wordToRemove) - 1);
                        wordsFoundInWindow--;
                        left += wordLength;
                    }
                }
            }
        }
        return result;
    }
}
