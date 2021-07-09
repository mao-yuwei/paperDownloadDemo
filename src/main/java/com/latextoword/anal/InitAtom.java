package com.latextoword.anal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.latextoword.atom.Atom;
import com.latextoword.atom.AtomBE;
import com.latextoword.dictionary.AtomChar;
import com.latextoword.dictionary.dicList.AtomCharList;
import com.latextoword.atom.AtomRegex;
//以AtomChar里面的字符结构为块级，一层层块级化
public class InitAtom {
private static Hashtable<String, AtomChar> atomCharMap;
	
	public static void init() {
		atomCharMap=AtomCharList.getAtomCharDicList();
	}
	
	public static  List<Atom> latexIntoAtomAll(String latex) {
		init();
		List<Atom> firLevel=latexIntoAtom(latexStrLeftBeginToBlock(latex));
		//List<Atom> firLevel=latexIntoAtom(latex);
		latexCellIntoAtom(firLevel);
		return firLevel;
	}
	public static String latexStrLeftBeginToBlock(String latex){
		String leftRegex="\\\\left[ ]*(\\\\([a-zA-Z]+|[\\W\\S])|[\\W\\S])";
		String rightRegex="\\\\right[ ]*(\\\\([a-zA-Z]+|[\\W\\S])|[\\W\\S])";
		String beginRegex="\\\\begin[ ]*\\{[a-zA-Z]+\\}";
		String endRegex="\\\\end[ ]*\\{[a-zA-Z]+\\}";

		/*Pattern leftPattern=Pattern.compile(leftRegex);
		Matcher leftMatcher=leftPattern.matcher(latex);
		while (leftMatcher.find()){
			String leftMatcherStr=leftMatcher.group();
			latex=latex.replace(leftMatcherStr,"{"+leftMatcherStr);
		}

		Pattern rightPattern=Pattern.compile(rightRegex);
		Matcher rightMatcher=rightPattern.matcher(latex);
		while (rightMatcher.find()){
			String rightMatcherStr=rightMatcher.group();
			latex=latex.replace(rightMatcherStr,rightMatcherStr+"}");
		}*/

		Pattern beginPattern=Pattern.compile(beginRegex);
		Matcher beginMatcher=beginPattern.matcher(latex);
		while (beginMatcher.find()){
			String beginMatcherStr=beginMatcher.group();
			latex=latex.replace(beginMatcherStr,"{"+beginMatcherStr);
		}

		Pattern endPattern=Pattern.compile(endRegex);
		Matcher endMatcher=endPattern.matcher(latex);
		while (endMatcher.find()){
			String endMatcherStr=endMatcher.group();
			latex=latex.replace(endMatcherStr,endMatcherStr+"}");
		}
		System.out.println(latex);
		return latex;
	}

	public static List<Atom> latexIntoAtom(String latex) {
		List<Atom> atoms=new ArrayList<Atom>();
		String noAnalStr = "";
		Integer notTogetherCount=0;//不能合并在一起的atom数量
		for(int i=0;i<latex.length();) {
			Atom atom=new Atom();
			String tmpChar=latex.substring(i, i+1);
			String tmpLatex=latex.substring(i);
			Integer step=charToAtom(tmpChar,tmpLatex,atom);
			notTogetherCount--;
			if(atom.getAnalType()!=null&&atom.getAnalType().equals(1)) notTogetherCount=3;
			if(notTogetherCount<1&&atom.getAnalType()!=null&&atom.getAnalType().equals(0)&&atom.getType()!=null&&atom.getType().equals(0)&&atom.getAtomBEs()==null&&atom.getAtomName()!=null){
				noAnalStr = noAnalStr + atom.getAtomName();
			}else {
				if(!noAnalStr.equals("")) {
					String noAnalNoSpaceStr=noAnalStr.replaceAll(" +","\u200B");
					Atom atomNew = new Atom();
					atomNew.setAtomName(noAnalNoSpaceStr);
					atomNew.setAnalType(0);
					atomNew.setType(0);
					atoms.add(atomNew);
				}
				if(atom.getAnalType()!=null&&atom.getAnalType().equals(0)&&atom.getAtomName()!=null){
					String noSapceAtomName=atom.getAtomName().replaceAll(" +","\u200B");
					atom.setAtomName(noSapceAtomName);
				}
				atoms.add(atom);
				noAnalStr="";
			}
			i=i+step;
		}
		if(!noAnalStr.equals("")){
			String noAnalNoSpaceStr=noAnalStr.replaceAll(" +","\u200B");
			Atom atomNew = new Atom();
			atomNew.setAtomName(noAnalNoSpaceStr);
			atomNew.setAnalType(0);
			atomNew.setType(0);
			atoms.add(atomNew);
		}
		return atoms;
	}
	
    public static void latexCellIntoAtom(List<Atom> atoms) {
    	for(Atom atom:atoms) {
    		List<AtomBE> atomBEs=atom.getAtomBEs();
            if(atomBEs!=null&&atomBEs.size()>0) {
    			for(AtomBE atomBE : atomBEs) {
    				Atom atomTmp=atomBE.getAtom();
    				if(atomTmp==null||atomTmp.getType()==null||!atomTmp.getType().equals(1)||atomTmp.getAtomName()==null||atomTmp.getAtomName().equals("")) continue;
    				List<Atom> childrenAtom=latexIntoAtom(atomTmp.getAtomName());
    				atomTmp.setAtomName(null);
    				atomTmp.setType(0);
    				atomTmp.setAnalType(0);
            		if(childrenAtom==null||childrenAtom.size()<1) continue;
            		latexCellIntoAtom(childrenAtom);
            		atomTmp.setAtomBEsFromAtomList(childrenAtom);	
    			}	
    		}
    		if(atom.getType()!=null&&atom.getType().equals(1)&&atom.getAtomName()!=null&&!atom.getAtomName().equals("")) {
    			List<Atom> childrenAtom=latexIntoAtom(atom.getAtomName());
        		atom.setAtomName(null);
        		atom.setType(0);
        		atom.setAnalType(0);
        		if(childrenAtom==null||childrenAtom.size()<1) continue;
        		latexCellIntoAtom(childrenAtom);
        		atom.setAtomBEsFromAtomList(childrenAtom);	
    		}
    	}
	}
    
    public static Integer charToAtom(String charStr,String latexStr,Atom resultAtom) {
    	AtomChar atomChar=atomCharMap.get(charStr);
		if(atomChar==null) {//无需转义字符
			resultAtom.setAtomName(charStr);
			resultAtom.setType(0);
			resultAtom.setAnalType(0);
			return 1;
		}
		List<AtomRegex> regex=atomChar.getRegex();
		String atomStr=null;
		AtomRegex atomRegexTmp=null;
		for(AtomRegex atomRegex:regex) {
			atomStr=getFirstMatchStr(latexStr,atomRegex,atomChar.getType());
			atomRegexTmp=atomRegex;
			if(atomStr!=null&&!atomStr.equals("")) break;
		}
		if(atomChar.getType().equals(0)&&(atomRegexTmp==null||atomRegexTmp.getMatchtype()==null||!atomRegexTmp.getMatchtype().equals(1))) {//本级需转义
			resultAtom.setAtomName(atomStr==null||atomStr.equals("")?charStr:atomStr);
			resultAtom.setType(0);
			resultAtom.setAnalType(1);
			if(atomRegexTmp!=null&&atomRegexTmp.getAnalysisType()!=null&&atomRegexTmp.getAnalysisType().equals(2)) {resultAtom.setAnalType(0);}
			if(atomRegexTmp!=null&&atomRegexTmp.getRegexId()!=null&&atomRegexTmp.getRegexId().equals(1)&&atomStr!=null&&!atomStr.equals("")){//去除"^\\\\([a-zA-Z]+|\\\\| |;|,|!)"匹配后的一个空格
				String nextStr=atomStr.length()+1>latexStr.length()?null:latexStr.substring(atomStr.length(),atomStr.length()+1);
				if(nextStr!=null&&nextStr.equals(" ")) return atomStr.length()+1;
			}
			String afterCharStr=charStr.length()>latexStr.length()?null:latexStr.substring(charStr.length());
			if(atomStr==null&&afterCharStr!=null){
				Pattern pattern=Pattern.compile("^ +");
				Matcher matcher=pattern.matcher(afterCharStr);
				if(matcher.find()){
					String matchStr=matcher.group();
					return matchStr.length()+1;
				}
			}
			return atomStr==null||atomStr.equals("")?1:atomStr.length();
		}
		if(atomChar.getType().equals(1)||(atomRegexTmp!=null&&atomRegexTmp.getMatchtype()!=null&&atomRegexTmp.getMatchtype().equals(1))) {//块级
			AtomBE atomBE= new AtomBE();
			if(atomStr==null) {
				resultAtom.setAtomName(charStr);
				resultAtom.setType(0);
				resultAtom.setAnalType(0);
				return 1;
			}
			Integer stepTmp=atomStr==null||atomStr.equals("")?1:atomStr.length();
			if(atomStr!=null&&!atomStr.equals("")) {
			    int  beginLength = atomRegexTmp.getMatchtype()!=null&&atomRegexTmp.getMatchtype().equals(1)?2:1;
			   atomStr=atomStr.substring(beginLength);
			   atomStr=atomStr.length()>beginLength?atomStr.substring(0, atomStr.length()-beginLength):"";
			   Atom atomTmp=new Atom();
			   atomTmp.setAtomName(atomStr.equals("")?"\\u200B":atomStr);
			   atomTmp.setType(atomStr.equals("")?0:1);
			   atomTmp.setAnalType(1);
			   atomBE.setAtom(atomTmp);
			}
			atomBE.setBegin(atomRegexTmp==null||atomRegexTmp.getBegin()==null?null:atomRegexTmp.getBegin());
			atomBE.setEnd(atomRegexTmp==null||atomRegexTmp.getEnd()==null?null:atomRegexTmp.getEnd());
			atomBE.setType(atomRegexTmp==null||atomRegexTmp.getType()==null?null:atomRegexTmp.getType());
			resultAtom.setAtomBEs(Arrays.asList(atomBE));
			resultAtom.setType(0);
			resultAtom.setAnalType(0);
			return stepTmp;
		}	
		return 1;
	}
    public static String getFirstMatchStr(String latexStr,AtomRegex atomRegex,Integer type) {
    	if(type.equals(1)) {
    		String begin=atomRegex.getBegin();
    		String end=atomRegex.getEnd();
    		if(!begin.equals(latexStr.substring(0, 1))) return null;
    		int num=1;
    		for(int i=1;i<latexStr.length();i++) {
    			String charStr=latexStr.substring(i, i+1);
    			String beforeCharStr=latexStr.substring(i-1, i);
    			if(charStr.equals(begin)&&!beforeCharStr.equals("\\")) num++;
    			if(charStr.equals(end)&&!beforeCharStr.equals("\\")) num--;
    			if(num==0) return latexStr.substring(0, i+1);
    		}
    		return null;
    	}
    	String regex=atomRegex.getRegex();
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher=pattern.matcher(latexStr);
    	if(matcher.find()){
    		String matchStr = matcher.group();
    		if(matchStr.equals(latexStr.substring(0,matchStr.length()))) return  matchStr;
		}
		return null;
	}
    
}
