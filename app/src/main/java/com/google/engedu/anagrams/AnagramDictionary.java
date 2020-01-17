/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<String>();
    private HashSet <String> wordSet = new HashSet<String>();
    private HashMap <String, ArrayList<String>> lettersToWord = new HashMap<>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            String sortedWord = sortLetters(word);
            if (lettersToWord.containsKey(sortedWord)==true){
                ArrayList<String> newArray = lettersToWord.get(sortedWord);
                newArray.add(word);
                lettersToWord.put(sortedWord, newArray);
            }
            else{
                ArrayList<String> newArray = new ArrayList<String>();
                newArray.add(word);
                lettersToWord.put(sortedWord, newArray);
            }

        }
    }


    public boolean isGoodWord(String word, String base) {
        if (wordSet.contains(word)){
            if (!word.contains(base)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        int counter = 0;
        while (counter!= wordList.size()){
           String oneWord = wordList.get(counter);
            if (sortLetters(oneWord).equals(sortLetters(targetWord)) && oneWord.length() == targetWord.length()){
                result.add(oneWord);
            }
            counter++;
        }
        return result;
    }

    /*
    Finally, implement getAnagramsWithOneMoreLetter which takes a string and finds all
    anagrams that can be formed by adding one letter to that word.

    Be sure to instantiate a new ArrayList as your return value then
    check the given word + each letter of the alphabet one by one against the entries in lettersToWord.

    Also update defaultAction in AnagramActivity to invoke getAnagramsWithOneMoreLetter instead of getAnagrams.
*/
    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i = 0; i <= alphabet.length; i++){
            String sortedWord = sortLetters(word+ String.valueOf(alphabet[i]));
            if (lettersToWord.containsKey(sortedWord)==true){
                result.addAll(lettersToWord.get(sortedWord));
            }
        }
        return result;
    }

    public String sortLetters (String inputStr) {
        char [] stringChars = inputStr.toCharArray();
        Arrays.sort(stringChars);
        String sortedStr = new String(stringChars);
        return sortedStr;
    }
/*
If your game is working, proceed to implement pickGoodStarterWord to make the game more interesting.
Pick a random starting point in the wordList array and check each word in the array until you find
one that has at least MIN_NUM_ANAGRAMS anagrams. Be sure to handle wrapping around to the start of the array if needed.


 */

    public String pickGoodStarterWord() {
        wordList.get(random.nextInt(wordList.size()-1));
        
        return "stop";
    }
}
