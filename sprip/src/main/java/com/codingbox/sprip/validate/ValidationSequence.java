package com.codingbox.sprip.validate;

import javax.validation.GroupSequence;

import com.codingbox.sprip.validate.ValidationGroup.EmailCheckGroup;
import com.codingbox.sprip.validate.ValidationGroup.NotNullGroup;
import com.codingbox.sprip.validate.ValidationGroup.PatternCheckGroup;
import com.codingbox.sprip.validate.ValidationGroup.SizeCheckGroup;

@GroupSequence({NotNullGroup.class, SizeCheckGroup.class,
				PatternCheckGroup.class, EmailCheckGroup.class})

public interface ValidationSequence {

}
