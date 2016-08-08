package afl;

public class Word implements java.io.Serializable{
	public int count;
	public String word;

	public Word(String name){
		this.word = name;
		this.count = 1;
	}

	public int getCount() {
		return count;
	}

	public String getWord() {
		return word;
	}

	public void addWord() {
		count += 1;
	}
}
