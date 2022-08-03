package fr.m2i.models;

import java.text.ParseException;
import java.util.HashMap;

public interface TacheDAO {
	void add(Tache tache) ;
	HashMap<Integer,Tache> display() throws ParseException;
	void delete(Integer id);
	void modify(Integer id, Tache tache);

}
