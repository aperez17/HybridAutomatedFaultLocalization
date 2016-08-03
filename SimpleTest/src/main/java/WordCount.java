package afl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCount {
	String filePath;
	ArrayList<Word> words;
	ArrayList<String> wordFromObjs;
	WordComparator c;

	public WordCount(String filePath) {
		this.filePath = filePath;
		words = new ArrayList<Word>();
		wordFromObjs = new ArrayList<String>();
		c = new WordComparator();
	}

	public void readFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
		{
			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				String[] wordsFromLine = currentLine.split(" ");
				for (int i=0; i<wordsFromLine.length; i++) {
					wordsFromLine[i] = wordsFromLine[i].trim().toLowerCase();
					if(!wordFromObjs.contains(wordsFromLine[i])) {
						words.add(new Word(wordsFromLine[i]));
						wordFromObjs.add(wordsFromLine[i]);
					} else {
						int index = wordFromObjs.indexOf(wordsFromLine[i].trim());
						words.get(index).addWord();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printWordCounts() {
		System.out.println(this.toString());
	}

	public ArrayList<Word> getWords() {
		return words;
	}

	public String toString() {
		String str = "";
		Collections.sort(words, c);
		for(Word word : words) {
			str += (word.getWord() + " : " + word.getCount() + "\n");
		}
		return str;
	}

	private class WordComparator implements Comparator<Word> {

		@Override
	    public int compare(Word word1, Word word2) {
	        return word2.getCount() - word1.getCount();
	    }
	}
}
