import java.io.*;

public class IFile {

    private String text;
    private String url;

    public IFile(String url) {
        this.url = url;
        this.text = readFile(this.url);
    }

    private String readFile(String url) {
        String output = "";
        try {
            File file = new java.io.File(url);
            if (file.exists()) {
                BufferedReader buffer = new BufferedReader(new FileReader(file));
                String line;
                while ((line = buffer.readLine()) != null) {
                    output = output + line;
                }
            } else {
                System.out.println("File not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public int getLength() {
        return text.trim().length();
    }

    public String getText() {
        return this.text;
    }

    public int getCharacterSize() {
        int count = 0;
        for (int i = 0; i < text.trim().length(); i++) {
            if (text.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }

    public String[] getWordList() {
        return text.split(" ");
    }

    public int getWordSize() {
        if (getLength() == 0){
            return 0;
        }else {
            return getWordList().length;
        }
    }

    public boolean searchWord(String text) {
        String[] searchList = text.split(" ");
        String[] wordList = getWordList();
        for (String w1 : searchList) {
            for (String w2 : wordList) {
                if (w1.equalsIgnoreCase(w2))
                    return true;
            }
        }
        return false;
    }

    public void clearFile() {
        this.text = "";
        writeFile("");
    }

    public void addToFile(String text) {
        this.text += text;
        writeFile(this.text);
    }

    public void addFile(String url){
        String fileText = readFile(url);
        addToFile(fileText);
    }

    public void replace(String word, String newWord) {
        String[] wordList = getWordList();
        String output = "";

        for (String w : wordList) {
            if (w.equalsIgnoreCase(word)) {
                output += newWord;
            } else {
                output += w;
            }
            output += " ";
        }

        this.text = output;
        writeFile(text);
    }

    private int writeFile(String text) {
        try {
            File file = new File(url);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            buffer.write(text);
            buffer.close();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int repeatTheWord(String word){
        int count = 0;
        String[] searchList = word.split(" ");
        String[] wordList = getWordList();
        for (String w1 : searchList) {
            for (String w2 : wordList) {
                if (w1.equalsIgnoreCase(w2))
                    count += 1;
            }
        }
        return count;
    }
}
