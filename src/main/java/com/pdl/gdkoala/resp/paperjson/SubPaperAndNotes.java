package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * SubPaperAndNotes
 * @author yangwei
 *
 */
@Getter
@Setter
public class SubPaperAndNotes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3139098817430872288L;

	private boolean subPaperAndNotesExist = true;
	
	private List<Notes> notesList;
	
}
