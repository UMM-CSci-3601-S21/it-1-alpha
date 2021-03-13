package umm3601.contextpacks;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Id;

import com.google.common.collect.Lists;

import org.mongojack.ObjectId;

import io.javalin.http.NotFoundResponse;
import umm3601.wordlists.WordList;

public class ContextPack {
  @ObjectId
  @Id
  String id;
  String name;
  String icon;
  boolean enabled;
  WordList[] wordlists;

  public void setName(String name) {
    this.name = name;
  }

  public void addWordList(WordList wordList) {
    List<WordList> temp = Arrays.asList(wordlists);
    temp.add(wordList);
    wordlists = (WordList[]) temp.toArray();
  }

  public void deleteWordList(WordList wordList) {
    List<WordList> temp = Arrays.asList(wordlists);
    if (temp.contains(wordList))
      temp.remove(temp.indexOf(wordList));
    wordlists = (WordList[]) temp.toArray();
  }

  public WordList getWordListByName(String name) {
    List<WordList> temp = Arrays.asList(wordlists);
    WordList[] result = new WordList[] { null };
    temp.stream().filter(list -> list.name.equals(name)).findFirst().ifPresent(list -> {
      result[0] = list;
    });
    if(result[0] == null) throw new NotFoundResponse("The given wordlist was not found.");
    else return result[0];
  }
  public void editWordList(String name,WordList wordList) {
    List<WordList> temp = Arrays.asList(wordlists);
    WordList original = getWordListByName(name);
    if (temp.contains(original))
      temp.set(temp.indexOf(original),wordList);
    wordlists = (WordList[]) temp.toArray();
  }
  public WordList[] getWordLists() {
    return wordlists;
  }
}