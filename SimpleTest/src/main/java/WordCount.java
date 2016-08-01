import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCount {
	static String filePath;
	static ArrayList<Word> words;
	static ArrayList<String> wordFromObjs;
	static WordComparator c;

	public WordCount(String filePath) {
		this.filePath = filePath;
		words = new ArrayList<Word>();
		wordFromObjs = new ArrayList<String>();
		c = new WordComparator();
	}

	public static void readFile() {
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

	public static void printWordCounts() {
		Collections.sort(words, c);
		for(Word word : words) {
			System.out.println(word.getWord() + " : " + word.getCount());
		}
	}

	public static void main(String[] args) {
		if(args.length <= 0) {
			System.out.println("ERROR YOU NEED TO PROVIDE ARGUMENTS");
			System.exit(0);
		} else{
			WordCount foo = new WordCount(args[0]);
			foo.readFile();
			printWordCounts();
		}
	}

	private class WordComparator implements Comparator<Word> {

		@Override
	    public int compare(Word word1, Word word2) {
	        return word2.getCount() - word1.getCount();
	    }
	}
}