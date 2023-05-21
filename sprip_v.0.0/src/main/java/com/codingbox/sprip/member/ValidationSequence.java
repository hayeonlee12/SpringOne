package com.codingbox.sprip.member;

import javax.validation.GroupSequence;

import com.codingbox.sprip.member.ValidationGroup.EmailCheckGroup;
import com.codingbox.sprip.member.ValidationGroup.NotNullGroup;
import com.codingbox.sprip.member.ValidationGroup.PatternCheckGroup;
import com.codingbox.sprip.member.ValidationGroup.SizeCheckGroup;

@GroupSequence({NotNullGroup.class, SizeCheckGroup.class,
				PatternCheckGroup.class, EmailCheckGroup.class})

public interface ValidationSequence {

}
