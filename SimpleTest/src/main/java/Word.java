public class Word {
	private int count;
	private String word;

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