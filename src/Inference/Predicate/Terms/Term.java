package Inference.Predicate.Terms;

import Inference.Predicate.Unificator;

/**
 * @author erucolindo
 * <p>
 * Klasa reprezentuje ogólny term. Z niego dziedziczą rzeczywiste termy.
 * Zawiera pole "term name", które w zależności od realizacji określa nazwę danego termu
 * Oraz pole function_arg - używane tylko przez term typu {@link Function} (inne nie udostępniają konstruktorów do dodawania go)
 */
public abstract class Term {

    protected String term_name;

    protected Term() {
    }

    protected Term(Term other) {
        term_name = other.term_name;
    }

    protected Term(String name) {
        this.term_name = name;
    }


    public void setName(String new_name) {
        term_name = new_name;
    }

    public String getName() {
        return term_name;
    }

    //  public abstract boolean isRealizationOf(Term t);
//  public abstract boolean isInstanceOf(Term t);
    public abstract boolean isConstant();

    public abstract boolean isVariable();

    public abstract boolean isFunction();

    public boolean couldBeMarged(Term other) {
        throw new Error("moze usunac");
    }

    public abstract Term merge(Term other);//byc moze wystarczy przypisywac wartosc this a nie zwracac

    public String toString() {
        return term_name;
    }

    @Override
    public abstract Object clone();

    @Override
    public abstract boolean equals(Object other);

    public static Term getTermFromString(String string) {
        string = string.trim();
        if (string.contains("(")) {
            String args = string.substring(string.indexOf("(") + 1, string.lastIndexOf(")"));
            String funcName = string.substring(0, string.indexOf("("));
            return new Function(funcName, args);
        } else if (string.equals(string.toLowerCase())) {
            return new Variable(string);
        } else {
            return new Constant(string);
        }
    }
/**
 * return null if conflict
 * */
    public Term returnNarrowerTerm(final Term other){
        if (this.isConstant()){
            if(other.isConstant()) {
                return null;
            }
            if(other.isVariable()){
                return this;
            }
            if(other.isFunction()){
                // TODO: 01.01.2018
            }
        }else if(this.isVariable()){
            return other;
        }else if(this.isFunction()) {
            if (other.isVariable()) {
                return this;
            }
            if (other.isConstant()) {
                // TODO: 01.01.2018
            }
            if (other.isFunction()) {
                // TODO: 01.01.2018
            }
        }
        return null;
    }

}

